<b>Steps to run this project on local server (Linux)</b><br />

<b>Prerequisites </b><br />
maven
tomcat

<b>Install maven on Ubuntu</b><br />
sudo apt-get update && sudo apt-get install maven

<b>Install tomcat7 on Ubuntu</b><br />
sudo apt-get update && sudo apt-get install tomcat7

<b>Clone the project</b><br />
git clone https://github.com/NitinSatpal/location-tracker.git

<b>Go to root directory </b><br />
cd location-tracker/

<b>Build using maven</b><br />
mvn clean install

This will generate a 'target' folder inside the root directory with com.location.tracker-0.0.1-SNAPSHOT.war

<b>Go to the target directory</b><br />
cd target/

<b>Rename .war file inside target directory</b><br />
mv com.location.tracker-0.0.1-SNAPSHOT.war com.location.tracker

<b>Copy this renamed .war file to the webpps folder of tomcat</b><br />
sudo cp -r /home/nitin/location-tracker/target/com.location.tracker.war /var/lib/tomcat7/webapps/


<b>Restart tomcat</b><br />
sudo /etc/init.d/tomcat7 restart


<b>REST APIS</b><br />
We are assuming that we have driver (user), tracking device as well as vehicles already registered with us<br />

<b>To register Driver (user)</b><br />
http://localhost:8080/com.location.tracker/user<br />
Body Params {
	"address": "Madinaguda",
	"firstName": "Nitin",
	"lastName": "satpal"

}

<b>To register Tracking device</b><br />
http://localhost:8080/com.location.tracker/device<br />
Body Params: {
	"modelNo": "1230",
	"deviceManufacturer": DEVICE_MANUFACTURER
}

<b>To register Vehicle</b><br />
http://localhost:8080/com.location.tracker/vehicle<br />
Body Params : {
	"modelNo": "1230",
	"yearOfManufacture": YEAR_OF_MANUFACTURE
}
-> For the assignment no 'Required Checks' are present. So null data will also be getting saved with some unique id.

<b>To Save location data</b><br />
http://localhost:8080/com.location.tracker/location<br />
Body Params : {
	"deviceId": 2,
	"userId": 1,
	"vehicleId": 7,
	"latitude": 29.123,
	"longitude": 30.123
}

-> We can use NOSQL to allow more richer data to be saved along with location data like Key Value pair format or just a dump of json object. But for this assignment, I used predefined MySQL schema with above fields

<b>To get location data</b><br />
http://localhost:8080/com.location.tracker/locations?deviceId=2&startTime=1.31.2018-23:00:00&endTime=1.31.2018-23:47:00<br />

-> Query parameter startTime and endTime can be null. In that case, it will return the list of all the location records of the given device.<br />
For now we are not doing any pagination for fetching records of the device for the assignment. But we should not fetch all the records ideally. We should use lazy loading with pagination.

