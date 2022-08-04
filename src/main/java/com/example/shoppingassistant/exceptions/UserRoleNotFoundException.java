package com.example.shoppingassistant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User role was not found.")
public class UserRoleNotFoundException extends RuntimeException{
    public UserRoleNotFoundException() {
    }

    public UserRoleNotFoundException(String message) {
        super(message);
    }

    public UserRoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRoleNotFoundException(Throwable cause) {
        super(cause);
    }
}
