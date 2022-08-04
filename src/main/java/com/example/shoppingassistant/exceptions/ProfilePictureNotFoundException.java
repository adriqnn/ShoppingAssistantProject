package com.example.shoppingassistant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Profile picture was not found.")
public class ProfilePictureNotFoundException extends RuntimeException{

    public ProfilePictureNotFoundException() {
    }

    public ProfilePictureNotFoundException(String message) {
        super(message);
    }

    public ProfilePictureNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProfilePictureNotFoundException(Throwable cause) {
        super(cause);
    }
}
