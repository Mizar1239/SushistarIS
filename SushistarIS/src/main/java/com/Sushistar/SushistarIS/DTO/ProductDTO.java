package com.Sushistar.SushistarIS.DTO;

import com.Sushistar.SushistarIS.model.Product;
import com.Sushistar.SushistarIS.model.ProductCategory;

import java.io.Serializable;

public class ProductDTO implements Serializable
{
	public ProductDTO() {}

	public Product toProduct()
	{
		Product product = new Product();
		product.setProductName(this.productName);
		product.setDescription(this.description);
		product.setCategory(new ProductCategory(this.categoryId));
		product.setAmount(this.amount);
		product.setPrice(this.price);
		product.setImgPath(this.imgPath);

		return product;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	private String productName;
	private String description;
	private Double price;
	private int amount;
	private int categoryId;
	private String imgPath;
}
