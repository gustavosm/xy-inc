package br.com.gps.xyinc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.gps.xyinc.entity.PointEntity;

@Repository
public interface PointRepository extends CrudRepository<PointEntity, Long> {

	/**
	 * 
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param maxDist
	 * 
	 * Pela distancia euclidiana tem-se que:
	 * 	d = sqrt((x - x0) * (x - x0) + (y - y0) * (y - y0))
	 * Desta forma, podemos evitar o calculo da raiz, elevando a expressão ao quadrado:
	 *	maxDist = d^2 = (x - x0) * (x - x0) + (y - y0) * (y - y0)
	 * Neste caso maxDist vale o quadrado do numero passado para o serviço
	 * Recupera os pontos que estao no maximo a sqrt(maxDist) de distancia em relacao ao ponto (xCoordinate, yCoordinate)
	 * @return
	 */
	@Query(value = "select * from tb_point point where ((point.x_coordinate - :xCoordinate) * (point.x_coordinate - :xCoordinate)"
			+ " + (point.y_coordinate - :yCoordinate) * (point.y_coordinate - :yCoordinate)) <= :maxDist ", nativeQuery = true)
	public List<PointEntity> findClosestPoints(@Param("xCoordinate") Long xCoordinate,
			@Param("yCoordinate") Long yCoordinate, @Param("maxDist") Double maxDist);
}
