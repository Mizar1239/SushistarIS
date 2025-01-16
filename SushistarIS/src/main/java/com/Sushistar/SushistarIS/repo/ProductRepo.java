package com.Sushistar.SushistarIS.repo;

import com.Sushistar.SushistarIS.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findByid(Long id);
    Optional<Product> findByProductName(String name);
}
