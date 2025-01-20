package com.Sushistar.SushistarIS.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "favorite_products")
public class FavoriteProducts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Chiave esterna che punta a User
    private SushistarUser user;

    @ManyToMany
    @JoinTable(
            name = "favorite_products_mapping", // Nome della tabella di giunzione
            joinColumns = @JoinColumn(name = "favorite_id"), // Colonna di giunzione per FavoriteProducts
            inverseJoinColumns = @JoinColumn(name = "product_id") // Colonna di giunzione per Product
    )
    private List<Product> products = new ArrayList<>();


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public FavoriteProducts() {
    }

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
}