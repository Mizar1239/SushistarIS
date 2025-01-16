package com.Sushistar.SushistarIS.repo;

import com.Sushistar.SushistarIS.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<ProductCategory, Long> {
}