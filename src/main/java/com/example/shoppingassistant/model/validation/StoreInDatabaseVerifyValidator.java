package com.example.shoppingassistant.model.validation;

import com.example.shoppingassistant.service.StoreService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StoreInDatabaseVerifyValidator implements ConstraintValidator<StoreInDatabaseVerify, String> {

    private final StoreService storeService;

    public StoreInDatabaseVerifyValidator(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return this.storeService.findByName(name).isPresent();
    }
}
