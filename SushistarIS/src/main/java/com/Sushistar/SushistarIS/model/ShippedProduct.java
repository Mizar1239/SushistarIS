package com.Sushistar.SushistarIS.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "shipped_products")
public class ShippedProduct implements Serializable
{
	public ShippedProduct() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "product_name", length = 30, nullable = false)
	private String productName;

	@Column(nullable = false)
	private int quantity;

	@Column(nullable = false)
	private Double price;

	@ManyToOne
	@JoinColumn(name = "shipment_id", nullable = false)
	private Shipment shipment;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
}