package com.Sushistar.SushistarIS.services;

import com.Sushistar.SushistarIS.model.Product;
import com.Sushistar.SushistarIS.repo.CategoryRepo;
import com.Sushistar.SushistarIS.repo.ProductRepo;
import com.Sushistar.SushistarIS.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceUT {

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        // Mock del repository
        productRepo = mock(ProductRepo.class);

        // Inizializzazione del service con il mock
        productService = new ProductService(productRepo, categoryRepo);
    }

    @Test
    void testFindProductByName_UC1_01() {
        // Arrange
        Product product = new Product();
        product.setProductName("Tonkatsu");
        when(productRepo.findByProductName("Tonkatsu")).thenReturn(Optional.of(product));

        // Act
        Optional<Product> result = productService.findProductByName("Tonkatsu");

        // Assert
        assertTrue(result.isPresent(), "Il prodotto dovrebbe essere presente.");
        assertEquals("Tonkatsu", result.get().getProductName(), "Il nome del prodotto non corrisponde.");
    }

    @Test
    void testFindProductByName_UC1_02() {
        when(productRepo.findByProductName("Toncatsu")).thenReturn(null);

        Optional<Product> result = productService.findProductByName("Toncatsu");

        assertNull(result);
    }

}
