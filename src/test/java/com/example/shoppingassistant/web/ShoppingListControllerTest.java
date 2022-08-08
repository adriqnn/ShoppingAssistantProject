package com.example.shoppingassistant.web;

import com.example.shoppingassistant.model.userDetails.ApplicationUserDetails;
import com.example.shoppingassistant.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingListControllerTest {

    private static final long TEST_ID = 1;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    ApplicationUserDetails userDetailsForTesting = new ApplicationUserDetails(10L, "test", "test",
            "test","test@test.com",0,"test.png", List.of(new SimpleGrantedAuthority("ROLE_USER")));

    @Test
    void testProfileShoppingListViewShown() throws Exception {
        mockMvc.perform(get("/shoppingList/profile/" + userDetailsForTesting.getId()).with(user(userDetailsForTesting)))
                .andExpect(status().isOk()).andExpect(view().name("shopping-list"));
    }

    @Test
    void testAddGroceriesGetViewShown() throws Exception {
        mockMvc.perform(get("/shoppingList/add-groceries").with(user(userDetailsForTesting)))
                .andExpect(status().isOk()).andExpect(view().name("add-groceries"));
    }

    @Test
    void testAddGroceriesPostViewShownSuccessful() throws Exception {
        mockMvc.perform(post("/shoppingList/add-groceries").with(user(userDetailsForTesting))
                .param("name", "meat")
                .param("price", "10")
                .param("productCategory", "MEAT")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:add-groceries"));

    }

    @Test
    void testAddGroceriesPostViewShownUnsuccessfulName() throws Exception {
        mockMvc.perform(post("/shoppingList/add-groceries").with(user(userDetailsForTesting))
                        .param("name", "")
                        .param("price", "10")
                        .param("productCategory", "MEAT")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:add-groceries"));

    }

    @Test
    void testAddGroceriesPostViewShownUnsuccessfulPrice() throws Exception {
        mockMvc.perform(post("/shoppingList/add-groceries").with(user(userDetailsForTesting))
                        .param("name", "meat")
                        .param("price", "")
                        .param("productCategory", "MEAT")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:add-groceries"));

    }

    @Test
    void testAddGroceriesPostViewShownUnsuccessfulProductCategory() throws Exception {
        mockMvc.perform(post("/shoppingList/add-groceries").with(user(userDetailsForTesting))
                        .param("name", "meat")
                        .param("price", "10")
                        .param("productCategory", "")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:add-groceries"));

    }


    @Test
    void testRemoveOneItemFromTheListView() throws Exception {
        mockMvc.perform(get("/shoppingList/remove-one-item/" + TEST_ID).with(user(userDetailsForTesting)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/shoppingList/profile/" + userDetailsForTesting.getId()));
    }

    @Test
    void testRemoveAllItemsFromTheListView() throws Exception {
        mockMvc.perform(get("/shoppingList/remove-all-items").with(user(userDetailsForTesting)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("profile/" + userDetailsForTesting.getId()));
    }

}
