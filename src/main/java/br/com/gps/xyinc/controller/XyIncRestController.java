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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/point")
public class XyIncRestController {

	@Autowired
	private PointService pointService;

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Salva um ponto de interesse na base de dados.", response = ResponseEntity.class)
	public ResponseEntity<Object> save(
			@ApiParam(value = "Objeto a ser enviado no corpo da requisição.") @RequestBody PointEntity entity) {
		return pointService.save(entity);
	}

	@GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Lista todos os pontos de interesse cadastrados na base.", response = ResponseEntity.class)
	public ResponseEntity<Object> findAll() {
		return pointService.findAll();
	}

	@GetMapping(value = "/findClosest", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Lista os pontos de interesse cadastrados na base que estão a no maximo dMax"
			+ " de distancia de (xCoordinate,yCoordinate)", response = ResponseEntity.class)
	public ResponseEntity<Object> findClosestPoints(
			@ApiParam(value = "Coordenada X do ponto de referência.") @RequestParam(value = "xCoordinate") Long xCoordinate,
			@ApiParam(value = "Coordenada Y do ponto de referência.") @RequestParam(value = "yCoordinate") Long yCoordinate,
			@ApiParam(value = "Distância Máxima que os pontos devem estar da referência.") @RequestParam(value = "dMax") Double dMax) {
		return pointService.findClosestPoints(xCoordinate, yCoordinate, dMax);
	}
}
