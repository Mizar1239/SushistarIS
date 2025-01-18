package com.Sushistar.SushistarIS.service;

import com.Sushistar.SushistarIS.DTO.ProductDTO;
import com.Sushistar.SushistarIS.model.Product;
import com.Sushistar.SushistarIS.model.ProductCategory;
import com.Sushistar.SushistarIS.repo.CategoryRepo;
import com.Sushistar.SushistarIS.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    @Autowired
    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }

    public Optional<Product> findProductByName(String name) {
        return productRepo.findByProductName(name);
    }

    public List<Product> getTopProducts(int limit) {
        return productRepo.findAll(PageRequest.of(0, limit)).getContent();
    }

    public List<ProductCategory> findAllCategories() {
        return categoryRepo.findAll();
    }

    public Product addProduct(ProductDTO dto)
    {
        return productRepo.save(dto.toProduct());
    }
}
