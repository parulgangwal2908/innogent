# app/database.py
from prisma import Prisma

# Single shared Prisma client for the app
db = Prisma()
