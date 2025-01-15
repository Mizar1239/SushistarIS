package com.Sushistar.SpringDbMockup.SushistarSpringDatabase.model;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "product_category")
public class ProductCategory implements Serializable
{
	public ProductCategory() {}

	public ProductCategory(Integer id)
	{
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "categoryName", length = 20)
	private String categoryName;
}
