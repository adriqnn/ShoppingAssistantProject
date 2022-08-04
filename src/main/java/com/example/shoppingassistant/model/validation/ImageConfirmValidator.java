package com.example.shoppingassistant.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageConfirmValidator implements ConstraintValidator<ImageConfirm, String> {

    @Override
    public boolean isValid(String imageUrl, ConstraintValidatorContext constraintValidatorContext) {

        return imageUrl.endsWith(".png") || imageUrl.endsWith(".jpg");
    }
}
