// Shipment.model
package com.Sushistar.SushistarIS.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shipment_metadata")
public class Shipment implements Serializable {

	public Shipment() {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "shipment_address", length = 200, nullable = false)
	private String shipmentAddress;

	@Temporal(TemporalType.DATE)
	@Column(name = "shipment_date", nullable = false)
	private Date shipmentDate;

	@ManyToOne
	@JoinColumn(name = "shipment_status", nullable = false)
	private ShipmentStatus shipmentStatus;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private SushistarUser user;

	@OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
	private List<ShippedProduct> shippedProducts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<ShippedProduct> getShippedProducts() {
		return shippedProducts;
	}

	public void setShippedProducts(List<ShippedProduct> shippedProducts) {
		this.shippedProducts = shippedProducts;
	}
}
