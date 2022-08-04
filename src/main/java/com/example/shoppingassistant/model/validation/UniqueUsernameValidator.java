package com.example.shoppingassistant.model.validation;

import com.example.shoppingassistant.repository.UserRepository;
import com.example.shoppingassistant.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserService userService;

    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return this.userService.findByUsername(username).isEmpty();
    }
}
