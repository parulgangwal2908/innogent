from fastapi import APIRouter, HTTPException, Query
from typing import List
from prisma import Prisma
from .. import schemas

router = APIRouter(prefix="/companies", tags=["companies"])

# Use the global Prisma client from main.py
db = Prisma()

@router.post("/", response_model=schemas.CompanyRead)
async def create_company(company_in: schemas.CompanyCreate):
    existing = await db.company.find_first(where={"name": company_in.name})
    if existing:
        raise HTTPException(status_code=400, detail="Company with this name already exists")

    new_company = await db.company.create(data={"name": company_in.name})
    return new_company


@router.get("/", response_model=List[schemas.CompanyRead])
async def list_companies(
    skip: int = Query(0, ge=0),
    limit: int = Query(100, ge=1)
):
    companies = await db.company.find_many(skip=skip, take=limit)
    return companies
