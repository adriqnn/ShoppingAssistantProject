package com.example.shoppingassistant.service;

import com.example.shoppingassistant.exceptions.ProductNotFoundException;
import com.example.shoppingassistant.model.entity.*;
import com.example.shoppingassistant.model.entity.enums.ProductCategoryEnum;
import com.example.shoppingassistant.model.entity.enums.UserRoleEnum;
import com.example.shoppingassistant.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductCategoryService productCategoryService;
    @Mock
    private UserService userService;
    @Mock
    private ProductPictureService productPictureService;
    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    private ProductService productService;
    private ModelMapper modelMapper = new ModelMapper();

    @BeforeEach
    void setUp(){
        this.productService = new ProductService(productRepository, modelMapper, productCategoryService, userService, productPictureService, applicationEventPublisher);
    }

    @Test
    void testGetAllProductsInUsersShoppingList_returnListProductViewModel(){

        when(this.userService.findByName("test")).thenReturn(new User().setProducts(List.of(new Product())));

        Assertions.assertEquals(1, this.productService.getAllProductsInUsersShoppingList("test").size());
    }

    @Test
    void testGetTotalSum_returnBigDecimal(){

        when(this.userService.findByName("test")).thenReturn(new User().setProducts(List.of(new Product().setPrice(BigDecimal.valueOf(5)))));

        Assertions.assertEquals(BigDecimal.valueOf(5), this.productService.getTotalSum("test"));
    }

    @Test
    void testRemoveOneItemFromTheList_returnTrue(){

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

        when(this.userService.findByName("TestUsername")).thenReturn(testUser);
        when(this.productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(this.productRepository.findById(2L)).thenReturn(Optional.of(product2));

        Assertions.assertTrue(productService.removeOneItemFromTheList("TestUsername", 1L));
        Assertions.assertTrue(productService.removeOneItemFromTheList("TestUsername", 2L));

    }

    @Test
    void testRemoveOneItemFromTheList_returnFalse(){

        Product product1 = new Product(1L, "test1", BigDecimal.valueOf(1), LocalDate.of(2022,8,11),"picture.png", new ProductCategory());

        User testUser = new User().setId(1L).setUsername("TestUsername")
                .setPassword("TestPassword").setFirstName("TestFirstName")
                .setEmail("TestEmail@TestEmail.com").setApplicationPoints(1)
                .setProfilePicture("/images/test.png");

        when(this.userService.findByName("TestUsername")).thenReturn(testUser);
        when(this.productRepository.findById(1L)).thenReturn(Optional.of(product1));

        Assertions.assertFalse(productService.removeOneItemFromTheList("TestUsername",1L));
    }

    @Test
    void testRemoveOneItemFromTheList_throwError(){

        User testUser = new User().setId(1L).setUsername("TestUsername")
                .setPassword("TestPassword").setFirstName("TestFirstName")
                .setEmail("TestEmail@TestEmail.com").setApplicationPoints(1)
                .setProfilePicture("/images/test.png");

        when(this.userService.findByName("TestUsername")).thenReturn(testUser);

        Assertions.assertThrows(ProductNotFoundException.class, () -> this.productService.removeOneItemFromTheList("TestUsername", 1L));
    }
}
