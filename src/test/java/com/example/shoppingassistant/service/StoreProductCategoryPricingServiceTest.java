package com.example.shoppingassistant.service;

import com.example.shoppingassistant.model.entity.StoreProductCategoryPricing;
import com.example.shoppingassistant.model.entity.enums.ProductCategoryEnum;
import com.example.shoppingassistant.repository.StoreProductCategoryPricingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StoreProductCategoryPricingServiceTest {

    @Mock
    private StoreProductCategoryPricingRepository storeProductCategoryPricingRepository;

    private StoreProductCategoryPricingService storeProductCategoryPricingService;

    @BeforeEach
    void setUp(){
        storeProductCategoryPricingService = new StoreProductCategoryPricingService(storeProductCategoryPricingRepository);
    }

    @Test
    void testInitializeAllCategoryPricingBasedOnProductCategories_returnListOfStoreProductCategoryPricing() {
        List<StoreProductCategoryPricing> pricing = Arrays.stream(ProductCategoryEnum.values()).map(productCategoryEnum -> {
            StoreProductCategoryPricing storeProductCategoryPricing = new StoreProductCategoryPricing(
                    productCategoryEnum, 0,
                    String.format("The %% discount for the %s category in the %s Store", productCategoryEnum.name().toLowerCase(), "billa")
            );
            this.storeProductCategoryPricingRepository.save(storeProductCategoryPricing);
            return storeProductCategoryPricing;
        }).collect(Collectors.toList());

        Assertions.assertEquals(pricing.size(), storeProductCategoryPricingService.initializeAllCategoryPricingBasedOnProductCategories("billa").size());
    }
}
