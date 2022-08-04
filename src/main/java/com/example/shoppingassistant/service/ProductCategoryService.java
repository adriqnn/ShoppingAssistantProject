package com.example.shoppingassistant.service;

import com.example.shoppingassistant.exceptions.ProductCategoryNotFoundException;
import com.example.shoppingassistant.model.entity.ProductCategory;
import com.example.shoppingassistant.model.entity.enums.ProductCategoryEnum;
import com.example.shoppingassistant.repository.ProductCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public ProductCategory findByProductCategory(ProductCategoryEnum productCategory) {

        return this.productCategoryRepository.findByProductCategory(productCategory).orElseThrow(() -> new ProductCategoryNotFoundException("Product category with that name was not found."));
    }

    public void initializeAllMainProductCategories(){
        if(this.productCategoryRepository.count() == 0){
            Arrays.stream(ProductCategoryEnum.values()).forEach(productCategoryEnum -> {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setProductCategory(productCategoryEnum);
                productCategory.setDescription(String.format("Product category - %s.", productCategoryEnum.name().toLowerCase()));
                this.productCategoryRepository.save(productCategory);
            });
        }
    }
}
