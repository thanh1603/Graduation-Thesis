##### COMMANDS TO CREATE A SAMPLE DATABASE & COLLECTION #####
> use mydb

> db.mycollection.insertMany( [
	{ "id" : "101", "name" : "Daniel Atlas" }, 
	{ "id" : "102", "name" : "Charlotte Neil" },
	{ "id" : "97", "name" : "tom jackmen" }
] )

##### COMMAND TO DISPLAY THE DOCUMENTS OF A COLLECTION #####
> db.mycollection.find()