Steps to run this project on local server (Linux)

Prerequisites
maven
tomcat

Install maven on Ubuntu
sudo apt-get update && sudo apt-get install maven

Install tomcat7 on Ubuntu
sudo apt-get update && sudo apt-get install tomcat7

Clone the project
git clone https://github.com/NitinSatpal/location-tracker.git

Go to root directory - cd location-tracker/

Build using maven
mvn clean install

This will generate a 'target' folder inside the root directory with com.location.tracker-0.0.1-SNAPSHOT.war

Go to the target directory - cd target/

Rename .war file inside target directory
mv com.location.tracker-0.0.1-SNAPSHOT.war com.location.tracker

Copy this renamed .war file to the webpps folder of tomcat
sudo cp -r /home/nitin/location-tracker/target/com.location.tracker.war /var/lib/tomcat7/webapps/


Restart tomcat
sudo /etc/init.d/tomcat7 restart


REST APIS
We are assuming that we have driver (user), tracking device as well as vehicles already registered with us

To register Driver (user)
http://localhost:8080/com.location.tracker/user
Body Params {
	"address": "Madinaguda",
	"firstName": "Nitin",
	"lastName": "satpal"

}

To register Tracking device
http://localhost:8080/com.location.tracker/device
Body Params: {
	"modelNo": "1230",
	"deviceManufacturer": DEVICE_MANUFACTURER
}

To register Vehicle
http://localhost:8080/com.location.tracker/vehicle
Body Params : {
	"modelNo": "1230",
	"yearOfManufacture": YEAR_OF_MANUFACTURE
}
-> For the assignment no 'Required Checks' are present. So null data will also be getting saved with some unique id.

To Save location data
http://localhost:8080/com.location.tracker/locationy 
Body Params : {
	"deviceId": 2,
	"userId": 1,
	"vehicleId": 7,
	"latitude": 29.123,
	"longitude": 30.123
}

-> We can use NOSQL to allow more richer data to be saved along with location data like Key Value pair format or just a dump of json object. But for this assignment, I used predefined MySQL schema with above fields

To get location data
http://localhost:8080/com.location.tracker/locations?deviceId=2&startTime=1.31.2018-23:00:00&endTime=1.31.2018-23:47:00

-> Query parameter startTime and endTime can be null. In that case, it will return the list of all the location records of the given device.
For now we are not doing any pagination for fetching records of the device for the assignment. But we should not fetch all the records ideally. We should use lazy loading with pagination.

