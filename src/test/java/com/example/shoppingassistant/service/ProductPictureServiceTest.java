package com.example.shoppingassistant.service;

import com.example.shoppingassistant.exceptions.ProductPictureNotFoundException;
import com.example.shoppingassistant.model.entity.ProductPicture;
import com.example.shoppingassistant.repository.ProductPictureRepository;
import com.example.shoppingassistant.repository.ProfilePictureRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductPictureServiceTest {

    @Mock
    private ProductPictureRepository productPictureRepository;

    private ProductPictureService productPictureService;

    @BeforeEach
    void setUp(){
        this.productPictureService = new ProductPictureService(productPictureRepository);
    }

    @Test
    void testProductPictureServiceGetPictureByTagName_returnProductPicture(){

        ProductPicture productPicture1 = new ProductPicture("test1","test.png").setId(1L);
        ProductPicture productPicture2 = new ProductPicture().setId(2L).setTag("test2").setLocation("test.png");

        when(productPictureRepository.findByTag("test1")).thenReturn(Optional.of(productPicture1));
        when(productPictureRepository.findByTag("test2")).thenReturn(Optional.of(productPicture2));

        Assertions.assertEquals(1L,productPictureService.getPictureByTagName("test1").getId());
        Assertions.assertEquals(2L,productPictureService.getPictureByTagName("test2").getId());

    }

    @Test
    void testProductPictureServiceGetPictureByTagName_trowError(){

        Assertions.assertThrows(ProductPictureNotFoundException.class, () -> productPictureService.getPictureByTagName("test"));
    }
}
