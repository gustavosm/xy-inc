package br.com.gps.xyinc.test.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.Assert;

import br.com.gps.xyinc.Application;
import br.com.gps.xyinc.entity.PointEntity;
import br.com.gps.xyinc.service.PointService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@SpringBootTest
@ContextConfiguration(classes = { Application.class })
@TestPropertySource(locations = "classpath:application-test.properties")
public class POITest {

	@Autowired
	private PointService pointService;

	private static PointEntity entity;

	private static PointEntity entityToGetClose;
	private static PointEntity entityNotToGetClose;

	private static ResponseEntity<Object> response;

	/**************************************************
	 * 
	 * Scenario: Create a POI Begin
	 **************************************************/

	@Given("^a POI (\\d+) (\\d+)$")
	public void a_POI(Long xCoordinate, Long yCoordinate) throws Throwable {
		entity = new PointEntity("testing " + System.currentTimeMillis(), xCoordinate, yCoordinate);
	}

	@When("^the client calls save on pointService$")
	public void the_client_calls_save_on_pointService() throws Throwable {
		response = pointService.save(entity);
	}

	@Then("^the service returns success status$")
	public void the_service_returns_success_status() throws Throwable {
		Assert.notNull(response, "Response cannot be null");
		Assert.isTrue(response.getStatusCode().equals(HttpStatus.OK),
				"Expected to return " + HttpStatus.OK + " but got " + response.getStatusCode());
	}

	@Then("^the point in response body$")
	public void the_point_in_response_body() throws Throwable {
		Assert.isTrue(entity.equalsInFields((PointEntity) response.getBody()),
				"Expected to get fields: " + entity + ", but got " + ((PointEntity) response.getBody()));
	}

	@Then("^the point is removed from database$")
	public void the_point_is_removed_from_database() throws Throwable {
		ResponseEntity<String> result = pointService.remove(entity.getId());
		Assert.notNull(result, "Response cannot be null");
		Assert.isTrue(result.getStatusCode().equals(HttpStatus.OK),
				"Expected to return " + HttpStatus.OK + " but got " + result.getStatusCode());
	}

	/**************************************************
	 * End Scenario: Create a POI
	 * 
	 **************************************************/

	/**************************************************
	 * Scenario: Fail to Create a POI X Begin
	 **************************************************/

	@Given("^a POI with invalid X coordinate value$")
	public void a_POI_with_invalid_X_coordinate_value() throws Throwable {
		entity = new PointEntity("testing", -1L, 5L);
	}

	@Then("^the service returns fail status$")
	public void the_service_returns_fail_message() throws Throwable {
		Assert.notNull(response, "Response cannot be null");
		Assert.isTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST),
				"Expected to return " + HttpStatus.BAD_REQUEST + " but got " + response.getStatusCode());

	}

	/**************************************************
	 * End Scenario: Fail to Create a POI X
	 **************************************************/

	/**************************************************
	 * Scenario: Fail to Create a POI X Begin
	 **************************************************/

	@Given("^a POI with invalid Y coordinate value$")
	public void a_POI_with_invalid_Y_coordinate_value() throws Throwable {
		entity = new PointEntity("testing", 5L, -1L);
	}

	/**************************************************
	 * End Scenario: Fail to Create a POI Y
	 **************************************************/

	/**************************************************
	 * Scenario: List all POIs Begin
	 **************************************************/
	@When("^the client calls findAll on pointService$")
	public void the_client_calls_findAll_on_pointService() throws Throwable {
		response = pointService.findAll();
	}

	@SuppressWarnings("unchecked")
	@Then("^a list of POIs in response body$")
	public void a_list_of_POIs_in_response_body() throws Throwable {
		Assert.notNull(response.getBody(), "Expected response body not to be null, but it was.");
		Assert.isTrue(response.getBody() instanceof List,
				"Expected " + List.class + " in response body, but got " + response.getBody().getClass());
		List<Object> responseBody = (List<Object>) response.getBody();
		Long wrongObjects = responseBody.stream().filter(object -> !(object instanceof PointEntity)).count();
		Assert.isTrue(Long.valueOf(0L).equals(wrongObjects),
				"Expected to get 0 objects thats not a PointEntity object, but got: " + wrongObjects);

	}

	/**************************************************
	 * End Scenario: List all POIs
	 **************************************************/
	/**************************************************
	 * Begin Scenario: List all POIs closest
	 **************************************************/

	@Given("^two POIs (\\d+) (\\d+) (\\d+) (\\d+)$")
	public void two_POIs(Long xCoord1, Long yCoord1, Long xCoord2, Long yCoord2) throws Throwable {
		entityToGetClose = new PointEntity("testing " + System.currentTimeMillis(), xCoord1, yCoord1);
		entityNotToGetClose = new PointEntity("testing " + System.currentTimeMillis(), xCoord2, yCoord2);
	}

	@Given("^insert them in database$")
	public void insert_them_in_database() throws Throwable {
		entityToGetClose = (PointEntity) pointService.save(entityToGetClose).getBody();
		entityNotToGetClose = (PointEntity) pointService.save(entityNotToGetClose).getBody();
	}

	@When("^the client calls findClosestPoints on PointService to (-?\\d+) and (-?\\d+) given ([0-9.]+)$")
	public void the_client_calls_findClosestPoints_on_PointService_to_and_given(Long xCoord, Long yCoord, Double dMax)
			throws Throwable {
		response = pointService.findClosestPoints(xCoord, yCoord, dMax);
	}

	@SuppressWarnings("unchecked")
	@Then("^a correct list of POIs$")
	public void a_correct_list_of_POIs() throws Throwable {

		List<Object> responseBody = (List<Object>) response.getBody();
		Long wrongObjects = responseBody.stream().filter(object -> !(object instanceof PointEntity)).count();
		
		Assert.isTrue(Long.valueOf(0L).equals(wrongObjects),
				"Expected to get 0 thats not a PointEntity object, but got: " + wrongObjects);

		Long correctObject = responseBody.stream().filter(object -> entityToGetClose.equals(object)).count();
		Assert.isTrue(Long.valueOf(1L).equals(correctObject),
				"Expected to get 1 PointEntity equals to: " + entityToGetClose + ", but got: " + correctObject);
		
		Long wrongObject = responseBody.stream().filter(object -> entityNotToGetClose.equals(object)).count();
		Assert.isTrue(Long.valueOf(0L).equals(wrongObject),
				"Expected to get 0 PointEntity equals to: " + entityNotToGetClose + " but got: " + wrongObject);
	}

	@Then("^remove two POIs from database$")
	public void remove_two_POIs_from_database() throws Throwable {
		pointService.remove(entityToGetClose.getId());
		pointService.remove(entityNotToGetClose.getId());
	}
	/**************************************************
	 * End Scenario: List all POIs closest
	 **************************************************/
}
