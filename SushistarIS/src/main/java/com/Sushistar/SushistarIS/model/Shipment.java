package com.Sushistar.SushistarIS.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "shipment_metadata")
public class Shipment implements Serializable
{
	public Shipment() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}

	public String getShipmentAddress() {
		return shipmentAddress;
	}

	public void setShipmentAddress(String shipmentAddress) {
		this.shipmentAddress = shipmentAddress;
	}

	public Date getShipmentDate() {
		return shipmentDate;
	}

	public void setShipmentDate(Date shipmentDate) {
		this.shipmentDate = shipmentDate;
	}

	public ShipmentStatus getShipmentStatus() {
		return shipmentStatus;
	}

	public void setShipmentStatus(ShipmentStatus shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	public SushistarUser getUser() {
		return user;
	}

	public void setUser(SushistarUser user) {
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "shipmentId", length = 20, nullable = false, unique = true)
	private String shipmentId;

	@Column(name = "shipmentAddress", length = 200, nullable = false)
	private String shipmentAddress;

	@Temporal(TemporalType.DATE)
	@Column(name = "shipmentDate", nullable = false)
	private Date shipmentDate;

	@ManyToOne
	@JoinColumn(name = "shipmentStatus", nullable = false)
	private ShipmentStatus shipmentStatus;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private SushistarUser user;
}