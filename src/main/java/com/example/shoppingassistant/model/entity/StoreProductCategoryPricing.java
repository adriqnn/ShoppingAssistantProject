package com.example.shoppingassistant.model.entity;

import com.example.shoppingassistant.model.entity.enums.ProductCategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "store_product_category_pricing")
public class StoreProductCategoryPricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "pricing_category", nullable = false)
    private ProductCategoryEnum pricingCategory;

    @Column(name = "discount_percentage", nullable = false)
    private int discountPercentage;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    public StoreProductCategoryPricing() {
    }

    public StoreProductCategoryPricing(ProductCategoryEnum pricingCategory, int discountPercentage, String description) {
        this.pricingCategory = pricingCategory;
        this.discountPercentage = discountPercentage;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public StoreProductCategoryPricing setId(Long id) {
        this.id = id;
        return this;
    }

    public ProductCategoryEnum getPricingCategory() {
        return pricingCategory;
    }

    public StoreProductCategoryPricing setPricingCategory(ProductCategoryEnum pricingCategory) {
        this.pricingCategory = pricingCategory;
        return this;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public StoreProductCategoryPricing setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StoreProductCategoryPricing setDescription(String description) {
        this.description = description;
        return this;
    }
}
