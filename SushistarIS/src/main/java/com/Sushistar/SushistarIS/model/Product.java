package com.Sushistar.SushistarIS.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "product")
public class Product implements Serializable
{
	public Product() {}

	public void setId(Long id) {
		this.id = id;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setImgPath(String imgPath) { this.imgPath = imgPath; }
	public void setPrice(Double price) {
		this.price = price;
	}

	public ProductCategory getCategory() {
		return category;
	}
	public Long getId() {
		return id;
	}
	public String getProductName() {
		return productName;
	}
	public String getDescription() {
		return description;
	}
	public Double getPrice() {
		return price;
	}
	public int getAmount() {
		return amount;
	}
	public String getImgPath() { return imgPath; }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "productName", length = 30, nullable = false)
	private String productName;

	@Column(nullable = false)
	private Double price;

	@Column(nullable = false, length = 500)
	private String description;

	@Column(nullable = false)
	private int amount;

	@Column(nullable = false)
	private String imgPath;

	@ManyToOne
	@JoinColumn(name = "product_category", nullable = false)
	private ProductCategory category;
}