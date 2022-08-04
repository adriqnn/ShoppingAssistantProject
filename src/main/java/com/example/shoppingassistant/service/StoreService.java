package com.example.shoppingassistant.service;

import com.example.shoppingassistant.exceptions.StoreNotFoundException;
import com.example.shoppingassistant.model.entity.Store;
import com.example.shoppingassistant.model.entity.StoreProductCategoryPricing;
import com.example.shoppingassistant.model.service.ProductCategoriesDiscountChangesServiceModel;
import com.example.shoppingassistant.model.service.StoreServiceModel;
import com.example.shoppingassistant.model.view.StoreViewModel;
import com.example.shoppingassistant.repository.StoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper;
    private final StoreProductCategoryPricingService storeProductCategoryPricingService;

    public StoreService(StoreRepository storeRepository, ModelMapper modelMapper, StoreProductCategoryPricingService storeProductCategoryPricingService) {
        this.storeRepository = storeRepository;
        this.modelMapper = modelMapper;
        this.storeProductCategoryPricingService = storeProductCategoryPricingService;
    }

    public Optional<Store> findByName(String name) {
        return this.storeRepository.findByName(name);
    }

    public void saveAddedNewStore(StoreServiceModel storeServiceModel) {

        Store store = this.modelMapper.map(storeServiceModel, Store.class);
        List<StoreProductCategoryPricing> listOfStoreProductCategoryPricingForStore = this.storeProductCategoryPricingService.initializeAllCategoryPricingBasedOnProductCategories(store.getName());
        store.setPricingList(listOfStoreProductCategoryPricingForStore);
        this.storeRepository.save(store);
    }

    public List<String> getAllStoresNames() {
        return this.storeRepository.getAllStoresNames();
    }

    public long getCountOfAllStores() {
        return this.storeRepository.count();
    }

    public void updateDiscountPercentagesOfProductCategoriesForTheStore(ProductCategoriesDiscountChangesServiceModel productCategoriesDiscountChangesServiceModel) {
        Store store = this.storeRepository.findByName(productCategoriesDiscountChangesServiceModel.getName()).orElseThrow(() -> new StoreNotFoundException("Store was not found."));
        Map<String, Integer> productCategories = productCategoriesDiscountChangesServiceModel.getProductCategories();
        //Field[] declaredFields1 = productCategoriesDiscountChangesServiceModel.getClass().getDeclaredFields();

        store.getPricingList().stream().forEach(pricingCategory -> {
            pricingCategory.setDiscountPercentage(productCategories.get(pricingCategory.getPricingCategory().name()));
            this.storeProductCategoryPricingService.saveChanges(pricingCategory);
        });
    }

    public List<StoreViewModel> getAllStores() {
        List<StoreViewModel> stores = new ArrayList<>();
        this.storeRepository.findAll().stream().map(store -> {
            store.getPricingList().stream().forEach(pricing -> {
                StoreViewModel storeViewModel = new StoreViewModel(store.getName(), pricing.getPricingCategory().name(), pricing.getDiscountPercentage());
                stores.add(storeViewModel);
            });
            return store;
        }).collect(Collectors.toList());
        return stores;
    }

    public List<StoreViewModel> getAllStoresAndFilterOnlyDiscountedCategories() {
        List<StoreViewModel> storesAndDiscounts = new ArrayList<>();

//        List<Store> collect = this.storeRepository.findAll().stream().map(s -> {
//            s.setPricingList(s.getPricingList().stream().filter(pricing -> pricing.getDiscountPercentage() > 0).collect(Collectors.toList()));
//            return s;
//        }).collect(Collectors.toList());

        List<Store> stores = this.storeRepository.findAll().stream().map(store -> {
            store.getPricingList().stream()
                    .filter(pricing -> pricing.getDiscountPercentage() > 0)
                    .forEach(element -> {
                        StoreViewModel storeViewModel = new StoreViewModel(store.getName(), element.getPricingCategory().name(), element.getDiscountPercentage());
                        storesAndDiscounts.add(storeViewModel);
                    });
            return store;
        }).toList();

        return storesAndDiscounts;
    }
}
