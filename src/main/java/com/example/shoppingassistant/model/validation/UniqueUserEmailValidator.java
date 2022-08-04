package com.example.shoppingassistant.model.validation;

import com.example.shoppingassistant.repository.UserRepository;
import com.example.shoppingassistant.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {

    private final UserService userService;

    public UniqueUserEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context){
        return this.userService.findByEmail(email).isEmpty();
    }
}
