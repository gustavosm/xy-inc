package br.com.gps.xyinc.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@Entity
@Table(name = "tb_point")
public class PointEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    @ApiModelProperty(notes = "ID gerado pela base de dados")
	private Long id;

	@Column(name = "nm_poi")
    @ApiModelProperty(notes = "Nome atribuido ao POI (Point Of Interest)", required = true)
	private String poiName;

	@Column(name = "x_coordinate")
    @ApiModelProperty(notes = "Coordenada X do ponto em que o POI está localizado", required = true, dataType = "long" )
	private Long xCoordinate;

	@Column(name = "y_coordinate")
    @ApiModelProperty(notes = "Coordenada Y do ponto em que o POI está localizado", required = true, dataType = "long")
	private Long yCoordinate;

	public PointEntity() {

	}

	public PointEntity(String poiName, Long xCoordinate, Long yCoordinate) {
		this.poiName = poiName;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPoiName() {
		return poiName;
	}

	public void setPoiName(String poiName) {
		this.poiName = poiName;
	}

	public Long getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(Long xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public Long getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(Long yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PointEntity other = (PointEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public boolean equalsInFields(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PointEntity other = (PointEntity) obj;
		if (poiName == null) {
			if (other.poiName != null)
				return false;
		} else if (!poiName.equals(other.poiName))
			return false;
		if (xCoordinate == null) {
			if (other.xCoordinate != null)
				return false;
		} else if (!xCoordinate.equals(other.xCoordinate))
			return false;
		if (yCoordinate == null) {
			if (other.yCoordinate != null)
				return false;
		} else if (!yCoordinate.equals(other.yCoordinate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PointEntity [poiName=" + poiName + ", xCoordinate=" + xCoordinate + ", yCoordinate=" + yCoordinate
				+ "]";
	}

}
