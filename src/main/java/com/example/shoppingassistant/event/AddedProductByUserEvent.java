package com.example.shoppingassistant.event;

import org.springframework.context.ApplicationEvent;

public class AddedProductByUserEvent extends ApplicationEvent {

    private final Long userId;

    public AddedProductByUserEvent(Object source, Long userId) {
        super(source);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
