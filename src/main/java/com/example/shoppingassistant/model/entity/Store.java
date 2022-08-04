package com.example.shoppingassistant.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "store_logo", nullable = false)
    private String storeLogo;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<StoreProductCategoryPricing> pricingList;

    public Store() {
        this.pricingList = new ArrayList<>();
    }

    public Store(Long id, String name, String description, String storeLogo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.storeLogo = storeLogo;
        this.pricingList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Store setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Store setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Store setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public Store setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
        return this;
    }

    public List<StoreProductCategoryPricing> getPricingList() {
        return pricingList;
    }

    public Store setPricingList(List<StoreProductCategoryPricing> pricingList) {
        this.pricingList = pricingList;
        return this;
    }
}
