package com.example.shoppingassistant.web;

import com.example.shoppingassistant.model.entity.*;
import com.example.shoppingassistant.model.entity.enums.ProductCategoryEnum;
import com.example.shoppingassistant.model.entity.enums.UserRoleEnum;
import com.example.shoppingassistant.model.userDetails.ApplicationUserDetails;
import com.example.shoppingassistant.repository.UserRepository;
import com.example.shoppingassistant.service.ApplicationUserDetailsService;
import com.example.shoppingassistant.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class StoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ApplicationUserDetails userDetailsForTesting = new ApplicationUserDetails(10L, "test", "test",
            "test","test@test.com",0,"test.png", List.of(new SimpleGrantedAuthority("ROLE_USER")));

    @Test
    void testDisplayStoreDiscountInformationWebView() throws Exception{

        mockMvc.perform(get("/stores").with(user(userDetailsForTesting)).with(csrf()))
                .andExpect(status().isOk()).andExpect(view().name("stores"));
    }
}
