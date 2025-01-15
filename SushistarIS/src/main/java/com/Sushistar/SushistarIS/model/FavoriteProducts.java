package com.Sushistar.SpringDbMockup.SushistarSpringDatabase.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "favorite_products")
public class FavoriteProducts implements Serializable
{
	public FavoriteProducts() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SushistarUser getUser() {
		return user;
	}

	public void setUser(SushistarUser user) {
		this.user = user;
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

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private SushistarUser user;

	@ManyToOne
	@JoinColumn(name = "productId", nullable = false)
	private Product product;
}