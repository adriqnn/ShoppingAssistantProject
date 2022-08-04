package com.example.shoppingassistant.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stores")
public class StoreController {

    @GetMapping
    //@GetMapping("/info-for-discounts-in-stores")
    public String displayStoreDiscountInformation(Model model){
        return "stores";
    }
}
