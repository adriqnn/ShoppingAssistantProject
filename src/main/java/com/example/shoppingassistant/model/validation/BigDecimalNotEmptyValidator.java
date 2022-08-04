package com.example.shoppingassistant.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class BigDecimalNotEmptyValidator implements ConstraintValidator<BigDecimalNotEmpty, BigDecimal> {
    @Override
    public boolean isValid(BigDecimal bigDecimal, ConstraintValidatorContext constraintValidatorContext) {
        return bigDecimal != null;
    }
}
