package com.example.shoppingassistant.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testUserLoginGetPageShown() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk()).andExpect(view().name("login"));
    }

    @Test
    void testUserLoginNoUsernamePageShown() throws Exception {
        mockMvc.perform(post("/users/login")
                .param("username", "testNoUser")
                .param("password", "123")
                        .with(csrf()))
                .andExpect(status().isOk()).andExpect(forwardedUrl("/users/login-error"));
    }

    @Test
    void testUserLoginWrongPasswordPageShown() throws Exception {
        mockMvc.perform(post("/users/login")
                        .param("username", "administrator")
                        .param("password", "test123")
                        .with(csrf()))
                .andExpect(status().isOk()).andExpect(forwardedUrl("/users/login-error"));
    }

    @Test
    void testUserLoginError() throws Exception {
        mockMvc.perform(post("/users/login-error").with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testUserRegistrationGetPageShown() throws Exception {
        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk()).andExpect(view().name("register"));
    }

    @Test
    void testUserRegistrationPostSuccessfulPageShown() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("username", "test")
                        .param("password", "test123")
                        .param("confirmPassword", "test123")
                        .param("firstName", "test")
                        .param("email", "test@test.com")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testUserRegistrationPostUnsuccessfulUsernameAndEmailInDatabasePageShown() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("username", "test")
                        .param("password", "test123")
                        .param("confirmPassword", "test123")
                        .param("firstName", "test")
                        .param("email", "test@test.com")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("register"));
    }

    @Test
    void testUserRegistrationPostUnsuccessfulUsernameInDatabasePageShown() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("username", "test")
                        .param("password", "test123")
                        .param("confirmPassword", "test123")
                        .param("firstName", "test")
                        .param("email", "testusername@test.com")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("register"));
    }

    @Test
    void testUserRegistrationPostUnsuccessfulUserEmailInDatabasePageShown() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("username", "testEmail")
                        .param("password", "test123")
                        .param("confirmPassword", "test123")
                        .param("firstName", "test")
                        .param("email", "test@test.com")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("register"));
    }

    @Test
    void testUserRegistrationPostUnsuccessfulUsernamePageShown() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("username", "")
                        .param("password", "test123")
                        .param("confirmPassword", "test123")
                        .param("firstName", "test")
                        .param("email", "test@test.com")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("register"));
    }

    @Test
    void testUserRegistrationPostUnsuccessfulPasswordOnePageShown() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("username", "test")
                        .param("password", "")
                        .param("confirmPassword", "")
                        .param("firstName", "test")
                        .param("email", "test@test.com")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("register"));
    }

    @Test
    void testUserRegistrationPostUnsuccessfulPasswordTwoPageShown() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("username", "test")
                        .param("password", "test12345")
                        .param("confirmPassword", "test123")
                        .param("firstName", "test")
                        .param("email", "test@test.com")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("register"));
    }

    @Test
    void testUserRegistrationPostUnsuccessfulFirstNamePageShown() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("username", "test")
                        .param("password", "test123")
                        .param("confirmPassword", "test123")
                        .param("firstName", "")
                        .param("email", "test@test.com")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("register"));
    }

    @Test
    void testUserRegistrationPostUnsuccessfulEmailPageShown() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("username", "test")
                        .param("password", "test123")
                        .param("confirmPassword", "test123")
                        .param("firstName", "test")
                        .param("email", "")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("register"));

    }
}