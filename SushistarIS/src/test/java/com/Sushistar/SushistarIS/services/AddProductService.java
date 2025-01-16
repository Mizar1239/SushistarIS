package com.Sushistar.SushistarIS.services;

import com.Sushistar.SushistarIS.DTO.ProductDTO;
import com.Sushistar.SushistarIS.model.Product;
import com.Sushistar.SushistarIS.repo.CategoryRepo;
import com.Sushistar.SushistarIS.repo.ProductRepo;
import com.Sushistar.SushistarIS.service.ProductService;
import org.hamcrest.core.AnyOf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddProductService
{
	private ProductRepo mockProductRepo;
	private CategoryRepo categoryRepo;
	private ProductService productService;

	@BeforeEach
	void setUp() {
		// Mock del repository
		mockProductRepo = mock(ProductRepo.class);

		// Inizializzazione del service con il mock
		productService = new ProductService(mockProductRepo, categoryRepo);
	}

	@Test
	void testAddProduct_UC3_01() {
		// Arrange
		ProductDTO dto = new ProductDTO();
		dto.setAmount(4);
		dto.setPrice(15.99);
		dto.setProductName("Sashimi Salmone");
		dto.setDescription("Un gustoso mix di salmone");
		dto.setCategoryId(4);

		Product product = dto.toProduct();

		when(mockProductRepo.save(product)).thenReturn(product);

		// Act
		Product result = productService.addProduct(dto);

		// Assert
		assertEquals(result, product);
	}
}
