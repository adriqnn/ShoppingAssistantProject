package com.example.shoppingassistant.model.service;

import com.example.shoppingassistant.model.entity.enums.ProductCategoryEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductServiceModel {

    private Long id;
    private String name;
    private BigDecimal price;
    private LocalDate neededBefore;
    private ProductCategoryEnum productCategory;

    public ProductServiceModel() {
    }

    public ProductServiceModel(Long id, String name, BigDecimal price, LocalDate neededBefore, ProductCategoryEnum productCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.neededBefore = neededBefore;
        this.productCategory = productCategory;
    }

    public Long getId() {
        return id;
    }

    public ProductServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDate getNeededBefore() {
        return neededBefore;
    }

    public ProductServiceModel setNeededBefore(LocalDate neededBefore) {
        this.neededBefore = neededBefore;
        return this;
    }

    public ProductCategoryEnum getProductCategory() {
        return productCategory;
    }

    public ProductServiceModel setProductCategory(ProductCategoryEnum productCategory) {
        this.productCategory = productCategory;
        return this;
    }
}
