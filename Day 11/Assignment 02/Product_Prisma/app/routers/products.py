from fastapi import APIRouter, HTTPException, Query
from typing import Optional, List
from prisma import Prisma
from .. import schemas

router = APIRouter(prefix="/products", tags=["products"])
db = Prisma()  # Prisma client (main.py should handle connect/disconnect)


# 🟢 Create Product
@router.post("/", response_model=schemas.ProductRead)
async def create_product(product_in: schemas.ProductCreate):
    # Check if company exists (if provided)
    if product_in.company_id:
        company = await db.company.find_unique(where={"id": product_in.company_id})
        if not company:
            raise HTTPException(status_code=404, detail="Company not found")

    # Create product
    try:
        product = await db.product.create(
            data={
                "name": product_in.name,
                "price": product_in.price,
                "category": product_in.category,
                "company_id": product_in.company_id,
                "tag_ids": product_in.tag_ids or [],
            }
        )
        return product
    except Exception as e:
        raise HTTPException(status_code=400, detail=str(e))


# 🟢 Read Product by ID
@router.get("/{product_id}", response_model=schemas.ProductRead)
async def read_product(product_id: int):
    product = await db.product.find_unique(where={"id": product_id})
    if not product:
        raise HTTPException(status_code=404, detail="Product not found")
    return product


# 🟢 Update Product
@router.put("/{product_id}", response_model=schemas.ProductRead)
async def update_product(product_id: int, payload: schemas.ProductUpdate):
    product = await db.product.find_unique(where={"id": product_id})
    if not product:
        raise HTTPException(status_code=404, detail="Product not found")

    data = payload.dict(exclude_unset=True)

    try:
        updated = await db.product.update(where={"id": product_id}, data=data)
        return updated
    except Exception as e:
        raise HTTPException(status_code=400, detail=str(e))


# 🟢 Delete Product
@router.delete("/{product_id}")
async def delete_product(product_id: int):
    product = await db.product.find_unique(where={"id": product_id})
    if not product:
        raise HTTPException(status_code=404, detail="Product not found")

    await db.product.delete(where={"id": product_id})
    return {"detail": "Product deleted"}


# 🟢 Search Products (with optional company filter & pagination)
@router.get("/search", response_model=schemas.ProductSearchResult)
async def search_products(
    q: Optional[str] = Query(None, description="Search term"),
    company_id: Optional[int] = Query(None),
    skip: int = Query(0, ge=0),
    limit: int = Query(20, ge=1),
):
    filters = {}

    if q:
        filters["OR"] = [
            {"name": {"contains": q, "mode": "insensitive"}},
            {"category": {"contains": q, "mode": "insensitive"}},
        ]
    if company_id:
        filters["company_id"] = company_id

    total = await db.product.count(where=filters)
    items = await db.product.find_many(where=filters, skip=skip, take=limit)
    return {"total": total, "items": items}
