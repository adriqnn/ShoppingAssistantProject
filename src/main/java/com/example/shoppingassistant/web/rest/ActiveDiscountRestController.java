package com.example.shoppingassistant.web.rest;

import com.example.shoppingassistant.model.view.ActiveDiscountViewModel;
import com.example.shoppingassistant.service.ActiveDiscountService;
import com.example.shoppingassistant.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class ActiveDiscountRestController {

    private final DiscountService discountService;
    private final ActiveDiscountService activeDiscountService;

    public ActiveDiscountRestController(DiscountService discountService, ActiveDiscountService activeDiscountService) {
        this.discountService = discountService;
        this.activeDiscountService = activeDiscountService;
    }

    @GetMapping("/get-active-daily-discounts")
    public ResponseEntity<List<ActiveDiscountViewModel>> getActiveDailyDiscounts(){

        return ResponseEntity.ok(activeDiscountService.getAllActiveDiscounts());
    }
}
