from fastapi import APIRouter, Depends, HTTPException, Query
from sqlalchemy.orm import Session
from typing import Optional, List
from .. import crud, schemas, models
from ..database import get_db
router = APIRouter(prefix="/products", tags=["products"])

@router.post("/", response_model=schemas.ProductRead)
def create_product(product_in: schemas.ProductCreate, db: Session = Depends(get_db)):
    if product_in.company_id:
        comp = crud.get_company(db, product_in.company_id)
        if not comp:
            raise HTTPException(status_code=404, detail="Company not found")
    try:
        p = crud.create_product(db, product_in)
        return p
    except ValueError as ve:
        raise HTTPException(status_code=400, detail=str(ve))

@router.get("/{product_id}", response_model=schemas.ProductRead)
def read_product(product_id: int, db: Session = Depends(get_db)):
    p = crud.get_product(db, product_id)
    if not p:
        raise HTTPException(status_code=404, detail="Product not found")
    return p

@router.put("/{product_id}", response_model=schemas.ProductRead)
def update_product(product_id: int, payload: schemas.ProductUpdate, db: Session = Depends(get_db)):
    p = crud.get_product(db, product_id)
    if not p:
        raise HTTPException(status_code=404, detail="Product not found")

    data = payload.dict(exclude_unset=True)

    if "tag_ids" in data:
        tag_ids = data.pop("tag_ids")
        p.tags.clear()
        if tag_ids:
            for tid in tag_ids:
                tag = db.query(models.Tag).filter(models.Tag.id == tid).first()
                if tag:
                    p.tags.append(tag)
        db.commit()
        db.refresh(p)

    updated = crud.update_product(db, p, data)
    return updated

@router.delete("/{product_id}")
def delete_product(product_id: int, db: Session = Depends(get_db)):
    p = crud.get_product(db, product_id)
    if not p:
        raise HTTPException(status_code=404, detail="Product not found")
    crud.delete_product(db, p)
    return {"detail": "Product deleted"}

@router.get("/search", response_model=schemas.ProductSearchResult)
def search_products(q: Optional[str] = Query(None, description="search term"),
                    company_id: Optional[int] = Query(None),
                    skip: int = Query(0, ge=0), limit: int = Query(20, ge=1),
                    db: Session = Depends(get_db)):
    total, items = crud.search_product(db, q=q, company_id=company_id, skip=skip, limit=limit)
    return {"total": total, "items": items}