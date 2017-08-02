Feature: Write Point of Interest (POI)
	
	Scenario Outline: Create a POI
		Given a POI <xCoordinate> <yCoordinate>
		When the client calls save on pointService
		Then the service returns success status
		And the point in response body
		And the point is removed from database
		
		Examples:
	    | xCoordinate | yCoordinate | 
	    |    4		  |  2	 		|
	    |    50		  |  29	  		|	
	    
	Scenario: Fail to Create a POI X
		Given a POI with invalid X coordinate value
		When the client calls save on pointService
		Then the service returns fail status
		
	Scenario: Fail to Create a POI Y
		Given a POI with invalid Y coordinate value
		When the client calls save on pointService
		Then the service returns fail status
		
		