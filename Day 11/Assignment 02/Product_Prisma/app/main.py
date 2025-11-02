# app/main.py
from fastapi import FastAPI
from app.database import db
from app.routers import products, companies, tags

app = FastAPI()


@app.on_event("startup")
async def startup():
    await db.connect()
    print(" Prisma connected")

@app.on_event("shutdown")
async def shutdown():
    await db.disconnect()
    print("Prisma disconnected")

app.include_router(companies.router, prefix="/companies", tags=["Companies"])
app.include_router(tags.router, prefix="/tags", tags=["Tags"])
app.include_router(products.router, prefix="/products", tags=["Products"])
