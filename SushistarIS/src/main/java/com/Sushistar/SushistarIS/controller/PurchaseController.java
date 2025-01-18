package com.Sushistar.SushistarIS.controller;

import com.Sushistar.SushistarIS.model.Purchase;
import com.Sushistar.SushistarIS.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<String> makePurchase(@RequestBody Purchase purchase) {
        try {
            purchaseService.processPurchase(purchase);
            return ResponseEntity.ok("Purchase completed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process purchase: " + e.getMessage());
        }
    }
}
