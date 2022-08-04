package com.example.shoppingassistant.service;

import com.example.shoppingassistant.exceptions.ProductPictureNotFoundException;
import com.example.shoppingassistant.model.entity.ProductPicture;
import com.example.shoppingassistant.model.entity.enums.ProductCategoryEnum;
import com.example.shoppingassistant.repository.ProductPictureRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class ProductPictureService {

    private final ProductPictureRepository productPictureRepository;

    public ProductPictureService(ProductPictureRepository productPictureRepository) {
        this.productPictureRepository = productPictureRepository;
    }

    public void initializeProductCategoriesPictures() {
       if(this.productPictureRepository.count() == 0){
           Arrays.stream(ProductCategoryEnum.values()).forEach(productCategoryEnum -> {

               String createTagForProductPicture = productCategoryEnum.name();
               String resourceOfTheProductPicture = String.format("/images/%s.png",productCategoryEnum);

               ProductPicture productPicture = new ProductPicture(createTagForProductPicture, resourceOfTheProductPicture);
               this.productPictureRepository.save(productPicture);
           });
       }


    }

    public ProductPicture getPictureByTagName(String tag) {
        return this.productPictureRepository.findByTag(tag).orElseThrow(() -> new ProductPictureNotFoundException("Product picture with tag: " + tag + " was not found."));
    }
}
