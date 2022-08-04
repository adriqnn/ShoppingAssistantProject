package com.example.shoppingassistant.model.view;



public class StoreViewModel {

    private String name;
    private String pricingCategory;
    private int discountPercentage;

    public StoreViewModel() {
    }

    public StoreViewModel(String name, String pricingCategory, int discountPercentage) {
        this.name = name;
        this.pricingCategory = pricingCategory;
        this.discountPercentage = discountPercentage;
    }

    public String getName() {
        return name;
    }

    public StoreViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getPricingCategory() {
        return pricingCategory;
    }

    public StoreViewModel setPricingCategory(String pricingCategory) {
        this.pricingCategory = pricingCategory;
        return this;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public StoreViewModel setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
        return this;
    }
}
