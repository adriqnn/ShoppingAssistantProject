package com.example.shoppingassistant.model.binding;

import com.example.shoppingassistant.model.validation.ConfirmPassword;
import com.example.shoppingassistant.model.validation.UniqueUsername;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsernameChangeBindingModel {

    @UniqueUsername(message = "Username already taken")
    @NotBlank(message = "Username is required.")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 symbols.")
    private String username;

    @ConfirmPassword(message = "Wrong Password")
    private String password;

    public UsernameChangeBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public UsernameChangeBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UsernameChangeBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
