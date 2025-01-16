package com.Sushistar.SushistarIS.controller;


import com.Sushistar.SushistarIS.model.Product;
import com.Sushistar.SushistarIS.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<Optional<Product>> getProductByName(@PathVariable("name") String name) {
        Optional<Product> product = productService.findProductByName(name);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
