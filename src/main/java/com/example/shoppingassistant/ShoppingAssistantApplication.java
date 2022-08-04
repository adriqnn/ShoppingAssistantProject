package com.example.shoppingassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShoppingAssistantApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingAssistantApplication.class, args);
    }

}
