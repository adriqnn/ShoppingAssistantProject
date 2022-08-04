package com.example.shoppingassistant.service;

import com.example.shoppingassistant.model.entity.StoreProductCategoryPricing;
import com.example.shoppingassistant.model.entity.enums.ProductCategoryEnum;
import com.example.shoppingassistant.repository.StoreProductCategoryPricingRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreProductCategoryPricingService {
    private final StoreProductCategoryPricingRepository storeProductCategoryPricingRepository;

    public StoreProductCategoryPricingService(StoreProductCategoryPricingRepository storeProductCategoryPricingRepository) {
        this.storeProductCategoryPricingRepository = storeProductCategoryPricingRepository;
    }

    public List<StoreProductCategoryPricing> initializeAllCategoryPricingBasedOnProductCategories(String storeName) {
        return Arrays.stream(ProductCategoryEnum.values()).map(productCategoryEnum -> {
            StoreProductCategoryPricing storeProductCategoryPricing = new StoreProductCategoryPricing(
                    productCategoryEnum, 0,
                    String.format("The %% discount for the %s category in the %s Store", productCategoryEnum.name().toLowerCase(), storeName)
            );
            this.storeProductCategoryPricingRepository.save(storeProductCategoryPricing);
            return storeProductCategoryPricing;
        }).collect(Collectors.toList());

    }

    public void saveChanges(StoreProductCategoryPricing pricingCategory) {
        this.storeProductCategoryPricingRepository.save(pricingCategory);
    }
}
