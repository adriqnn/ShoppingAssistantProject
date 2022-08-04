package com.example.shoppingassistant.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {

    @NotBlank(message = "Username is required.")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 symbols.")
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(min = 5, message = "Password must be at least 5 symbols.")
    private String password;

    @NotBlank(message = "Password is required")
    @Size(min = 5, message = "Password must be at least 5 symbols.")
    private String confirmPassword;

    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 symbols.")
    private String firstName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email must be valid.")
    private String email;

    public UserRegisterBindingModel() {
    }

    public UserRegisterBindingModel(String username, String password, String confirmPassword, String firstName, String email) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.firstName = firstName;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
