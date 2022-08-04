package com.example.shoppingassistant.model.binding;

import com.example.shoppingassistant.model.validation.ConfirmPassword;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PasswordChangeBindingModel {

    @ConfirmPassword
    private String password;
    @NotBlank(message = "Password is required.")
    @Size(min = 5, message = "Password must be at least 5 symbols.")
    private String newPassword;
    @NotBlank(message = "Password is required.")
    @Size(min = 5, message = "Password must be at least 5 symbols.")
    private String confirmNewPassword;

    public PasswordChangeBindingModel() {
    }

    public String getPassword() {
        return password;
    }

    public PasswordChangeBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public PasswordChangeBindingModel setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public PasswordChangeBindingModel setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
        return this;
    }
}
