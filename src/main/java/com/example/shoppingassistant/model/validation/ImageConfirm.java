package com.example.shoppingassistant.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ImageConfirmValidator.class)
public @interface ImageConfirm {
    String message() default "It has to be an image -> .jpg or .png";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
