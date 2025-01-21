package com.Sushistar.SushistarIS.controller;


import com.Sushistar.SushistarIS.DTO.ProductDTO;
import com.Sushistar.SushistarIS.model.Product;
import com.Sushistar.SushistarIS.model.ProductCategory;
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
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable("name") String name) {
        Optional<Product> product = productService.findProductByName(name);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/findId/{id}")
    public ResponseEntity<Optional<Product>> getProductByName(@PathVariable("id") Long id) {
        Optional<Product> product = productService.findProductByName(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/topList")
    public List<Product> getTopProducts(@RequestParam(defaultValue = "4") int limit) {
        return productService.getTopProducts(limit);
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<ProductCategory>> getAllCategories() {
        List<ProductCategory> categories = productService.findAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO dto) {
        Product p = this.productService.addProduct(dto);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }
}
