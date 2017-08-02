# XYInc GPS

## Introduction

This project is to manage points in a 2D plan, providing five basic operations:

    1 - Insert a point into database;

    2 - List all points stored in database;

    3 - List all points that are at most a certain distance far from a determined point;
    
    4 - List a certain point given its id;
    
    5 - Remove a certain point given its id.

## Installation

In order to get this project running, folow the steps bellow:

    1 - Make sure your machine contains Maven, Git and JDK 1.8 installed. If you don't have it you may download Maven in (https://maven.apache.org/download.cgi), Git in (https://git-scm.com/downloads) and JDK in (http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
   
    2 - Install a MySQL Server on your computer listening in 3306 port. You can download it here (https://dev.mysql.com/downloads/installer/), or feel free to get from wherever you want;
    
    3 - Login with root on MySQL Server and execute CreateUserDB.sql script, and then execute CreateDatabase.sql script on your MySQL Server;

    4 - Clone this repository using command-line: git clone https://github.com/gustavosm/xy-inc.git  or use an UI git to do this;

    5 - Open terminal and navigate to the directory containing pom.xml and execute: mvn clean install;
    
    6 - In this directory, execute: mvn spring-boot:run;
    
    7 - You may access it from: http://localhost:8080/swagger-ui.html Or you may use it from your favorite http client.

## Testing Samples

    1 - There are five routes: POST /point/save, GET /point/findAll, GET /point/findClosest?xCoordinate=&yCoordinate=&dMax=, GET /point?pointId=, DELETE /point/{pointId};
   
    2 - POST /point/save expects a JSON like:
		
		{
  			"id": 0, //can be ommited - if present system will try to update this point
 			"poiName": "string",
  			"xCoordinate": 0,
  			"yCoordinate": 0
		}
    	
	If everything goes fine, the new object will be returned with 200 OK. Else some 400 Bad Request may be returned telling user what causes the error;

    3 - GET /point/findAll returns a list of points. Points are serialized as JSON (just like the JSON above). If everything goes fine, a list will be returned with 200 OK, else some 400 Bad Request may be returned telling user what causes the error;

    4 - GET /point/findClosest?xCoordinate=&yCoordinate=&dMax= returns a list of points thats up to dMax distance from point (xCoordinate, yCoordinate). If everything goes fine, a list will be returned with 200 OK, else some 400 Bad Request may be returned telling user what causes the error;

    5 - GET /point?pointId= expects to receive an idas query param of some existing point. If everything goes fine, a point will be returned with 200 OK, else some 400 Bad Request may be returned telling user what causes the error;
    
    6 - DELETE /point/{pointId} expects to receive an idas path param of some existing point. If everything goes fine, a "Success" message will be returned with 200 OK, else some 400 Bad Request may be returned telling user what causes the error.