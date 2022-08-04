package com.example.shoppingassistant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Discount was not found.")
public class DiscountNotFoundException extends RuntimeException{

    public DiscountNotFoundException() {
    }

    public DiscountNotFoundException(String message) {
        super(message);
    }

    public DiscountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DiscountNotFoundException(Throwable cause) {
        super(cause);
    }
}
