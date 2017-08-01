package br.com.gps.xyinc.utils;

import java.util.InputMismatchException;

import org.springframework.util.StringUtils;

import br.com.gps.xyinc.entity.PointEntity;
import br.com.gps.xyinc.exception.InvalidPointException;

public class ValidationUtils {

	private static final Long LONG_ZERO = Long.valueOf(0L);

	public static void check(PointEntity entity) throws InvalidPointException {
		if (entity.getxCoordinate() == null) {
			throw new InvalidPointException(entity + " is not a valid point, because its xCoodinate is null.");
		}

		if (entity.getyCoordinate() == null) {
			throw new InvalidPointException(entity + " is not a valid point, because its yCoodinate is null.");
		}

		if (StringUtils.isEmpty(entity.getPoiName())) {
			throw new InvalidPointException(entity + " is not a valid point, because its poiName is null or empty.");
		}

		if (entity.getxCoordinate().compareTo(LONG_ZERO) < 0) {
			throw new InvalidPointException(
					entity + " is not a valid point, because its xCoordinate is less than zero.");
		}

		if (entity.getyCoordinate().compareTo(LONG_ZERO) < 0) {
			throw new InvalidPointException(
					entity + " is not a valid point, because its yCoordinate is less than zero.");
		}
	}
	
	public static void isValidCoordinate(String coordinateName, Long coordinate) throws InputMismatchException {
		if (coordinate.compareTo(LONG_ZERO) < 0) {
			throw new InputMismatchException(coordinateName + " cannot be negative.");
		}
	}
}
