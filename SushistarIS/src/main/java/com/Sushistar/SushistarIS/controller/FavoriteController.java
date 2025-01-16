package com.Sushistar.SushistarIS.controller;

import com.Sushistar.SushistarIS.model.Cart;
import com.Sushistar.SushistarIS.model.FavoriteProducts;
import com.Sushistar.SushistarIS.model.Product;
import com.Sushistar.SushistarIS.model.SushistarUser;
import com.Sushistar.SushistarIS.service.CartService;
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
            return ResponseEntity.status(404).body("Cart not found.");
        }
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<?> addToFavorite(@PathVariable Long userId, @RequestBody Product product) {
        try {
            FavoriteProducts updatedFavorite = favoriteService.addToFavorite(userId, product);
            return ResponseEntity.ok(updatedFavorite);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding product to cart: " + e.getMessage());
        }
    }

    @PostMapping("/{userId}/remove")
    public ResponseEntity<?> removeFromFavorite(@PathVariable Long userId, @RequestBody Product product) {
        try {
            FavoriteProducts favoriteProducts = favoriteService.getFavoriteByUserId(userId);
            FavoriteProducts removed = favoriteService.removeFromFavorite(userId, product);

            if (removed != null) {
                return ResponseEntity.ok(favoriteProducts);
            } else {
                return ResponseEntity.status(404).body("Product not found in cart.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error removing product from cart: " + e.getMessage());
        }
    }
}
