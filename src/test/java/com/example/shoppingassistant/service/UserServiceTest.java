package com.example.shoppingassistant.service;

import com.example.shoppingassistant.exceptions.UserNotFoundException;
import com.example.shoppingassistant.model.entity.Product;
import com.example.shoppingassistant.model.entity.ProductCategory;
import com.example.shoppingassistant.model.entity.User;
import com.example.shoppingassistant.model.entity.UserRole;
import com.example.shoppingassistant.model.entity.enums.UserRoleEnum;
import com.example.shoppingassistant.model.view.UserViewModel;
import com.example.shoppingassistant.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private UserRoleService userRoleService;

    @Mock
    private ProfilePictureService profilePictureService;

    private ModelMapper modelMapper = new ModelMapper();

    private PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();

    private UserService userService;

    @BeforeEach
    void setUp(){
        this.userService = new UserService(userRepository, modelMapper, passwordEncoder,userDetailsService, userRoleService, profilePictureService);
    }

    @Test
    void testFindByName_returnUser(){
        Product product1 = new Product(1L, "test1", BigDecimal.valueOf(1), LocalDate.of(2022,8,11),"picture.png", new ProductCategory());
        Product product2 = new Product().setId(2L).setName("test2").setPrice(BigDecimal.valueOf(2)).setNeededBefore(LocalDate.of(2022,8,11)).setProductPicture("picture.png").setCategory(new ProductCategory());

        UserRole testRole1 = new UserRole().setUserRole(UserRoleEnum.ADMIN);
        UserRole testRole2 = new UserRole().setUserRole(UserRoleEnum.USER);

        User testUser = new User().setId(1L).setUsername("TestUsername")
                .setPassword("TestPassword").setFirstName("TestFirstName")
                .setEmail("TestEmail@TestEmail.com").setApplicationPoints(1)
                .setProfilePicture("/images/test.png").setRoles(List.of(testRole1,testRole2));

        testUser.getProducts().add(product1);
        testUser.getProducts().add(product2);

        when(userRepository.findByUsername("TestUsername")).thenReturn(Optional.of(testUser));

        Assertions.assertEquals("TestUsername", userService.findByName("TestUsername").getUsername());
    }

    @Test
    void testFindByName_throwError(){

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.findByName("test"));
    }

    @Test
    void testGetUserViewModel_returnUserViewModel(){

        UserViewModel userViewModel1 = new UserViewModel().setId(1L).setUsername("test").setFirstName("test").setEmail("test").setApplicationPoints(1).setProfilePicture("test.png");
        when(userRepository.findById(1L)).thenReturn(Optional.of(new User().setId(1L).setUsername("test")));

        Assertions.assertEquals(userViewModel1.getId(), userService.getUserViewModel(1L).getId());

    }

    @Test
    void testGetUserViewModel_throwError(){

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUserViewModel(1L));
    }

    @Test
    void testFindByEmail_returnOptionalUser(){
        User user = new User().setId(1L);
        when(userRepository.findByEmail("test")).thenReturn(Optional.of(user));

        Assertions.assertEquals(1L, this.userService.findByEmail("test").get().getId());


    }

    @Test
    void testFindByUsername_returnOptionalUser(){
        User user = new User().setId(1L);
        when(userRepository.findByUsername("test")).thenReturn(Optional.of(user));

        Assertions.assertEquals(1L, this.userService.findByUsername("test").get().getId());

    }

    @Test
    void testGetAllUsersUsername_returnListOfString(){

        List<String> users = new ArrayList<>();
        users.add("test1");
        users.add("test2");

        when(userRepository.getAllUsersUsernames()).thenReturn(users);

        Assertions.assertEquals(2, userService.getAllUsersUsernames().size());
        Assertions.assertEquals("test1", userService.getAllUsersUsernames().get(0));
        Assertions.assertEquals("test2", userService.getAllUsersUsernames().get(1));

    }

    @Test
    void testGetCountOfAllUser_returnCountOfAllUsers(){

        List<String> users = new ArrayList<>();
        users.add("test1");
        users.add("test2");

        when(userRepository.count()).thenReturn(2L);
        Assertions.assertEquals(2L, userService.getCountOfAllUsers());
        Assertions.assertEquals(users.size(), userService.getCountOfAllUsers());

    }

    @Test
    void testGetById_returnUser(){
        Product product1 = new Product(1L, "test1", BigDecimal.valueOf(1), LocalDate.of(2022,8,11),"picture.png", new ProductCategory());
        Product product2 = new Product().setId(2L).setName("test2").setPrice(BigDecimal.valueOf(2)).setNeededBefore(LocalDate.of(2022,8,11)).setProductPicture("picture.png").setCategory(new ProductCategory());

        UserRole testRole1 = new UserRole().setUserRole(UserRoleEnum.ADMIN);
        UserRole testRole2 = new UserRole().setUserRole(UserRoleEnum.USER);

        User testUser = new User().setId(1L).setUsername("TestUsername")
                .setPassword("TestPassword").setFirstName("TestFirstName")
                .setEmail("TestEmail@TestEmail.com").setApplicationPoints(1)
                .setProfilePicture("/images/test.png").setRoles(List.of(testRole1,testRole2));

        testUser.getProducts().add(product1);
        testUser.getProducts().add(product2);

        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        Assertions.assertEquals("TestUsername", userService.getById(1L).getUsername());

    }

    @Test
    void testGetById_throwError(){

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.getById(1L));
    }

}
