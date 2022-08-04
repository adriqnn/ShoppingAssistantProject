package com.example.shoppingassistant.model.view;

public class ActiveDiscountViewModel {

    private Long id;
    private String discountType;
    private int discountValue;
    private String picture;
    private String description;

    public ActiveDiscountViewModel(Long id, String discountType, int discountValue, String picture, String description) {
        this.id = id;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.picture = picture;
        this.description = description;
    }

    public ActiveDiscountViewModel() {
    }

    public Long getId() {
        return id;
    }

    public ActiveDiscountViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDiscountType() {
        return discountType;
    }

    public ActiveDiscountViewModel setDiscountType(String discountType) {
        this.discountType = discountType;
        return this;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public ActiveDiscountViewModel setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public ActiveDiscountViewModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ActiveDiscountViewModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
