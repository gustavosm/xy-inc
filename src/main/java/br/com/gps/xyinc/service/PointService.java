package br.com.gps.xyinc.service;

import java.util.InputMismatchException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.gps.xyinc.entity.PointEntity;
import br.com.gps.xyinc.exception.InvalidPointException;
import br.com.gps.xyinc.repository.PointRepository;
import br.com.gps.xyinc.utils.ValidationUtils;

@Service
public class PointService {

	@Autowired
	private PointRepository pointRepository;

	public ResponseEntity<String> save(PointEntity entity) {
		try {
			ValidationUtils.check(entity);
			entity = pointRepository.save(entity);
			Boolean ok = (entity.getId() != null);

			if (ok) {
				return ResponseEntity.ok("Success");
			} else {
				return ResponseEntity.badRequest().body("Cannot save point: " + entity + ". Please try again later");
			}

		} catch (InvalidPointException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An internal server error has occurred. Please try again in a few minutes.");
		}
	}

	public ResponseEntity<Object> findAll() {
		try {
			return ResponseEntity.ok(pointRepository.findAll());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	public ResponseEntity<Object> findClosestPoints(Long xCoordinate, Long yCoordinate, Double dMax) {
		try {
			ValidationUtils.isValidCoordinate("xCoordinate", xCoordinate);
			ValidationUtils.isValidCoordinate("yCoordinate", yCoordinate);
			Double maxDist = dMax * dMax;
			
			return ResponseEntity.ok(pointRepository.findClosestPoints(xCoordinate, yCoordinate, maxDist));
		} catch (InputMismatchException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
