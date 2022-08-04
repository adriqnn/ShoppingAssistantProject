package com.example.shoppingassistant.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/discounts")
public class DiscountController {

    @GetMapping
    public String displayActiveDailyDiscountTokens(Model model){
        return "discounts";
    }

}


