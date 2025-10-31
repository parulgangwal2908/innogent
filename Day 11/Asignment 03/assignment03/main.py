from fastapi import FastAPI, UploadFile, File
from fastapi.responses import JSONResponse, StreamingResponse
from prisma import Prisma
import pandas as pd
import io
from fastapi.encoders import jsonable_encoder

app = FastAPI()




db = Prisma()

@app.on_event("startup")
async def startup():
    await db.connect()

@app.on_event("shutdown")
async def shutdown():
    await db.disconnect()


@app.post("/upload-csv")
async def upload_csv(file: UploadFile = File(...)):
    try:
        content = await file.read()
        df = pd.read_csv(io.StringIO(content.decode('utf-8')))

        records = []
        for _, row in df.iterrows():
            record = await db.record.create(
                data={
                    "name": str(row["name"]),
                    "email": str    (row["email"]),
                    "age": int(row["age"])
                }
            )
            records.append(record)

        return JSONResponse(content={"inserted": len(records)})

    except Exception as e:
        return JSONResponse(content={"error": str(e)}, status_code=400)




@app.post("/add-record")
async def add_record(data: dict):
    try:
        record = await db.record.create(
            data={
                "name": data["name"],
                "email": data["email"],
                "age": int(data["age"])
            }
        )

        safe_record = jsonable_encoder(record)

        return JSONResponse(content={"record": safe_record})

    except Exception as e:
        return JSONResponse(content={"error": str(e)}, status_code=400)

@app.get("/download")
async def download_file(format: str = "csv", limit: int = None):
    try:
        records = await db.record.find_many(take=limit)
        if not records:
            return JSONResponse(content={"message": "No records found"})

        df = pd.DataFrame([r.dict() for r in records])

        if format == "csv":
            output = io.StringIO()
            df.to_csv(output, index=False)
            output.seek(0)
            return StreamingResponse(
                io.BytesIO(output.getvalue().encode()),
                media_type="text/csv",
                headers={"Content-Disposition": "attachment; filename=data.csv"}
            )

        elif format in ["xls", "xlsx"]:
            output = io.BytesIO()
            df.to_excel(output, index=False)
            output.seek(0)
            return StreamingResponse(
                output,
                media_type="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                headers={"Content-Disposition": "attachment; filename=data.xlsx"}
            )

        else:
            return JSONResponse(content={"error": "Unsupported format"}, status_code=400)

    except Exception as e:
        return JSONResponse(content={"error": str(e)}, status_code=500)

