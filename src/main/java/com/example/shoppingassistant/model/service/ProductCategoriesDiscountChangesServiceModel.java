package com.example.shoppingassistant.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCategoriesDiscountChangesServiceModel {

    private String name;
    private Integer meat;
    private Integer fish;
    private Integer dairy;
    private Integer vegetables;
    private Integer fruits;
    private Integer drinks;
    private Integer alcohol;
    private Integer nuts;
    private Integer chocolate;
    private Integer oils;
    private Integer sauces;
    private Integer sweets;
    private Integer household;
    private Integer other;

    public ProductCategoriesDiscountChangesServiceModel() {
    }

    public Map<String, Integer> getProductCategories(){
        Map<String, Integer> stringIntegerValues = new HashMap<>();

        stringIntegerValues.put("MEAT", this.meat);
        stringIntegerValues.put("FISH", this.fish);
        stringIntegerValues.put("DAIRY", this.dairy);
        stringIntegerValues.put("VEGETABLES", this.vegetables);
        stringIntegerValues.put("FRUITS", this.fruits);
        stringIntegerValues.put("DRINKS", this.drinks);
        stringIntegerValues.put("ALCOHOL", this.alcohol);
        stringIntegerValues.put("NUTS", this.nuts);
        stringIntegerValues.put("CHOCOLATE", this.chocolate);
        stringIntegerValues.put("OILS", this.oils);
        stringIntegerValues.put("SAUCES", this.sauces);
        stringIntegerValues.put("SWEETS", this.sweets);
        stringIntegerValues.put("HOUSEHOLD", this.household);
        stringIntegerValues.put("OTHER", this.other);

        return stringIntegerValues;
    }

    public String getName() {
        return name;
    }

    public ProductCategoriesDiscountChangesServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getMeat() {
        return meat;
    }

    public ProductCategoriesDiscountChangesServiceModel setMeat(Integer meat) {
        this.meat = meat;
        return this;
    }

    public Integer getFish() {
        return fish;
    }

    public ProductCategoriesDiscountChangesServiceModel setFish(Integer fish) {
        this.fish = fish;
        return this;
    }

    public Integer getDairy() {
        return dairy;
    }

    public ProductCategoriesDiscountChangesServiceModel setDairy(Integer dairy) {
        this.dairy = dairy;
        return this;
    }

    public Integer getVegetables() {
        return vegetables;
    }

    public ProductCategoriesDiscountChangesServiceModel setVegetables(Integer vegetables) {
        this.vegetables = vegetables;
        return this;
    }

    public Integer getFruits() {
        return fruits;
    }

    public ProductCategoriesDiscountChangesServiceModel setFruits(Integer fruits) {
        this.fruits = fruits;
        return this;
    }

    public Integer getDrinks() {
        return drinks;
    }

    public ProductCategoriesDiscountChangesServiceModel setDrinks(Integer drinks) {
        this.drinks = drinks;
        return this;
    }

    public Integer getAlcohol() {
        return alcohol;
    }

    public ProductCategoriesDiscountChangesServiceModel setAlcohol(Integer alcohol) {
        this.alcohol = alcohol;
        return this;
    }

    public Integer getNuts() {
        return nuts;
    }

    public ProductCategoriesDiscountChangesServiceModel setNuts(Integer nuts) {
        this.nuts = nuts;
        return this;
    }

    public Integer getChocolate() {
        return chocolate;
    }

    public ProductCategoriesDiscountChangesServiceModel setChocolate(Integer chocolate) {
        this.chocolate = chocolate;
        return this;
    }

    public Integer getOils() {
        return oils;
    }

    public ProductCategoriesDiscountChangesServiceModel setOils(Integer oils) {
        this.oils = oils;
        return this;
    }

    public Integer getSauces() {
        return sauces;
    }

    public ProductCategoriesDiscountChangesServiceModel setSauces(Integer sauces) {
        this.sauces = sauces;
        return this;
    }

    public Integer getSweets() {
        return sweets;
    }

    public ProductCategoriesDiscountChangesServiceModel setSweets(Integer sweets) {
        this.sweets = sweets;
        return this;
    }

    public Integer getHousehold() {
        return household;
    }

    public ProductCategoriesDiscountChangesServiceModel setHousehold(Integer household) {
        this.household = household;
        return this;
    }

    public Integer getOther() {
        return other;
    }

    public ProductCategoriesDiscountChangesServiceModel setOther(Integer other) {
        this.other = other;
        return this;
    }


}
