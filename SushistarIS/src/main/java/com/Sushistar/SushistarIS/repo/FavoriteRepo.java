package com.Sushistar.SushistarIS.repo;

import com.Sushistar.SushistarIS.model.FavoriteProducts;
import com.Sushistar.SushistarIS.model.SushistarUser;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepo extends JpaRepository<FavoriteProducts, Long> {
    // Optional<FavoriteProducts> findByProductId(Long productId);
    FavoriteProducts findByUserId(Long userId);
    Optional<FavoriteProducts> findByUser(SushistarUser user);
}
