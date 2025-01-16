package com.Sushistar.SushistarIS.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Chiave esterna che punta a User
    private SushistarUser user;

    @ManyToMany
    @JoinTable(
            name = "cart_product", // Nome della tabella di giunzione
            joinColumns = @JoinColumn(name = "cart_id"), // Colonna di giunzione per Cart
            inverseJoinColumns = @JoinColumn(name = "product_id") // Colonna di giunzione per Product
    )
    private List<Product> products = new ArrayList<>(); // Inizializza la lista

    // Costruttore vuoto richiesto da JPA
    public Cart() {
    }

    // Costruttore parametrizzato
    public Cart(SushistarUser user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    // Getter e Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SushistarUser getUser() {
        return user;
    }

    public void setUser(SushistarUser user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

