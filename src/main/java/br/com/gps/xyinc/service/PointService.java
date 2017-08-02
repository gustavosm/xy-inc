package br.com.gps.xyinc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger log = LoggerFactory.getLogger(PointService.class);
	
	@Autowired
	private PointRepository pointRepository;

	public ResponseEntity<Object> save(PointEntity entity) {
		try {
			log.info("Method: save");
			ValidationUtils.check(entity);
			log.info("Saving point of interest: " + entity);
			entity = pointRepository.save(entity);

			if (entity.getId() != null) {
				log.info("Success on save: Point ID: " + entity.getId());
				return ResponseEntity.ok(entity);
			} else {
				log.error("Error on saving point: " + entity);
				return ResponseEntity.badRequest().body("Cannot save point: " + entity + ". Please try again later");
			}

		} catch (InvalidPointException e) {
			log.error("Input: " + entity + ", didn't pass through validation: " + e.toString());
			return ResponseEntity.badRequest().body(e);
		} catch (Exception e) {
			log.error("Exception thrown: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An internal server error has occurred. Please try again in a few minutes.");
		}
	}

	public ResponseEntity<Object> findAll() {
		try {
			log.info("Method: findAll");
			log.info("Getting all points on database.");
			return ResponseEntity.ok(pointRepository.findAll());
			
		} catch (Exception e) {
			log.error("Error while getting all points: " + e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	public ResponseEntity<Object> findClosestPoints(Long xCoordinate, Long yCoordinate, Double dMax) {
		try {
			log.info("Method: findClosestPoints");
			ValidationUtils.isValidCoordinate("xCoordinate", xCoordinate);
			ValidationUtils.isValidCoordinate("yCoordinate", yCoordinate);
			Double maxDist = dMax * dMax;
			
			log.info("Getting all points to a maximum distance of " + dMax + " from (" + xCoordinate + ", " + yCoordinate + ").");
			List<PointEntity> closestPoints = pointRepository.findClosestPoints(xCoordinate, yCoordinate, maxDist);

			log.info("Got points: " + closestPoints);
			return ResponseEntity.ok(closestPoints);
		} catch (InvalidPointException e) {
			log.error("Input: (" + xCoordinate + ", " + yCoordinate + "), didn't pass through validation: " + e.toString());
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			log.error("Exception thrown: " + e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	public ResponseEntity<String> remove(PointEntity entity) {
		try {
			log.info("Method: remove");
			pointRepository.delete(entity);
			log.info(entity + " removed successfully.");
			return ResponseEntity.ok("Success");
		} catch (Exception e) {
			log.error("Exception thrown: " + e.getMessage());
			return ResponseEntity.badRequest().body("Could not remove this point: " + entity + ". Reason: " + e.getMessage());
		}
	}
}
