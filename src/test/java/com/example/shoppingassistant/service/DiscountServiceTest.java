package com.example.shoppingassistant.service;

import com.example.shoppingassistant.exceptions.DiscountNotFoundException;
import com.example.shoppingassistant.model.entity.Discount;
import com.example.shoppingassistant.repository.DiscountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DiscountServiceTest {

    @Mock
    private DiscountRepository discountRepository;

    private DiscountService discountService;

    @BeforeEach
    void SetUp(){
        discountService = new DiscountService(discountRepository);
    }

    @Test
    void testFindDiscountById_returnsDiscount(){

        Discount discount1 = new Discount("10%", 10,"test.png","testDescription1").setId(1L);
        Discount discount2 = new Discount().setId(2L).setDiscountType("20%").setDiscountValue(20).setDiscountPicture("test.png").setDescription("testDescription2");

        when(discountRepository.findById(1L)).thenReturn(Optional.of(discount1));
        when(discountRepository.findById(2L)).thenReturn(Optional.of(discount2));

        Assertions.assertEquals(1L,discountService.findById(1L).getId());
        Assertions.assertEquals(2L,discountService.findById(2L).getId());

    }

    @Test
    void testFindDiscountById_throwError(){

        Assertions.assertThrows(DiscountNotFoundException.class, () -> discountService.findById(10L));
    }
}
