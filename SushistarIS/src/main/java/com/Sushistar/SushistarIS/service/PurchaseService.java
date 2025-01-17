package com.Sushistar.SushistarIS.service;

import com.Sushistar.SushistarIS.model.*;
import com.Sushistar.SushistarIS.repo.PurchaseRepo;
import com.Sushistar.SushistarIS.repo.ShipmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PurchaseService{

    @Autowired
    private PurchaseRepo purchaseRepository;

    @Autowired
    private ShipmentRepo shipmentRepository;

    @Transactional
    public void processPurchase(Purchase purchase) {
        purchase.setPurchaseDate(new Date());
        purchaseRepository.save(purchase);

        // Validate the card number
        String cardNumber = purchase.getUser().getCardNumber();
        if (cardNumber == null || cardNumber.length() != 16) {
            throw new RuntimeException("Numero di carta troppo corto!");  // Specific error for short card number
        }

        // Validate CVV
        String cvv = purchase.getUser().getCvv();
        if (cvv == null || cvv.length() != 3) {
            throw new RuntimeException("CVV non valido!");  // Specific error for invalid CVV
        }

        // Validate Expiration Date
        String expirationDate = purchase.getUser().getExpirationDate();
        if (expirationDate == null || !expirationDate.matches("\\d{2}/\\d{2}")) {
            throw new RuntimeException("Data di scadenza non valida!");  // Validating expiration date format
        }

        // Validate card owner
        String cardOwner = purchase.getUser().getOwnerName();
        if (cardOwner == null || cardOwner.matches(".*\\d.*")) {
            throw new RuntimeException("Proprietario Carta non valido!");  // Card owner shouldn't contain digits
        }

        // Validate address
        String address = purchase.getUser().getAddress();
        if (address == null || !address.matches("^[a-zA-Z0-9, ]+$")) {
            throw new RuntimeException("Indirizzo di consegna non valido!");  // Validating address for alphanumeric characters
        }

        // Validate CAP (Postal Code)
        String cap = purchase.getUser().getCap();
        if (cap == null || cap.length() != 5 || !cap.matches("\\d{5}")) {
            throw new RuntimeException("CAP non valido: deve contenere 5 caratteri numerici");
        }

        // Shipment logic (use UUID for ID)
        Shipment shipment = new Shipment();
        shipment.setId(System.currentTimeMillis());
        shipment.setShipmentAddress(address); // Use valid address
        shipment.setShipmentDate(new Date());
        shipment.setUser(purchase.getUser());

        ShipmentStatus status = new ShipmentStatus();
        status.setId(1); // Example status ID
        shipment.setShipmentStatus(status);

        shipment.setShippedProducts(purchase.getPurchasedProducts().stream().map(pp -> {
            ShippedProduct shippedProduct = new ShippedProduct();
            shippedProduct.setProductName(pp.getProduct().getName());
            shippedProduct.setQuantity(pp.getQuantity());
            shippedProduct.setPrice(pp.getPrice());
            shippedProduct.setProduct(pp.getProduct());
            shippedProduct.setShipment(shipment);
            return shippedProduct;
        }).collect(Collectors.toList()));

        shipmentRepository.save(shipment);
    }


}