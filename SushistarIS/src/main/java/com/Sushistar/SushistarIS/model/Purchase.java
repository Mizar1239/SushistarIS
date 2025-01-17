package com.Sushistar.SushistarIS.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shipment_metadata")
public class Purchase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shipment_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date purchaseDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private SushistarUser user;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<PurchasedProduct> purchasedProducts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public SushistarUser getUser() {
        return user;
    }

    public void setUser(SushistarUser user) {
        this.user = user;
    }

    public List<PurchasedProduct> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(List<PurchasedProduct> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }
}