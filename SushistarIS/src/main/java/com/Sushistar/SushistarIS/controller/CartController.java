package com.Sushistar.SushistarIS.controller;

import com.Sushistar.SushistarIS.model.Cart;
import com.Sushistar.SushistarIS.model.Product;
import com.Sushistar.SushistarIS.model.SushistarUser;
import com.Sushistar.SushistarIS.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> viewCart(@PathVariable Long userId) {
        Optional<Cart> cart = cartService.viewCart(new SushistarUser(userId));
        if (cart.isPresent()) {
            return ResponseEntity.ok(cart.get());
        } else {
            return ResponseEntity.status(404).body("Cart not found.");
        }
    }


    @PostMapping("/{userId}/add")
    public ResponseEntity<?> addToCart(@PathVariable Long userId, @RequestBody Product product) {
        if (product == null || product.getId() == null || product.getId() <= 0) {
            return ResponseEntity.badRequest().body("Invalid product details provided.");
        }

        try {
            Cart updatedCart = cartService.addToCart(userId, product);
            return ResponseEntity.ok(updatedCart);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error adding product to cart: " + e.getMessage());
        }
    }


    @PostMapping("/{userId}/remove")
    public ResponseEntity<?> removeFromCart(@PathVariable Long userId, @RequestBody Product product) {
        try {
            Cart cart = cartService.getCartByUserId(userId);
            Cart removed = cartService.removeFromCart(userId, product);

            if (removed != null) {
                return ResponseEntity.ok(cart);
            } else {
                return ResponseEntity.status(404).body("Product not found in cart.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error removing product from cart: " + e.getMessage());
        }
    }
}

