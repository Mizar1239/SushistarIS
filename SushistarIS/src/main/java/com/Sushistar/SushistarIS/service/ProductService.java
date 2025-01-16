package com.Sushistar.SushistarIS.service;

import com.Sushistar.SushistarIS.model.Product;
import com.Sushistar.SushistarIS.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }

    public Optional<Product> findProductByName(String name) {
        return productRepo.findByProductName(name);
    }
}
