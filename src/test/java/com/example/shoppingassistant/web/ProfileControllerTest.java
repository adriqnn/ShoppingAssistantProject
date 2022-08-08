package com.example.shoppingassistant.web;

import com.example.shoppingassistant.model.service.UserServiceModel;
import com.example.shoppingassistant.model.userDetails.ApplicationUserDetails;
import com.example.shoppingassistant.model.view.UserViewModel;
import com.example.shoppingassistant.service.ProductService;
import com.example.shoppingassistant.service.UserService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final ModelMapper modelMapper = new ModelMapper();
    private final PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();

    ApplicationUserDetails userDetailsForTesting = new ApplicationUserDetails(10L, "test", passwordEncoder.encode("test"),
            "test","test@test.com",0,"test.png", List.of(new SimpleGrantedAuthority("ROLE_USER")));


    @Test
    void testProfileIdShowProfileViewShown() throws Exception {
        when(userService.getUserViewModel(userDetailsForTesting.getId())).thenReturn(modelMapper.map(userDetailsForTesting, UserViewModel.class));

        mockMvc.perform(get("/profile/" + userDetailsForTesting.getId()).with(user(userDetailsForTesting)))
                .andExpect(status().isOk()).andExpect(view().name("profile"));
    }

    @Test
    void testChangeUsernameGetViewShown() throws Exception {
        when(userService.getUserViewModel(userDetailsForTesting.getId())).thenReturn(modelMapper.map(userDetailsForTesting, UserViewModel.class));

        mockMvc.perform(get("/profile/" + userDetailsForTesting.getId() + "/change-username").with(user(userDetailsForTesting)))
                .andExpect(status().isOk()).andExpect(view().name("change-username"));

    }

    @Test
    void testChangeUsernamePostViewShown() throws Exception {
        when(userService.findByPrincipalName(userDetailsForTesting.getUsername()))
                .thenReturn(modelMapper.map(userDetailsForTesting, UserServiceModel.class));

        mockMvc.perform(post("/profile/" + userDetailsForTesting.getId() + "/change-username").with(user(userDetailsForTesting))
                        .param("username", "test123")
                        .param("password", "test")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/profile/" + userDetailsForTesting.getId()));
    }

    @Test
    void testChangeUsernameFailWrongUsernamePostViewShown() throws Exception {
        when(userService.findByPrincipalName(userDetailsForTesting.getUsername()))
                .thenReturn(modelMapper.map(userDetailsForTesting, UserServiceModel.class));

        mockMvc.perform(post("/profile/" + userDetailsForTesting.getId() + "/change-username").with(user(userDetailsForTesting))
                        .param("username", "")
                        .param("password", "test")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/profile/" + userDetailsForTesting.getId() + "/change-username"));
    }

    @Test
    void testChangeUsernameFailWrongPasswordPostViewShown() throws Exception {
        when(userService.findByPrincipalName(userDetailsForTesting.getUsername()))
                .thenReturn(modelMapper.map(userDetailsForTesting, UserServiceModel.class));

        mockMvc.perform(post("/profile/" + userDetailsForTesting.getId() + "/change-username").with(user(userDetailsForTesting))
                        .param("username", "test123")
                        .param("password", "")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/profile/" + userDetailsForTesting.getId() + "/change-username"));
    }

    @Test
    void testChangePasswordGetViewShown() throws Exception {

        mockMvc.perform(get("/profile/" + userDetailsForTesting.getId() + "/change-password").with(user(userDetailsForTesting)))
                .andExpect(status().isOk()).andExpect(view().name("change-password"));

    }

    @Test
    void testChangePasswordPostViewShown() throws Exception {
        when(userService.findByPrincipalName(userDetailsForTesting.getUsername()))
                .thenReturn(modelMapper.map(userDetailsForTesting, UserServiceModel.class));

        mockMvc.perform(post("/profile/" + userDetailsForTesting.getId() + "/change-password").with(user(userDetailsForTesting))
                        .param("password", "test")
                        .param("newPassword", "test123")
                        .param("confirmNewPassword", "test123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/profile/" + userDetailsForTesting.getId()));
    }

    @Test
    void testChangePasswordFailPostViewShown() throws Exception {
        when(userService.findByPrincipalName(userDetailsForTesting.getUsername()))
                .thenReturn(modelMapper.map(userDetailsForTesting, UserServiceModel.class));

        mockMvc.perform(post("/profile/" + userDetailsForTesting.getId() + "/change-password").with(user(userDetailsForTesting))
                        .param("password", "")
                        .param("newPassword", "test123")
                        .param("confirmPassword", "test123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/profile/" + userDetailsForTesting.getId() + "/change-password"));
    }


    @Test
    void testChangeFirstnameGetViewShown() throws Exception {

        mockMvc.perform(get("/profile/" + userDetailsForTesting.getId() + "/change-firstname").with(user(userDetailsForTesting)))
                .andExpect(status().isOk()).andExpect(view().name("change-firstname"));

    }

    @Test
    void testChangeFirstNamePostViewShown() throws Exception {

        mockMvc.perform(post("/profile/" + userDetailsForTesting.getId() + "/change-firstname").with(user(userDetailsForTesting))
                        .param("firstName", "test1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/profile/" + userDetailsForTesting.getId()));
    }

    @Test
    void testChangeFirstNameFailPostViewShown() throws Exception {

        mockMvc.perform(post("/profile/" + userDetailsForTesting.getId() + "/change-firstname").with(user(userDetailsForTesting))
                        .param("firstName", "")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/profile/" + userDetailsForTesting.getId() + "/change-firstname"));
    }

    @Test
    void testChangeEmailGetViewShown() throws Exception {

        mockMvc.perform(get("/profile/" + userDetailsForTesting.getId() + "/change-email").with(user(userDetailsForTesting)))
                .andExpect(status().isOk()).andExpect(view().name("change-email"));

    }

    @Test
    void testChangeEmailPostViewShown() throws Exception {

        mockMvc.perform(post("/profile/" + userDetailsForTesting.getId() + "/change-email").with(user(userDetailsForTesting))
                        .param("email", "test@test.com")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/profile/" + userDetailsForTesting.getId()));
    }

    @Test
    void testChangeEmailFailPostViewShown() throws Exception {

        mockMvc.perform(post("/profile/" + userDetailsForTesting.getId() + "/change-email").with(user(userDetailsForTesting))
                        .param("email", "")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/profile/" + userDetailsForTesting.getId() + "/change-email"));
    }

    @Test
    void testChangeProfilePictureGetViewShown() throws Exception {

        mockMvc.perform(get("/profile/" + userDetailsForTesting.getId() + "/change-picture").with(user(userDetailsForTesting)))
                .andExpect(status().isOk()).andExpect(view().name("change-picture"));

    }

    @Test
    void testChangeProfilePicturePostViewShown() throws Exception {

        mockMvc.perform(post("/profile/" + userDetailsForTesting.getId() + "/change-picture").with(user(userDetailsForTesting))
                        .param("imageUrl", "test123.png")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/profile/" + userDetailsForTesting.getId()));
    }

    @Test
    void testChangeProfilePictureFailPostViewShown() throws Exception {

        mockMvc.perform(post("/profile/" + userDetailsForTesting.getId() + "/change-picture").with(user(userDetailsForTesting))
                        .param("imageUrl", "test123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/profile/" + userDetailsForTesting.getId() + "/change-picture"));
    }

}
