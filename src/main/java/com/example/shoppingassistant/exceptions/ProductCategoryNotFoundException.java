package com.example.shoppingassistant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product category was not found.")
public class ProductCategoryNotFoundException extends RuntimeException{

    public ProductCategoryNotFoundException() {
    }

    public ProductCategoryNotFoundException(String message) {
        super(message);
    }

    public ProductCategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductCategoryNotFoundException(Throwable cause) {
        super(cause);
    }
}
