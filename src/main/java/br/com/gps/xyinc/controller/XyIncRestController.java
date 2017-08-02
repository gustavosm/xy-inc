package br.com.gps.xyinc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gps.xyinc.entity.PointEntity;
import br.com.gps.xyinc.service.PointService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/point")
public class XyIncRestController {

	@Autowired
	private PointService pointService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Returns a point of interest, based on parameter: pointId")
	public ResponseEntity<Object> find(
			@ApiParam(value = "POI's ID you want to get.") @RequestParam("pointId") Long pointId) {
		return pointService.findOne(pointId);
	}

	@DeleteMapping("/{pointId}")
	@ApiOperation(value = "Removes the point of interest whose id is equal to pointId param.")
	public ResponseEntity<String> delete(
			@ApiParam(value = "POI's ID you want to delete.") @PathVariable Long pointId) {
		return pointService.remove(pointId);
	}

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Saves a point of interest on data base.", response = ResponseEntity.class)
	public ResponseEntity<Object> save(
			@ApiParam(value = "Object to be sent in request body.") @RequestBody PointEntity entity) {
		return pointService.save(entity);
	}

	@GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Lists all points of interests stored in database.", response = ResponseEntity.class)
	public ResponseEntity<Object> findAll() {
		return pointService.findAll();
	}

	@GetMapping(value = "/findClosest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Lists ponits of interest stored in database that are at most dMax"
			+ " far from (xCoordinate,yCoordinate)", response = ResponseEntity.class)
	public ResponseEntity<Object> findClosestPoints(
			@ApiParam(value = "X Coordinate of reference point.") @RequestParam(value = "xCoordinate") Long xCoordinate,
			@ApiParam(value = "Y Coordinate of reference point.") @RequestParam(value = "yCoordinate") Long yCoordinate,
			@ApiParam(value = "Maximun distance that points may be from reference point.") @RequestParam(value = "dMax") Double dMax) {
		return pointService.findClosestPoints(xCoordinate, yCoordinate, dMax);
	}

}
