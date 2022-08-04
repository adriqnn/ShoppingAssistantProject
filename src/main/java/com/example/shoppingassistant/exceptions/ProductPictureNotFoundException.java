package com.example.shoppingassistant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product picture was not found.")
public class ProductPictureNotFoundException extends RuntimeException{

    public ProductPictureNotFoundException() {
    }

    public ProductPictureNotFoundException(String message) {
        super(message);
    }

    public ProductPictureNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductPictureNotFoundException(Throwable cause) {
        super(cause);
    }
}
