from fastapi import APIRouter, HTTPException, Query
from typing import List
from prisma import Prisma
from .. import schemas

router = APIRouter(prefix="/tags", tags=["Tags"])
db = Prisma()  # Prisma client instance


@router.get("/", response_model=List[schemas.TagRead])
async def list_all_tags(skip: int = 0, limit: int = 100):
    tags = await db.tag.find_many(skip=skip, take=limit)
    return tags



@router.post("/", response_model=schemas.TagRead)
async def create_new_tag(name: str):
    existing_tag = await db.tag.find_first(where={"name": name})
    if existing_tag:
        raise HTTPException(status_code=400, detail="Tag already exists")

    tag = await db.tag.create(data={"name": name})
    return tag


@router.get("/{name}", response_model=schemas.TagRead)
async def get_tag_by_name(name: str):
    tag = await db.tag.find_first(where={"name": name})
    if not tag:
        raise HTTPException(status_code=404, detail="Tag not found")
    return tag
