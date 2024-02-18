# main.py
from typing import Union
from fastapi import FastAPI, Request, APIRouter, Response, File, UploadFile
import pymongo
from bson.json_util import dumps
from bson.json_util import loads
from fastapi.templating import Jinja2Templates
from fastapi.responses import HTMLResponse, RedirectResponse
from fastapi.staticfiles import StaticFiles
from pydantic import BaseModel, Field
import datetime
import secrets
import time
import json

app = FastAPI()
app.mount("/img", StaticFiles(directory="img"), name="img")

templates = Jinja2Templates(directory="templates")
page_router = APIRouter()

class Story(BaseModel):
    user: str
    title: str
    text: str
    long: float
    lat: float



@app.get("/")
async def root():
    return {"message" : "empty"}

@app.post("/createstoryb59a1937")
async def createstory(s: Story):

	ins_sid = secrets.token_hex(4)
	
	ins_story = { 
        "sid": f'{ins_sid}', 
        "user": f'{s.user}', 
        "title": f'{s.title}',
        "text": f'{s.text}',
        "location": {'type': "Point", "coordinates": [s.long,s.lat]},
		"time": int(time.time()),
    } 

	myclient = pymongo.MongoClient("mongodb://127.0.0.1:27017")
	db = myclient["speaks"]
	col = db["story"]
	col.insert_one(ins_story)
	return {"message" : "success"}


@app.post("/uploadimage696467a7")
async def create_upload_file(image: UploadFile = File(...)):
    myclient = pymongo.MongoClient("mongodb://127.0.0.1:27017")
    
    db = myclient["speaks"]
    col = db["story"]
    ret = col.find({}, {'_id': 0}).sort({"time" : -1})
    obj = loads(dumps(ret))
    sid = obj[0]["sid"]

    image.filename = f"{sid}.jpg"
    contents = await image.read()
    with open(f"img/{image.filename}", "wb") as f:
        f.write(contents)
    return {"name": image.filename}

@app.get("/getstory2f22d7d5")
async def read_item(lat: float, long: float):
	myclient = pymongo.MongoClient("mongodb://127.0.0.1:27017")
	db = myclient["speaks"]
	col = db["story"]
	#ret = col.find( { "location" : { "$near" : [long,lat] } } ).limit(5)
	ret = col.find({"location" : {"$nearSphere" : {"$geometry": {"type": "Point", "coordinates": [long, lat]}}}},{'_id' : 0}).limit(5)
	print(type(ret))
	return loads(dumps(ret))

	#pipeline = [
    #    {"$project": {"diff": {"$abs": {"$subtract": [lat, '$lat']}},"diff2": {"$abs": {"$subtract": [long, '$long']}}, "_id": 0, "sid": 1,"user": 1, "title": 1, "text": 1, "lat": 1, "long": 1, "imgName": 1}},
    #    {"$sort": {"diff": 1, "diff2": 1}},
    #    {"$limit": 5}
    #]
	#ret = col.aggregate(pipeline)

@app.get("/getallstoryb59a1937")
async def root():
	myclient = pymongo.MongoClient("mongodb://127.0.0.1:27017")
	db = myclient["speaks"]
	col = db["story"]
	ret = col.find({}, {'_id': 0})
	return loads(dumps(list(ret)))

@app.get("/getallstoryb59a1937")
async def root():
	myclient = pymongo.MongoClient("mongodb://127.0.0.1:27017")
	db = myclient["speaks"]
	col = db["story"]
	ret = col.find({}, {'_id': 0})
	return loads(dumps(list(ret)))