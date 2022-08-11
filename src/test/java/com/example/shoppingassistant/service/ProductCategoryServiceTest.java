package com.example.shoppingassistant.service;

import com.example.shoppingassistant.exceptions.DiscountNotFoundException;
import com.example.shoppingassistant.exceptions.ProductCategoryNotFoundException;
import com.example.shoppingassistant.model.entity.ProductCategory;
import com.example.shoppingassistant.model.entity.enums.ProductCategoryEnum;
import com.example.shoppingassistant.repository.ProductCategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductCategoryServiceTest {

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    private ProductCategoryService productCategoryService;

    @BeforeEach
    void setUp(){
        productCategoryService = new ProductCategoryService(productCategoryRepository);
    }

    @Test
    void testProductCategoryFindByProductCategory_returnsProductCategory(){

        ProductCategory productCategory1 = new ProductCategory(1L,ProductCategoryEnum.MEAT,"test1");
        ProductCategory productCategory2 = new ProductCategory().setId(2L).setProductCategory(ProductCategoryEnum.FISH).setDescription("test2");

        when(productCategoryRepository.findByProductCategory(ProductCategoryEnum.MEAT)).thenReturn(Optional.of(productCategory1));
        when(productCategoryRepository.findByProductCategory(ProductCategoryEnum.FISH)).thenReturn(Optional.of(productCategory2));

        Assertions.assertEquals("MEAT",productCategoryService.findByProductCategory(ProductCategoryEnum.MEAT).getProductCategory().name());
        Assertions.assertEquals("FISH",productCategoryService.findByProductCategory(ProductCategoryEnum.FISH).getProductCategory().name());

    }


    @Test
    void testProductCategoryFindByProductCategory_throwsError(){

        Assertions.assertThrows(ProductCategoryNotFoundException.class, () -> productCategoryService.findByProductCategory(ProductCategoryEnum.MEAT));
    }
}
