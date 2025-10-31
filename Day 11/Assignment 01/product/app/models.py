from sqlalchemy import Column,Integer,String,Float,ForeignKey,Table,UniqueConstraint
from sqlalchemy.orm import relationship
from .database import Base

product_tag=Table("product_tag",
                  Base.metadata,
                  Column("product_id",Integer,ForeignKey("products.id",ondelete="CASCADE"),primary_key=True),
                  Column("tag_id",Integer,ForeignKey("tags.id",ondelete="CASCADE"),primary_key=True))

class Company(Base):
    __tablename__="companies"
    id=Column(Integer,primary_key=True,index=True)
    name=Column(String,unique=True,nullable=False,index=True)
    description=Column(String,nullable=True)

    products=relationship("Product",back_populates="company",cascade='all, delete-orphan')

class Product(Base):
    __tablename__="products"
    id=Column(Integer,primary_key=True,index=True)
    name=Column(String,unique=True,nullable=False,index=True)
    category=Column(String,nullable=True,index=True)
    price=Column(Float,nullable=False,default=0.0)
    company_id=Column(Integer,ForeignKey("companies.id",ondelete="SET NULL"),nullable=True)

    __table_args__ = (UniqueConstraint("name", "company_id", name="uix_name_company"),)
    company=relationship("Company",back_populates="products")
    tags=relationship("Tag",secondary=product_tag,back_populates="products")


class Tag(Base):
    __tablename__="tags"
    id=Column(Integer,primary_key=True,index=True)
    name=Column(String,nullable=False,unique=True,index=True)

    products=relationship("Product",secondary=product_tag,back_populates="tags")