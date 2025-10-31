from typing import List,Optional
from pydantic import BaseModel,Field

class CompanyBase(BaseModel):
    name: str=Field(...,min_length=1,max_length=200)
    description:Optional[str]=None

class CompanyCreate(CompanyBase):
    pass

class CompanyRead(CompanyBase):
    id:int
    class Config:
       from_attributes=True




class TagBase(BaseModel):
    name:str=Field(...,min_length=1,max_length=100)
class TagCreate(TagBase):
    pass
class TagRead(TagBase):
    id:int
    class Config:
       from_attributes=True





class ProductBase(BaseModel):
    name: str=Field(...,min_length=1,max_length=200)
    category:Optional[str]=None
    price:float=Field(...,ge=0)

class ProductCreate(ProductBase):
    company_id:Optional[int]=None
    tag_ids:Optional[List[int]]=[]
class ProductUpdate(BaseModel):
    name:Optional[str]
    category:Optional[str]
    price:Optional[float]
    company_id:Optional[int]
    tag_ids:Optional[List[int]]
class ProductRead(BaseModel):
    id:int
    company:Optional[CompanyRead]=None
    tags:List[TagRead]=[]
    class Config:
        from_attributes=True


class ProductSearchResult(BaseModel):
    total:int
    items:List[ProductRead]
