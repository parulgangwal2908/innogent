from fastapi import APIRouter, Depends, HTTPException, Query
from sqlalchemy.orm import Session
from typing import Optional, List
from .. import crud, schemas, models
from ..database import get_db

router = APIRouter(
    prefix="/tags",
    tags=["Tags"]
)


@router.get("/", response_model=List[schemas.TagRead])
def list_all_tags(skip: int = 0, limit: int = 100, db: Session = Depends(get_db)):
    tags = crud.list_tags(db, skip=skip, limit=limit)
    return tags

@router.post("/", response_model=schemas.TagRead)
def create_new_tag(name: str, db: Session = Depends(get_db)):
    existing_tag = crud.get_tag(db, name)
    if existing_tag:
        raise HTTPException(status_code=400, detail="Tag already exists")
    tag = crud.create_tag(db, name)
    return tag

@router.get("/{name}", response_model=schemas.TagRead)
def get_tag_by_name(name: str, db: Session = Depends(get_db)):
    tag = crud.get_tag(db, name)
    if not tag:
        raise HTTPException(status_code=404, detail="Tag not found")
    return tag
