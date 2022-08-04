package com.example.shoppingassistant.model.validation;

import com.example.shoppingassistant.service.StoreService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueStoreNameValidator implements ConstraintValidator<UniqueStoreName, String> {

    private final StoreService storeService;

    public UniqueStoreNameValidator(StoreService storeService) {
        this.storeService = storeService;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return this.storeService.findByName(name).isEmpty();
    }
}
