package com.example.shoppingassistant.model.entity;

import com.example.shoppingassistant.model.entity.enums.ProductCategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "product_categories")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private ProductCategoryEnum productCategory;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    public ProductCategory() {
    }

    public ProductCategory(ProductCategoryEnum productCategory, String description) {
        this.productCategory = productCategory;
        this.description = description;
    }

    public ProductCategory(Long id, ProductCategoryEnum productCategory, String description) {
        this.id = id;
        this.productCategory = productCategory;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public ProductCategory setId(Long id) {
        this.id = id;
        return this;
    }

    public ProductCategoryEnum getProductCategory() {
        return productCategory;
    }

    public ProductCategory setProductCategory(ProductCategoryEnum productCategory) {
        this.productCategory = productCategory;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductCategory setDescription(String description) {
        this.description = description;
        return this;
    }


}
