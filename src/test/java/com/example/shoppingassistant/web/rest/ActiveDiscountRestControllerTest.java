package com.example.shoppingassistant.web.rest;

import com.example.shoppingassistant.model.view.ActiveDiscountViewModel;
import com.example.shoppingassistant.service.ActiveDiscountService;
import com.example.shoppingassistant.service.StoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ActiveDiscountRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActiveDiscountService activeDiscountService;

    @Test
    public void testGetActiveDailyDiscounts() throws Exception {
        when(activeDiscountService.getAllActiveDiscounts()).thenReturn(List.of(
                new ActiveDiscountViewModel(1L,"test",1,"test.png","testDescription1"),
                new ActiveDiscountViewModel().setId(2L).setDiscountType("test").setDiscountValue(2).setPicture("test.png").setDescription("testDescription2")
        ));

        mockMvc.perform(get("/api/discounts/get-active-daily-discounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(jsonPath("$.[0].discountType", is("test")))
                .andExpect(jsonPath("$.[0].discountValue", is(1)))
                .andExpect(jsonPath("$.[0].description", is("testDescription1")))
                .andExpect(jsonPath("$.[1].id", is(2)))
                .andExpect(jsonPath("$.[1].discountType", is("test")))
                .andExpect(jsonPath("$.[1].discountValue", is(2)))
                .andExpect(jsonPath("$.[1].description", is("testDescription2")));
    }
}



















