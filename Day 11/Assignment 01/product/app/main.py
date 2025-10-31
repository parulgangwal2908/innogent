from fastapi import FastAPI, Request
import logging
from .database import engine, Base
from .routers import companies, products,tags

Base.metadata.create_all(bind=engine)
app=FastAPI(title="Product Mangement Api")

app.include_router(companies.router)
app.include_router(products.router)
app.include_router(tags.router)
@app.get("/")
def root():
    return {"message": "Product Management API."}

