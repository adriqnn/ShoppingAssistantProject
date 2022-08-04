package com.example.shoppingassistant.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberCannotBeEmptyValidator implements ConstraintValidator<NumberCannotBeEmpty, Integer> {


    @Override
    public boolean isValid(Integer discountPercentage, ConstraintValidatorContext constraintValidatorContext) {
        if(discountPercentage == null){
            return false;
        }
        return true;
    }
}
