package com.Sushistar.SushistarIS.service;

import com.Sushistar.SushistarIS.model.Product;
import com.Sushistar.SushistarIS.model.SushistarUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Sushistar.SushistarIS.repo.CartRepo;
import com.Sushistar.SushistarIS.model.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepo cartRepo;

    @Autowired
    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    public Cart getCartByUserId(Long userId) {
        return cartRepo.findByUser(new SushistarUser(userId))
                .orElseThrow(() -> new IllegalArgumentException("Cart not found for user ID: " + userId));
    }

    public Optional<Cart> viewCart(SushistarUser user) {
        return cartRepo.findByUser(user);
    }

    public Cart addToCart(Long userId, Product product) {
        Cart cart = cartRepo.findByUser(new SushistarUser(userId)).orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUser(new SushistarUser(userId));
            newCart.setProducts(new ArrayList<>());
            return newCart;
        });
        cart.getProducts().add(product);
        return cartRepo.save(cart);
    }

    public Cart removeFromCart(Long userId, Product productToRemove) {

        Cart cart = cartRepo.findByUserId(userId); // Recupera il carrello dell'utente usando l'userId

        if (cart == null) {
            throw new IllegalArgumentException("Carrello non trovato per l'utente con ID: " + userId);
        }

        List<Product> products = cart.getProducts(); // Ottieni la lista di prodotti dal carrello

        // Rimuovi il prodotto specificato
        if (!products.remove(productToRemove)) {
            throw new IllegalArgumentException("Prodotto non trovato nel carrello dell'utente con ID: " + userId);
        }

        cart.setProducts(products); // Aggiorna la lista di prodotti nel carrello

        // Salva il carrello aggiornato nel repository
        return cartRepo.save(cart);
    }
}

