package com.example.shoppingassistant.model.binding;

import com.example.shoppingassistant.model.validation.NumberCannotBeEmpty;
import com.example.shoppingassistant.model.validation.StoreInDatabaseVerify;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class ChangeCategoryDiscountsInStoreBindingModel {

    @StoreInDatabaseVerify
    @NotBlank(message = "Store name cannot be empty")
    private String name;
    @NumberCannotBeEmpty
    @Min(value = 0, message = "The value must be positive")
    private Integer meat;
    @NumberCannotBeEmpty
    @Min(value = 0, message = "The value must be positive")
    private Integer fish;
    @NumberCannotBeEmpty
    @Min(value = 0, message = "The value must be positive")
    private Integer dairy;
    @NumberCannotBeEmpty
    @Min(value = 0, message = "The value must be positive")
    private Integer vegetables;
    @NumberCannotBeEmpty
    @Min(value = 0, message = "The value must be positive")
    private Integer fruits;
    @NumberCannotBeEmpty
    @Min(value = 0, message = "The value must be positive")
    private Integer drinks;
    @NumberCannotBeEmpty
    @Min(value = 0, message = "The value must be positive")
    private Integer alcohol;
    @NumberCannotBeEmpty
    @Min(value = 0, message = "The value must be positive")
    private Integer nuts;
    @NumberCannotBeEmpty
    @Min(value = 0, message = "The value must be positive")
    private Integer chocolate;
    @NumberCannotBeEmpty
    @Min(value = 0, message = "The value must be positive")
    private Integer oils;
    @NumberCannotBeEmpty
    @Min(value = 0, message = "The value must be positive")
    private Integer sauces;
    @NumberCannotBeEmpty
    @Min(value = 0, message = "The value must be positive")
    private Integer sweets;
    @NumberCannotBeEmpty
    @Min(value = 0, message = "The value must be positive")
    private Integer household;
    @NumberCannotBeEmpty
    @Min(value = 0, message = "The value must be positive")
    private Integer other;

    public ChangeCategoryDiscountsInStoreBindingModel() {
    }

    public String getName() {
        return name;
    }

    public ChangeCategoryDiscountsInStoreBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getMeat() {
        return meat;
    }

    public ChangeCategoryDiscountsInStoreBindingModel setMeat(Integer meat) {
        this.meat = meat;
        return this;
    }

    public Integer getFish() {
        return fish;
    }

    public ChangeCategoryDiscountsInStoreBindingModel setFish(Integer fish) {
        this.fish = fish;
        return this;
    }

    public Integer getDairy() {
        return dairy;
    }

    public ChangeCategoryDiscountsInStoreBindingModel setDairy(Integer dairy) {
        this.dairy = dairy;
        return this;
    }

    public Integer getVegetables() {
        return vegetables;
    }

    public ChangeCategoryDiscountsInStoreBindingModel setVegetables(Integer vegetables) {
        this.vegetables = vegetables;
        return this;
    }

    public Integer getFruits() {
        return fruits;
    }

    public ChangeCategoryDiscountsInStoreBindingModel setFruits(Integer fruits) {
        this.fruits = fruits;
        return this;
    }

    public Integer getDrinks() {
        return drinks;
    }

    public ChangeCategoryDiscountsInStoreBindingModel setDrinks(Integer drinks) {
        this.drinks = drinks;
        return this;
    }

    public Integer getAlcohol() {
        return alcohol;
    }

    public ChangeCategoryDiscountsInStoreBindingModel setAlcohol(Integer alcohol) {
        this.alcohol = alcohol;
        return this;
    }

    public Integer getNuts() {
        return nuts;
    }

    public ChangeCategoryDiscountsInStoreBindingModel setNuts(Integer nuts) {
        this.nuts = nuts;
        return this;
    }

    public Integer getChocolate() {
        return chocolate;
    }

    public ChangeCategoryDiscountsInStoreBindingModel setChocolate(Integer chocolate) {
        this.chocolate = chocolate;
        return this;
    }

    public Integer getOils() {
        return oils;
    }

    public ChangeCategoryDiscountsInStoreBindingModel setOils(Integer oils) {
        this.oils = oils;
        return this;
    }

    public Integer getSauces() {
        return sauces;
    }

    public ChangeCategoryDiscountsInStoreBindingModel setSauces(Integer sauces) {
        this.sauces = sauces;
        return this;
    }

    public Integer getSweets() {
        return sweets;
    }

    public ChangeCategoryDiscountsInStoreBindingModel setSweets(Integer sweets) {
        this.sweets = sweets;
        return this;
    }

    public Integer getHousehold() {
        return household;
    }

    public ChangeCategoryDiscountsInStoreBindingModel setHousehold(Integer household) {
        this.household = household;
        return this;
    }

    public Integer getOther() {
        return other;
    }

    public ChangeCategoryDiscountsInStoreBindingModel setOther(Integer other) {
        this.other = other;
        return this;
    }
}
