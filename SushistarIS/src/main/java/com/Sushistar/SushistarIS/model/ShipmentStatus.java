package com.Sushistar.SushistarIS.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "shipment_status")
public class ShipmentStatus implements Serializable
{
	public ShipmentStatus() {}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "statusName", length = 20, nullable = false)
	private String statusName;
}