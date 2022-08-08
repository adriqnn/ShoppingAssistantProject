package com.example.shoppingassistant.web;

import com.example.shoppingassistant.model.entity.Store;
import com.example.shoppingassistant.model.userDetails.ApplicationUserDetails;
import com.example.shoppingassistant.service.ActiveDiscountService;
import com.example.shoppingassistant.service.StoreService;
import com.example.shoppingassistant.service.UserService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private  UserService userService;
    @MockBean
    private  StoreService storeService;
    @MockBean
    private  ModelMapper modelMapper;
    @MockBean
    private ActiveDiscountService activeDiscountService;

    ApplicationUserDetails userDetailsForTestingAdmin = new ApplicationUserDetails(10L, "test", "test",
            "test","test@test.com",0,"test.png", List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));

    ApplicationUserDetails userDetailsForTestingUser = new ApplicationUserDetails(10L, "test", "test",
            "test","test@test.com",0,"test.png", List.of(new SimpleGrantedAuthority("ROLE_USER")));


    @Test
    void testControlPanelGetShownViewAdmin() throws Exception {

        mockMvc.perform(get("/admin/control-panel").with(user(userDetailsForTestingAdmin)))
                .andExpect(status().isOk()).andExpect(view().name("admin"));
    }

    @Test
    void testControlPanelGetShowViewErrorUser() throws Exception {

        mockMvc.perform(get("/admin/control-panel").with(user(userDetailsForTestingUser)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testAddNewStoreGetShownViewAdmin() throws Exception {

        mockMvc.perform(get("/admin/add-new-store").with(user(userDetailsForTestingAdmin)))
                .andExpect(status().isOk()).andExpect(view().name("add-new-store"));
    }

    @Test
    void testAddNewStoreGetShowViewErrorUser() throws Exception {

        mockMvc.perform(get("/admin/add-new-store").with(user(userDetailsForTestingUser)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testAddNewStorePostRedirectAdmin() throws Exception {

        mockMvc.perform(post("/admin/add-new-store").with(user(userDetailsForTestingAdmin))
                .param("name", "test")
                .param("description", "test")
                .param("storeLogo", "test.png")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("add-new-store"));
    }

    @Test
    void testAddNewStorePostRedirectAdminWrongName() throws Exception {

        mockMvc.perform(post("/admin/add-new-store").with(user(userDetailsForTestingAdmin))
                        .param("name", "")
                        .param("description", "test")
                        .param("storeLogo", "test.png")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("add-new-store"));
    }

    @Test
    void testAddNewStorePostRedirectAdminWrongDescription() throws Exception {

        mockMvc.perform(post("/admin/add-new-store").with(user(userDetailsForTestingAdmin))
                        .param("name", "test")
                        .param("description", "")
                        .param("storeLogo", "test.png")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("add-new-store"));
    }

    @Test
    void testAddNewStorePostRedirectAdminWrongPictureUrl() throws Exception {

        mockMvc.perform(post("/admin/add-new-store").with(user(userDetailsForTestingAdmin))
                        .param("name", "test")
                        .param("description", "test")
                        .param("storeLogo", "test")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("add-new-store"));
    }

    @Test
    void testChangeCategoryDiscountsInStoreGetShownViewAdmin() throws Exception {

        mockMvc.perform(get("/admin/change-category-discounts-in-store").with(user(userDetailsForTestingAdmin)))
                .andExpect(status().isOk()).andExpect(view().name("change-category-discounts-in-store"));
    }

    @Test
    void testChangeCategoryDiscountsInStoreGetShownViewUserError() throws Exception {

        mockMvc.perform(get("/admin/change-category-discounts-in-store").with(user(userDetailsForTestingUser)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testChangeCategoryDiscountsInStorePostByAdminRedirect() throws Exception {
        Optional<Store> store = Optional.of(new Store());
        when(storeService.findByName("test")).thenReturn(store);

        mockMvc.perform(post("/admin/change-category-discounts-in-store").with(user(userDetailsForTestingAdmin))
                .param("name", "test")
                .param("meat", "1")
                .param("fish", "1")
                .param("dairy", "1")
                .param("vegetables", "1")
                .param("fruits", "1")
                .param("drinks", "1")
                .param("alcohol", "1")
                .param("nuts", "1")
                .param("chocolate", "1")
                .param("oils", "1")
                .param("sauces", "1")
                .param("sweets", "1")
                .param("household", "1")
                .param("other", "1")
                .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("change-category-discounts-in-store"));
    }

    @Test
    void testChangeCategoryDiscountsInStorePostByAdminRedirectWrongName() throws Exception {

        mockMvc.perform(post("/admin/change-category-discounts-in-store").with(user(userDetailsForTestingAdmin))
                        .param("name", "")
                        .param("meat", "1")
                        .param("fish", "1")
                        .param("dairy", "1")
                        .param("vegetables", "1")
                        .param("fruits", "1")
                        .param("drinks", "1")
                        .param("alcohol", "1")
                        .param("nuts", "1")
                        .param("chocolate", "1")
                        .param("oils", "1")
                        .param("sauces", "1")
                        .param("sweets", "1")
                        .param("household", "1")
                        .param("other", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("change-category-discounts-in-store"));
    }

    @Test
    void testChangeCategoryDiscountsInStorePostByAdminRedirectWrongValueMeat() throws Exception {

        mockMvc.perform(post("/admin/change-category-discounts-in-store").with(user(userDetailsForTestingAdmin))
                        .param("name", "test")
                        .param("meat", "")
                        .param("fish", "1")
                        .param("dairy", "1")
                        .param("vegetables", "1")
                        .param("fruits", "1")
                        .param("drinks", "1")
                        .param("alcohol", "1")
                        .param("nuts", "1")
                        .param("chocolate", "1")
                        .param("oils", "1")
                        .param("sauces", "1")
                        .param("sweets", "1")
                        .param("household", "1")
                        .param("other", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("change-category-discounts-in-store"));
    }

    @Test
    void testChangeCategoryDiscountsInStorePostByAdminRedirectWrongValueFish() throws Exception {

        mockMvc.perform(post("/admin/change-category-discounts-in-store").with(user(userDetailsForTestingAdmin))
                        .param("name", "test")
                        .param("meat", "1")
                        .param("fish", "")
                        .param("dairy", "1")
                        .param("vegetables", "1")
                        .param("fruits", "1")
                        .param("drinks", "1")
                        .param("alcohol", "1")
                        .param("nuts", "1")
                        .param("chocolate", "1")
                        .param("oils", "1")
                        .param("sauces", "1")
                        .param("sweets", "1")
                        .param("household", "1")
                        .param("other", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("change-category-discounts-in-store"));
    }

    @Test
    void testChangeCategoryDiscountsInStorePostByAdminRedirectWrongValueDairy() throws Exception {

        mockMvc.perform(post("/admin/change-category-discounts-in-store").with(user(userDetailsForTestingAdmin))
                        .param("name", "test")
                        .param("meat", "1")
                        .param("fish", "1")
                        .param("dairy", "")
                        .param("vegetables", "1")
                        .param("fruits", "1")
                        .param("drinks", "1")
                        .param("alcohol", "1")
                        .param("nuts", "1")
                        .param("chocolate", "1")
                        .param("oils", "1")
                        .param("sauces", "1")
                        .param("sweets", "1")
                        .param("household", "1")
                        .param("other", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("change-category-discounts-in-store"));
    }

    @Test
    void testChangeCategoryDiscountsInStorePostByAdminRedirectWrongValueVegetables() throws Exception {

        mockMvc.perform(post("/admin/change-category-discounts-in-store").with(user(userDetailsForTestingAdmin))
                        .param("name", "test")
                        .param("meat", "1")
                        .param("fish", "1")
                        .param("dairy", "1")
                        .param("vegetables", "")
                        .param("fruits", "1")
                        .param("drinks", "1")
                        .param("alcohol", "1")
                        .param("nuts", "1")
                        .param("chocolate", "1")
                        .param("oils", "1")
                        .param("sauces", "1")
                        .param("sweets", "1")
                        .param("household", "1")
                        .param("other", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("change-category-discounts-in-store"));
    }

    @Test
    void testChangeCategoryDiscountsInStorePostByAdminRedirectWrongValueFruits() throws Exception {

        mockMvc.perform(post("/admin/change-category-discounts-in-store").with(user(userDetailsForTestingAdmin))
                        .param("name", "test")
                        .param("meat", "1")
                        .param("fish", "1")
                        .param("dairy", "1")
                        .param("vegetables", "1")
                        .param("fruits", "")
                        .param("drinks", "1")
                        .param("alcohol", "1")
                        .param("nuts", "1")
                        .param("chocolate", "1")
                        .param("oils", "1")
                        .param("sauces", "1")
                        .param("sweets", "1")
                        .param("household", "1")
                        .param("other", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("change-category-discounts-in-store"));
    }

    @Test
    void testChangeCategoryDiscountsInStorePostByAdminRedirectWrongValueDrinks() throws Exception {

        mockMvc.perform(post("/admin/change-category-discounts-in-store").with(user(userDetailsForTestingAdmin))
                        .param("name", "test")
                        .param("meat", "1")
                        .param("fish", "1")
                        .param("dairy", "1")
                        .param("vegetables", "1")
                        .param("fruits", "1")
                        .param("drinks", "")
                        .param("alcohol", "1")
                        .param("nuts", "1")
                        .param("chocolate", "1")
                        .param("oils", "1")
                        .param("sauces", "1")
                        .param("sweets", "1")
                        .param("household", "1")
                        .param("other", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("change-category-discounts-in-store"));
    }

    @Test
    void testChangeCategoryDiscountsInStorePostByAdminRedirectWrongValueAlcohol() throws Exception {

        mockMvc.perform(post("/admin/change-category-discounts-in-store").with(user(userDetailsForTestingAdmin))
                        .param("name", "test")
                        .param("meat", "1")
                        .param("fish", "1")
                        .param("dairy", "1")
                        .param("vegetables", "1")
                        .param("fruits", "1")
                        .param("drinks", "1")
                        .param("alcohol", "")
                        .param("nuts", "1")
                        .param("chocolate", "1")
                        .param("oils", "1")
                        .param("sauces", "1")
                        .param("sweets", "1")
                        .param("household", "1")
                        .param("other", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("change-category-discounts-in-store"));
    }

    @Test
    void testChangeCategoryDiscountsInStorePostByAdminRedirectWrongValueNuts() throws Exception {

        mockMvc.perform(post("/admin/change-category-discounts-in-store").with(user(userDetailsForTestingAdmin))
                        .param("name", "test")
                        .param("meat", "1")
                        .param("fish", "1")
                        .param("dairy", "1")
                        .param("vegetables", "1")
                        .param("fruits", "1")
                        .param("drinks", "1")
                        .param("alcohol", "1")
                        .param("nuts", "")
                        .param("chocolate", "1")
                        .param("oils", "1")
                        .param("sauces", "1")
                        .param("sweets", "1")
                        .param("household", "1")
                        .param("other", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("change-category-discounts-in-store"));
    }

    @Test
    void testChangeCategoryDiscountsInStorePostByAdminRedirectWrongValueChocolate() throws Exception {

        mockMvc.perform(post("/admin/change-category-discounts-in-store").with(user(userDetailsForTestingAdmin))
                        .param("name", "test")
                        .param("meat", "1")
                        .param("fish", "1")
                        .param("dairy", "1")
                        .param("vegetables", "1")
                        .param("fruits", "1")
                        .param("drinks", "1")
                        .param("alcohol", "1")
                        .param("nuts", "1")
                        .param("chocolate", "")
                        .param("oils", "1")
                        .param("sauces", "1")
                        .param("sweets", "1")
                        .param("household", "1")
                        .param("other", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("change-category-discounts-in-store"));
    }

    @Test
    void testChangeCategoryDiscountsInStorePostByAdminRedirectWrongValueOils() throws Exception {

        mockMvc.perform(post("/admin/change-category-discounts-in-store").with(user(userDetailsForTestingAdmin))
                        .param("name", "test")
                        .param("meat", "1")
                        .param("fish", "1")
                        .param("dairy", "1")
                        .param("vegetables", "1")
                        .param("fruits", "1")
                        .param("drinks", "1")
                        .param("alcohol", "1")
                        .param("nuts", "1")
                        .param("chocolate", "1")
                        .param("oils", "")
                        .param("sauces", "1")
                        .param("sweets", "1")
                        .param("household", "1")
                        .param("other", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("change-category-discounts-in-store"));
    }

    @Test
    void testChangeCategoryDiscountsInStorePostByAdminRedirectWrongValueSauces() throws Exception {

        mockMvc.perform(post("/admin/change-category-discounts-in-store").with(user(userDetailsForTestingAdmin))
                        .param("name", "test")
                        .param("meat", "1")
                        .param("fish", "1")
                        .param("dairy", "1")
                        .param("vegetables", "1")
                        .param("fruits", "1")
                        .param("drinks", "1")
                        .param("alcohol", "1")
                        .param("nuts", "1")
                        .param("chocolate", "1")
                        .param("oils", "1")
                        .param("sauces", "")
                        .param("sweets", "1")
                        .param("household", "1")
                        .param("other", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("change-category-discounts-in-store"));
    }

    @Test
    void testChangeCategoryDiscountsInStorePostByAdminRedirectWrongValueSweets() throws Exception {

        mockMvc.perform(post("/admin/change-category-discounts-in-store").with(user(userDetailsForTestingAdmin))
                        .param("name", "test")
                        .param("meat", "1")
                        .param("fish", "1")
                        .param("dairy", "1")
                        .param("vegetables", "1")
                        .param("fruits", "1")
                        .param("drinks", "1")
                        .param("alcohol", "1")
                        .param("nuts", "1")
                        .param("chocolate", "1")
                        .param("oils", "1")
                        .param("sauces", "1")
                        .param("sweets", "")
                        .param("household", "1")
                        .param("other", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("change-category-discounts-in-store"));
    }

    @Test
    void testChangeCategoryDiscountsInStorePostByAdminRedirectWrongValueHousehold() throws Exception {

        mockMvc.perform(post("/admin/change-category-discounts-in-store").with(user(userDetailsForTestingAdmin))
                        .param("name", "test")
                        .param("meat", "1")
                        .param("fish", "1")
                        .param("dairy", "1")
                        .param("vegetables", "1")
                        .param("fruits", "1")
                        .param("drinks", "1")
                        .param("alcohol", "1")
                        .param("nuts", "1")
                        .param("chocolate", "1")
                        .param("oils", "1")
                        .param("sauces", "1")
                        .param("sweets", "1")
                        .param("household", "")
                        .param("other", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("change-category-discounts-in-store"));
    }

    @Test
    void testChangeCategoryDiscountsInStorePostByAdminRedirectWrongValueOther() throws Exception {

        mockMvc.perform(post("/admin/change-category-discounts-in-store").with(user(userDetailsForTestingAdmin))
                        .param("name", "test")
                        .param("meat", "1")
                        .param("fish", "1")
                        .param("dairy", "1")
                        .param("vegetables", "1")
                        .param("fruits", "1")
                        .param("drinks", "1")
                        .param("alcohol", "1")
                        .param("nuts", "1")
                        .param("chocolate", "1")
                        .param("oils", "1")
                        .param("sauces", "1")
                        .param("sweets", "1")
                        .param("household", "1")
                        .param("other", "")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("change-category-discounts-in-store"));
    }

    @Test
    void testReRollActiveDiscountTokensGetViewShownAdmin() throws Exception {

        mockMvc.perform(get("/admin/re-roll-active-discount-tokens").with(user(userDetailsForTestingAdmin)))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("control-panel"));
    }

    @Test
    void testReRollActiveDiscountTokensGetViewShownUser() throws Exception {

        mockMvc.perform(get("/admin/re-roll-active-discount-tokens").with(user(userDetailsForTestingUser)))
                .andExpect(status().is4xxClientError());
    }
}
