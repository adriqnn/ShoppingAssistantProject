package com.example.shoppingassistant.model.binding;

import com.example.shoppingassistant.model.validation.UniqueUserEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmailChangeBindingModel {

    @NotBlank(message = "Email is required.")
    @Email(message = "Email must be valid.")
    @UniqueUserEmail(message = "Email already taken")
    private String email;

    public EmailChangeBindingModel() {
    }

    public String getEmail() {
        return email;
    }

    public EmailChangeBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

}
