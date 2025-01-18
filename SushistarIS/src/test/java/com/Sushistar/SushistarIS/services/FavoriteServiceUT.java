package com.Sushistar.SushistarIS.services;

import com.Sushistar.SushistarIS.model.FavoriteProducts;
import com.Sushistar.SushistarIS.model.Product;
import com.Sushistar.SushistarIS.model.SushistarUser;
import com.Sushistar.SushistarIS.repo.FavoriteRepo;
import com.Sushistar.SushistarIS.service.FavoriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FavoriteServiceUT {

    @Mock
    private FavoriteRepo favoriteRepo;

    private FavoriteService favoriteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        favoriteService = new FavoriteService(favoriteRepo);
    }

    @Test
    public void testAddToFavorite_UserAuthenticated_ProductAdded() {
        // Test Frame: TC_UC4_1 (Utente autenticato, prodotto non presente nei preferiti)
        Long userId = 1L;
        Product product = new Product();
        product.setId(100L);

        FavoriteProducts favoriteProducts = new FavoriteProducts();
        favoriteProducts.setUser(new SushistarUser(userId));
        favoriteProducts.setProducts(new ArrayList<>());

        when(favoriteRepo.findByUser(any(SushistarUser.class))).thenReturn(Optional.of(favoriteProducts));
        when(favoriteRepo.save(any(FavoriteProducts.class))).thenReturn(favoriteProducts);

        FavoriteProducts updatedFavorites = favoriteService.addToFavorite(userId, product);

        assertNotNull(updatedFavorites);
        assertTrue(updatedFavorites.getProducts().contains(product));
        verify(favoriteRepo, times(1)).save(favoriteProducts);
    }

    @Test
    public void testAddToFavorite_ProductAlreadyInFavorites() {
        Long userId = 1L;
        Product product = new Product();
        product.setId(100L);

        FavoriteProducts favoriteProducts = new FavoriteProducts();
        favoriteProducts.setUser(new SushistarUser(userId));
        favoriteProducts.setProducts(new ArrayList<>(Collections.singletonList(product)));

        when(favoriteRepo.findByUser(any(SushistarUser.class))).thenReturn(Optional.of(favoriteProducts));
        when(favoriteRepo.save(any(FavoriteProducts.class))).thenAnswer(invocation -> invocation.getArgument(0)); // Mock save

        FavoriteProducts updatedFavorites = favoriteService.addToFavorite(userId, product);

        assertEquals(1, updatedFavorites.getProducts().size());
        assertTrue(updatedFavorites.getProducts().contains(product));
        verify(favoriteRepo, times(1)).save(favoriteProducts);
    }


    @Test
    public void testAddToFavorite_UserNotAuthenticated() {
        Long userId = null; // Simula un utente non autenticato
        Product product = new Product();
        product.setId(100L);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            favoriteService.addToFavorite(userId, product);
        });

        assertEquals("User ID cannot be null", exception.getMessage());
        verify(favoriteRepo, never()).save(any(FavoriteProducts.class));
    }


    @Test
    public void testRemoveFromFavorite_ProductNotInFavorites() {
        // Test Frame: TC_UC4_4 (Rimuovi un prodotto non presente nei preferiti)
        Long userId = 1L;
        Product product = new Product();
        product.setId(100L);

        FavoriteProducts favoriteProducts = new FavoriteProducts();
        favoriteProducts.setUser(new SushistarUser(userId));
        favoriteProducts.setProducts(new ArrayList<>());

        when(favoriteRepo.findByUserId(userId)).thenReturn(favoriteProducts);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            favoriteService.removeFromFavorite(userId, product);
        });

        assertEquals("Prodotto non trovato nei preferiti dell'utente con ID: 1", exception.getMessage());
        verify(favoriteRepo, never()).save(any(FavoriteProducts.class));
    }

    @Test
    public void testRemoveFromFavorite_ProductSuccessfullyRemoved() {
        // Test aggiuntivo: Verifica la rimozione corretta del prodotto
        Long userId = 1L;
        Product product = new Product();
        product.setId(100L);

        FavoriteProducts favoriteProducts = new FavoriteProducts();
        favoriteProducts.setUser(new SushistarUser(userId));
        favoriteProducts.setProducts(new ArrayList<>(Collections.singletonList(product)));

        when(favoriteRepo.findByUserId(userId)).thenReturn(favoriteProducts);
        when(favoriteRepo.save(any(FavoriteProducts.class))).thenReturn(favoriteProducts);

        FavoriteProducts updatedFavorites = favoriteService.removeFromFavorite(userId, product);

        assertFalse(updatedFavorites.getProducts().contains(product));
        verify(favoriteRepo, times(1)).save(favoriteProducts);
    }
}
