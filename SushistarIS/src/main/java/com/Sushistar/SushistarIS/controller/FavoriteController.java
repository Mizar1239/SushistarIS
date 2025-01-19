package com.Sushistar.SushistarIS.controller;

import com.Sushistar.SushistarIS.model.FavoriteProducts;
import com.Sushistar.SushistarIS.model.Product;
import com.Sushistar.SushistarIS.model.SushistarUser;
import com.Sushistar.SushistarIS.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/favorite_products")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<?> viewFavorite(@PathVariable Long userId) {
        Optional<FavoriteProducts> favoriteProducts = favoriteService.viewFavorite(new SushistarUser(userId));
        if (favoriteProducts.isPresent()) {
            return ResponseEntity.ok(favoriteProducts.get());
        } else {
            return ResponseEntity.status(404).body("Favorite list not found for user with ID " + userId);
        }
    }


    @PostMapping("/{userId}/add")
    public ResponseEntity<?> addToFavorite(@PathVariable Long userId, @RequestBody Product request) {
        if (request == null || request.getId() == null) {
            return ResponseEntity.badRequest().body("Invalid product data.");
        }
        try {
            FavoriteProducts updatedFavorite = favoriteService.addToFavorite(userId, request);
            return ResponseEntity.ok(updatedFavorite);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding product to favorites: " + e.getMessage());
        }
    }

    @PostMapping("/{userId}/remove")
    public ResponseEntity<?> removeFromFavorite(@PathVariable Long userId, @RequestBody Product request) {
        if (request == null || request.getId() == null) {
            return ResponseEntity.badRequest().body("Invalid product data.");
        }
        try {
            FavoriteProducts updatedFavorite = favoriteService.removeFromFavorite(userId, request);
            if (updatedFavorite != null) {
                return ResponseEntity.ok(updatedFavorite);
            } else {
                return ResponseEntity.status(404).body("Product not found in favorite list.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error removing product from favorites: " + e.getMessage());
        }
    }
}
