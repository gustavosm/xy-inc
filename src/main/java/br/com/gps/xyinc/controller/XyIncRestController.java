package br.com.gps.xyinc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gps.xyinc.entity.PointEntity;
import br.com.gps.xyinc.service.PointService;

@RestController
@RequestMapping("/point")
public class XyIncRestController {

	@Autowired
	private PointService pointService;

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> save(@RequestBody PointEntity entity) {
		return pointService.save(entity);
	}
	
	@GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> findAll() {
		return pointService.findAll();
	}

	@GetMapping(value = "/findClosest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> findClosestPoints(@RequestParam(value = "xCoordinate") Long xCoordinate,
			@RequestParam(value = "yCoordinate") Long yCoordinate, @RequestParam(value = "dMax") Double dMax) {
		return pointService.findClosestPoints(xCoordinate, yCoordinate, dMax);
	}
}
