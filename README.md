#Kafka Start code

.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

.\bin\windows\kafka-server-start.bat .\config\server.properties

# Product-Transaction
Product Transaction

localhost:5002/e-auction/api/v1/buyer/place-bid

{
	
	"firstName" : "Fname",
	"lastName":"Lname",
	"address":"TestAddress",
	"city":"TestCity",
	"state":"TestState",
	"pin":"12345",
	"phone":"1234567890",
	"email":"test@gmail.com",
	"productId":"123",
	"bidAmount":"100.00"
}


PUT
localhost:5002/e-auction/api/v1/buyer/update-bid/123/test@gmail.com/2000
