package com.example.shoppingassistant.model.service;

public class StoreServiceModel {

    private Long id;
    private String name;
    private String description;
    private String storeLogo;

    public StoreServiceModel() {
    }

    public String getName() {
        return name;
    }

    public StoreServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StoreServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public StoreServiceModel setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
        return this;
    }
}
