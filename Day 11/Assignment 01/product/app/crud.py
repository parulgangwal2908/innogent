from typing import List,Optional,Tuple
from sqlalchemy.orm import Session
from sqlalchemy import or_,func,String
from . import models,schemas

def get_company(db:Session,company_id:int)->Optional[models.Company]:
    return db.query(models.Company).filter(models.Company.id==company_id).first()
def create_company(db:Session,company_in:schemas.CompanyCreate)->models.Company:
    db_obj=models.Company(**company_in.dict())
    db.add(db_obj)
    db.commit()
    db.refresh(db_obj)
    return db_obj

def list_companies(db:Session,skip:int=0, limit:int=100)->List[models.Company]:
    return db.query(models.Company).offset(skip).limit(limit).all()

def get_tag(db:Session,name:str)->models.Tag:
    t=db.query(models.Tag).filter(func.lower(models.Tag.name)==name.lower()).first()
    if t:
        return t
def create_tag(db:Session,name:str)->models.Tag:
    t=db.query(models.Tag).filter(func.lower(models.Tag.name)==name.lower()).first()
    t=models.Tag(name=name)
    db.add(t)
    db.commit()
    db.refresh(t)
    return t
def list_tags(db:Session,skip:int=0,limit:int=100)->List[models.Tag]:
    return db.query(models.Tag).offset(skip).limit(limit).all()



def get_product(db: Session, product_id: int) -> Optional[models.Product]:
    return db.query(models.Product).filter(models.Product.id == product_id).first()

def create_product(db: Session, product_in: schemas.ProductCreate) -> models.Product:
    q = db.query(models.Product).filter(models.Product.name == product_in.name, models.Product.company_id == product_in.company_id)
    if q.first():
        raise ValueError("Product with this name already exists for the company")

    db_obj = models.Product(
        name=product_in.name,
        category=product_in.category,
        price=float(product_in.price),
        company_id=product_in.company_id
    )
    db.add(db_obj)
    db.commit()
    db.refresh(db_obj)

    if product_in.tag_ids:
        for tid in product_in.tag_ids:
            tag = db.query(models.Tag).filter(models.Tag.id == tid).first()
            if tag:
                db_obj.tags.append(tag)
        db.commit()
        db.refresh(db_obj)

    return db_obj

def update_product(db: Session, product: models.Product, updates: dict) -> models.Product:
    for key, val in updates.items():
        if hasattr(product, key) and val is not None:
            setattr(product, key, val)
    db.add(product)
    db.commit()
    db.refresh(product)
    return product

def delete_product(db: Session, product: models.Product):
    db.delete(product)
    db.commit()


def search_product(db: Session, q: Optional[str], company_id: Optional[int], skip: int, limit: int) -> Tuple[int, List[models.Product]]:
    query = db.query(models.Product)
    if q:
        like_q = f"%{q}%"
        query = query.filter(
            or_(
                models.Product.name.ilike(like_q),
                models.Product.category.ilike(like_q),
                func.cast(models.Product.price, String).ilike(like_q)
            )
        )
    if company_id:
        query = query.filter(models.Product.company_id == company_id)

    total = query.count()
    items = query.offset(skip).limit(limit).all()
    return total, items

