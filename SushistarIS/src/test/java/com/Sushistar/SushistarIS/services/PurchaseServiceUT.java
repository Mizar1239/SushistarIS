package com.Sushistar.SushistarIS.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.Sushistar.SushistarIS.model.*;
import com.Sushistar.SushistarIS.repo.PurchaseRepo;
import com.Sushistar.SushistarIS.repo.ShipmentRepo;
import com.Sushistar.SushistarIS.service.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

class PurchaseServiceUT {

    @Mock
    private PurchaseRepo purchaseRepository;

    @Mock
    private ShipmentRepo shipmentRepository;

    @InjectMocks
    private PurchaseService purchaseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_TC_UC2_1_InvalidCardNumber() {
        // NumeroCarta troppo corto
        SushistarUser user = createValidUser();
        user.setCardNumber("402360123456"); // Invalid card number

        Purchase purchase = createValidPurchase(user);

        Exception exception = assertThrows(RuntimeException.class, () -> purchaseService.processPurchase(purchase));
        assertEquals("Numero di carta troppo corto!", exception.getMessage());
    }

    @Test
    void test_TC_UC2_2_InvalidCVV() {
        // CVV troppo lungo
        SushistarUser user = createValidUser();
        user.setCvv("12345"); // Invalid CVV

        Purchase purchase = createValidPurchase(user);

        Exception exception = assertThrows(RuntimeException.class, () -> purchaseService.processPurchase(purchase));
        assertEquals("CVV non valido!", exception.getMessage());
    }

    @Test
    void test_TC_UC2_3_InvalidExpirationDate() {
        // Data scadenza non valida
        SushistarUser user = createValidUser();
        user.setExpirationDate("13/28"); // Invalid expiration date

        Purchase purchase = createValidPurchase(user);

        Exception exception = assertThrows(RuntimeException.class, () -> purchaseService.processPurchase(purchase));
        assertEquals("Data di scadenza non valida!", exception.getMessage());
    }

    @Test
    void test_TC_UC2_4_InvalidCardOwner() {
        // Proprietario Carta contiene numeri
        SushistarUser user = createValidUser();
        user.setOwnerName("Giuseppe13"); // Invalid card owner name

        Purchase purchase = createValidPurchase(user);

        Exception exception = assertThrows(RuntimeException.class, () -> purchaseService.processPurchase(purchase));
        assertEquals("Proprietario Carta non valido!", exception.getMessage());
    }

    @Test
    void test_TC_UC2_5_InvalidAddress() {
        // Via contiene caratteri non validi
        SushistarUser user = createValidUser();
        user.setAddress("Via Roma_3"); // Invalid address

        Purchase purchase = createValidPurchase(user);

        Exception exception = assertThrows(RuntimeException.class, () -> purchaseService.processPurchase(purchase));
        assertEquals("Indirizzo di consegna non valido!", exception.getMessage());
    }

    @Test
    void test_TC_UC2_6_InvalidCAP() {
        // CAP troppo corto
        SushistarUser user = createValidUser();
        user.setCap("841"); // Invalid CAP

        Purchase purchase = createValidPurchase(user);

        Exception exception = assertThrows(RuntimeException.class, () -> purchaseService.processPurchase(purchase));
        assertEquals("CAP non valido: deve contenere 5 caratteri numerici", exception.getMessage());
    }

    @Test
    void test_TC_UC2_7_ValidData() {
        // Tutti i dati validi
        SushistarUser user = createValidUser();
        Purchase purchase = createValidPurchase(user);

        assertDoesNotThrow(() -> purchaseService.processPurchase(purchase));

        verify(purchaseRepository, times(1)).save(purchase);
        verify(shipmentRepository, times(1)).save(any(Shipment.class));
    }

    private SushistarUser createValidUser() {
        SushistarUser user = new SushistarUser();
        user.setCardNumber("4023601234567890");
        user.setCvv("123");
        user.setExpirationDate("12/28");
        user.setOwnerName("Giuseppe Rossi");
        user.setAddress("Via Roma, 3");
        user.setCap("84135");
        return user;
    }

    private Purchase createValidPurchase(SushistarUser user) {
        Product product = new Product();
        product.setProductName("Sushi Roll");

        PurchasedProduct purchasedProduct = new PurchasedProduct();
        purchasedProduct.setProduct(product);
        purchasedProduct.setQuantity(2);
        purchasedProduct.setPrice(20.0);

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setPurchasedProducts(Collections.singletonList(purchasedProduct));

        return purchase;
    }
}