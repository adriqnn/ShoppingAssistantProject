package com.example.shoppingassistant.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ChangeFirstNameBindingModel {

    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 symbols.")
    private String firstName;

    public ChangeFirstNameBindingModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public ChangeFirstNameBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

}
