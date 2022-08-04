package com.example.shoppingassistant.model.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueStoreNameValidator.class)
public @interface UniqueStoreName {
    String message() default "Store name already in the database";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
