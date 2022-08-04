package com.example.shoppingassistant.model.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = StoreInDatabaseVerifyValidator.class)
public @interface StoreInDatabaseVerify {
    String message() default "Store must already be in the database";
    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};
}
