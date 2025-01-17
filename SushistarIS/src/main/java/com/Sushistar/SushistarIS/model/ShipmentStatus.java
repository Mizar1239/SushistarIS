// ShipmentStatus.model
package com.Sushistar.SushistarIS.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shipment_status")
public class ShipmentStatus implements Serializable {

	public ShipmentStatus() {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "status_name", length = 20, nullable = false)
	private String statusName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
