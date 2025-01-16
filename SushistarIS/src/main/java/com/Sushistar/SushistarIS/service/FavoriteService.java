package com.Sushistar.SushistarIS.service;

import com.Sushistar.SushistarIS.model.Cart;
import com.Sushistar.SushistarIS.model.FavoriteProducts;
import com.Sushistar.SushistarIS.model.Product;
import com.Sushistar.SushistarIS.model.SushistarUser;
import com.Sushistar.SushistarIS.repo.CartRepo;
import com.Sushistar.SushistarIS.repo.FavoriteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    private final FavoriteRepo favoriteRepo;

    @Autowired
    public FavoriteService(FavoriteRepo favoriteRepo) {
        this.favoriteRepo = favoriteRepo;
    }


    public FavoriteProducts getFavoriteByUserId(Long userId) {
        return favoriteRepo.findByUser(new SushistarUser(userId))
                .orElseThrow(() -> new IllegalArgumentException("Favorites not found for user ID: " + userId));
    }


    public Optional<FavoriteProducts> viewFavorite(SushistarUser user) {
        return favoriteRepo.findByUser(user);
    }


    public FavoriteProducts addToFavorite(Long userId, Product product) {
        FavoriteProducts favoriteProducts = favoriteRepo.findByUser(new SushistarUser(userId)).orElseGet(() -> {
            FavoriteProducts newFavoriteProducts = new FavoriteProducts();
            newFavoriteProducts.setUser(new SushistarUser(userId));
            newFavoriteProducts.setProducts(new ArrayList<>());
            return newFavoriteProducts;
        });
        favoriteProducts.getProducts().add(product);
        return favoriteRepo.save(favoriteProducts);
    }


    public FavoriteProducts removeFromFavorite(Long userId, Product productToRemove) {

        FavoriteProducts favoriteProducts = favoriteRepo.findByUserId(userId); // Recupera i preferiti dell'utente usando l'userId

        if (favoriteProducts == null) {
            throw new IllegalArgumentException("Preferiti non trovati per l'utente con ID: " + userId);
        }

        List<Product> products = favoriteProducts.getProducts(); // Ottieni la lista di prodotti preferiti

        // Rimuovi il prodotto specificato
        if (!products.remove(productToRemove)) {
            throw new IllegalArgumentException("Prodotto non trovato nei preferiti dell'utente con ID: " + userId);
        }

        favoriteProducts.setProducts(products); // Aggiorna la lista di prodotti nei preferiti

        return favoriteRepo.save(favoriteProducts); // Salva i preferiti aggiornati nel repository
    }

}
