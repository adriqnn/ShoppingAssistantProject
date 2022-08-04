package com.example.shoppingassistant.web.rest;

import com.example.shoppingassistant.model.view.StoreViewModel;
import com.example.shoppingassistant.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreInformationRestController {

    private final StoreService storeService;

    public StoreInformationRestController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/get-all-discounted-categories-for-each-store")
    public ResponseEntity<List<StoreViewModel>> getAllDiscountedCategoriesForEachStore(){

        List<StoreViewModel> allStoresAndFilterOnlyDiscountedCategories = storeService.getAllStoresAndFilterOnlyDiscountedCategories();

        if(!allStoresAndFilterOnlyDiscountedCategories.isEmpty()){
            return ResponseEntity.ok(storeService.getAllStoresAndFilterOnlyDiscountedCategories());
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
