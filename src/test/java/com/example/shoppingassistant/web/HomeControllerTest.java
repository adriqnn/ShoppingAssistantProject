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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ApplicationUserDetails userDetailsForTesting = new ApplicationUserDetails(10L, "test", "test",
            "test","test@test.com",0,"test.png", List.of(new SimpleGrantedAuthority("ROLE_USER")));


    @Test
    void testHomePathOneWithoutUserShownView() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk()).andExpect(view().name("index"));
    }

    @Test
    void testHomePathOneWithUserShownView() throws Exception {
        mockMvc.perform(get("/").with(user(userDetailsForTesting)))
                .andExpect(status().isOk()).andExpect(view().name("index"));
    }

    @Test
    void testHomePathTwoWithoutUserShownView() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk()).andExpect(view().name("index"));
    }

    @Test
    void testHomePathTwoWithUserShownView() throws Exception {
        mockMvc.perform(get("/home").with(user(userDetailsForTesting)))
                .andExpect(status().isOk()).andExpect(view().name("index"));
    }

    @Test
    void testAboutPathWithoutUserShownView() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(status().isOk()).andExpect(view().name("about"));
    }

    @Test
    void testAboutPathWithUserShownView() throws Exception {
        mockMvc.perform(get("/about").with(user(userDetailsForTesting)))
                .andExpect(status().isOk()).andExpect(view().name("about"));
    }

}
