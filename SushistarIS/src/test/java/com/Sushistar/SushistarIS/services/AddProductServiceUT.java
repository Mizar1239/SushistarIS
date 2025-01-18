package com.Sushistar.SushistarIS.services;

import com.Sushistar.SushistarIS.DTO.ProductDTO;
import com.Sushistar.SushistarIS.exceptions.BadProductException;
import com.Sushistar.SushistarIS.model.Product;
import com.Sushistar.SushistarIS.repo.CategoryRepo;
import com.Sushistar.SushistarIS.repo.ProductRepo;
import com.Sushistar.SushistarIS.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddProductServiceUT
{
	@Mock
	private ProductRepo mockProductRepo;
	private CategoryRepo categoryRepo;

	@InjectMocks
	private ProductService productService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		// Inizializzazione del service con il mock
		productService = new ProductService(mockProductRepo, categoryRepo);
	}

	@Test
	void testAddProduct_UC3_01_NotValidProductName() { // non è stato inserito un NomeProdotto valido
		// Arrange
		ProductDTO dto = new ProductDTO();
		dto.setProductName(null);
		dto.setDescription("Un gustoso mix di salmone");
		dto.setCategoryId(4);
		dto.setPrice(15.99);
		dto.setAmount(4);
		dto.setImgPath("sashimi_salmone.jpg");

		Product product = dto.toProduct();

		// Act
		Product result = productService.addProduct(dto);

		// Assert
		verify(mockProductRepo, times(1)).save(any(Product.class));
		assertNull(result);
	}

	@Test
	void testAddProduct_UC3_02_NotValidDescription() { // non è stato inserito un NomeProdotto valido
		// Arrange
		ProductDTO dto = new ProductDTO();
		dto.setProductName("Sashimi Salmone");
		dto.setDescription(null);
		dto.setCategoryId(4);
		dto.setPrice(15.99);
		dto.setAmount(4);
		dto.setImgPath("sashimi_salmone.jpg");

		// Act
		Product result = productService.addProduct(dto);

		// Assert
		verify(mockProductRepo, times(1)).save(any(Product.class));
		assertNull(result);
	}

	@Test
	void testAddProduct_UC3_03_NotValidCategoryId() { // non è stato inserito un NomeProdotto valido
		// Arrange
		ProductDTO dto = new ProductDTO();
		dto.setProductName("Sashimi Salmone");
		dto.setDescription(null);
		// dto.setCategoryId(4);
		dto.setPrice(15.99);
		dto.setAmount(4);
		dto.setImgPath("sashimi_salmone.jpg");

		// Act
		Product result = productService.addProduct(dto);

		// Assert
		verify(mockProductRepo, times(1)).save(any(Product.class));
		assertNull(result);
	}

	@Test
	void testAddProduct_UC3_04_NotValidPrice() { // non è stato inserito un NomeProdotto valido
		// Arrange
		ProductDTO dto = new ProductDTO();
		dto.setProductName("Sashimi Salmone");
		dto.setDescription(null);
		dto.setCategoryId(4);
		// dto.setPrice(15.99);
		dto.setAmount(4);
		dto.setImgPath("sashimi_salmone.jpg");

		// Act
		Product result = productService.addProduct(dto);

		// Assert
		verify(mockProductRepo, times(1)).save(any(Product.class));
		assertNull(result);
	}

	@Test
	void testAddProduct_UC3_05_NotValidAmount() { // non è stato inserito un NomeProdotto valido
		// Arrange
		ProductDTO dto = new ProductDTO();
		dto.setProductName("Sashimi Salmone");
		dto.setDescription(null);
		dto.setCategoryId(4);
		dto.setPrice(15.99);
		// dto.setAmount(4);
		dto.setImgPath("sashimi_salmone.jpg");

		// Act
		Product result = productService.addProduct(dto);

		// Assert
		verify(mockProductRepo, times(1)).save(any(Product.class));
		assertNull(result);
	}

	@Test
	void testAddProduct_UC3_06_NotValidImagePath() {
		// Arrange
		ProductDTO dto = new ProductDTO();
		dto.setProductName("Sashimi Salmone");
		dto.setDescription(null);
		dto.setCategoryId(4);
		dto.setPrice(15.99);
		dto.setAmount(4);
		// dto.setImgPath("sashimi_salmone.jpg");

		// Act
		Product result = productService.addProduct(dto);

		// Assert
		verify(mockProductRepo, times(1)).save(any(Product.class));
		assertNull(result);
	}

	@Test
	void testAddProduct_UC3_07_ValidProductInsert() {
		// Arrange
		ProductDTO dto = new ProductDTO();
		dto.setProductName("Sashimi Salmone");
		dto.setDescription(null);
		dto.setCategoryId(4);
		dto.setPrice(15.99);
		dto.setAmount(4);
		dto.setImgPath("sashimi_salmone.jpg");

		// Act
		Product result = productService.addProduct(dto);

		// Assert
		verify(mockProductRepo, times(1)).save(any(Product.class));
		assertNotNull(result);
	}
}
