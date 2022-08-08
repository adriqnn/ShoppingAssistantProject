package com.example.shoppingassistant.web.rest;

import com.example.shoppingassistant.model.view.StoreViewModel;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StoreInformationRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService storeService;

    @Test
    void testGetAllDiscountedCategoriesForEachStoreEmptyList() throws Exception {
        when(storeService.getAllStoresAndFilterOnlyDiscountedCategories()).thenReturn(List.of());

        mockMvc.perform(get("/api/stores/get-all-discounted-categories-for-each-store"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testGetAllDiscountedCategoriesForEachStoreNotEmptyList() throws Exception {
        when(storeService.getAllStoresAndFilterOnlyDiscountedCategories()).thenReturn(List.of(
                new StoreViewModel("testName1", "testCategory1", 10),
                new StoreViewModel().setName("testName2").setPricingCategory("testCategory2").setDiscountPercentage(20)
        ));

        mockMvc.perform(get("/api/stores/get-all-discounted-categories-for-each-store"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name", is("testName1")))
                .andExpect(jsonPath("$.[0].pricingCategory", is("testCategory1")))
                .andExpect(jsonPath("$.[0].discountPercentage", is(10)))
                .andExpect(jsonPath("$.[1].name", is("testName2")))
                .andExpect(jsonPath("$.[1].pricingCategory", is("testCategory2")))
                .andExpect(jsonPath("$.[1].discountPercentage", is(20)));
    }
}
