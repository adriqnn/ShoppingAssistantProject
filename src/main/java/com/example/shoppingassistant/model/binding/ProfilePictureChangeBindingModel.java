package com.example.shoppingassistant.model.binding;

import com.example.shoppingassistant.model.validation.ImageConfirm;

import javax.validation.constraints.NotBlank;

public class ProfilePictureChangeBindingModel {

    @NotBlank(message = "Image cannot be empty.")
    @ImageConfirm
    private String imageUrl;

    public ProfilePictureChangeBindingModel() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ProfilePictureChangeBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}