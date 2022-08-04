package com.example.shoppingassistant.model.binding;

import com.example.shoppingassistant.model.validation.ImageConfirm;
import com.example.shoppingassistant.model.validation.UniqueStoreName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class StoreAddBindingModel {

    @NotBlank(message = "Store name is required.")
    @Size(min = 2, max = 20, message = "Store name should be between 2 and 20 symbols.")
    @UniqueStoreName
    private String name;

    @NotBlank(message = "Store description is required.")
    @Size(min = 2, max = 50, message = "Store description should be between 2 and 500 symbols.")
    private String description;

    @NotBlank(message = "Store logo is required")
    @ImageConfirm
    private String storeLogo;

    public StoreAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public StoreAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StoreAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getStoreLogo() {
        return storeLogo;
    }

    public StoreAddBindingModel setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
        return this;
    }
}
