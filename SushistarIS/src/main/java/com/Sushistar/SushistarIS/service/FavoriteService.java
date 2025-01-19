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
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }

        FavoriteProducts favoriteProducts = favoriteRepo.findByUser(new SushistarUser(userId)).orElseGet(() -> {
            FavoriteProducts newFavoriteProducts = new FavoriteProducts();
            newFavoriteProducts.setUser(new SushistarUser(userId));
            newFavoriteProducts.setProducts(new ArrayList<>());
            return newFavoriteProducts;
        });

        // Evita duplicati
        if (!favoriteProducts.getProducts().contains(product)) {
            favoriteProducts.getProducts().add(product);
        }

        return favoriteRepo.save(favoriteProducts);
    }


    public FavoriteProducts removeFromFavorite(Long userId, Product product) {
        // Trova i preferiti dell'utente
        Optional<FavoriteProducts> optionalFavorites = Optional.ofNullable(favoriteRepo.findByUserId(userId));

        if (optionalFavorites.isPresent()) {
            FavoriteProducts favoriteProducts = optionalFavorites.get();

            // Verifica se il prodotto esiste nella lista dei preferiti
            if (favoriteProducts.getProducts().contains(product)) {
                favoriteProducts.getProducts().remove(product);
                return favoriteRepo.save(favoriteProducts); // Salva i preferiti aggiornati
            } else {
                throw new IllegalArgumentException(
                        "Prodotto non trovato nei preferiti dell'utente con ID: " + userId
                );
            }
        } else {
            throw new IllegalArgumentException("Nessuna lista di preferiti trovata per l'utente con ID: " + userId);
        }
    }


}
