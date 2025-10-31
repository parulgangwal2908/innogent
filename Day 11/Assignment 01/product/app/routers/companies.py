from fastapi import APIRouter, Depends, HTTPException, Query
from sqlalchemy.orm import Session
from typing import List
from .. import crud, schemas, models
from ..database import get_db
router = APIRouter(prefix="/companies", tags=["companies"])

@router.post("/", response_model=schemas.CompanyRead)
def create_company(company_in: schemas.CompanyCreate, db: Session = Depends(get_db)):
    if db.query(models.Company).filter(models.Company.name == company_in.name).first():
        raise HTTPException(status_code=400, detail="Company with this name already exists")
    return crud.create_company(db, company_in)

@router.get("/", response_model=List[schemas.CompanyRead])
def list_companies(skip: int = Query(0, ge=0), limit: int = Query(100, ge=1), db: Session = Depends(get_db)):
    return crud.list_companies(db, skip=skip, limit=limit)