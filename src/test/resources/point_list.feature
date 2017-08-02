Feature: List on Point of Interest (POI)
	
	Scenario: List all POIs
		When the client calls findAll on pointService
		Then the service returns success status
		And a list of POIs in response body	
		
	Scenario Outline: List all POIs closest
		Given two POIs <xCoord1> <yCoord1> <xCoord2> <yCoord2>
		And insert them in database
		When the client calls findClosestPoints on PointService to <xCoord> and <yCoord> given <distance>
		Then the service returns success status
		And a correct list of POIs
		And remove two POIs from database
	
	Examples:
    | xCoord | yCoord | distance | xCoord1 | yCoord1 | xCoord2 | yCoord2 |
    |    4	 |  2	  |     10.2 |    3	   |    4    |    50   |    28   |
    |    50	 |  29	  |     2.1	 |	  50   |   28    |   3     |    4    |
    
    
	Scenario Outline: List all POIs closest with wrong coordinates
		Given two POIs <xCoord1> <yCoord1> <xCoord2> <yCoord2>
		And insert them in database
		When the client calls findClosestPoints on PointService to <xCoord> and <yCoord> given <distance>
		Then the service returns fail status
		And remove two POIs from database
	
	Examples:
    | xCoord | yCoord | distance | xCoord1 | yCoord1 | xCoord2 | yCoord2 |
    |    -4	 |  2	  |     10.2 |    6	   |    7    |    9    |    48   |
    |    50	 |  -29	  |     2.1	 |	  50   |   21    |   6     |    9    |