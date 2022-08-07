package com.example.shoppingassistant.service;

import com.example.shoppingassistant.model.entity.*;
import com.example.shoppingassistant.model.entity.enums.ProductCategoryEnum;
import com.example.shoppingassistant.model.entity.enums.UserRoleEnum;
import com.example.shoppingassistant.model.userDetails.ApplicationUserDetails;
import com.example.shoppingassistant.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicationUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    private ApplicationUserDetailsService testUserDetailsService;

    @BeforeEach
    void setUp(){
        testUserDetailsService = new ApplicationUserDetailsService(mockUserRepository);
    }

    @Test
    void testLoadUserByUsername_UserExists(){

        UserRole testRole1 = new UserRole().setUserRole(UserRoleEnum.ADMIN);
        UserRole testRole2 = new UserRole().setUserRole(UserRoleEnum.USER);

        Product testProduct1 = new Product().setId(1L).setName("TestProduct1")
                .setPrice(BigDecimal.valueOf(10)).setNeededBefore(LocalDate.of(2022,8,7))
                .setProductPicture("/images/test.png").setCategory(new ProductCategory().setId(1L).setProductCategory(ProductCategoryEnum.MEAT).setDescription("testDescription1"))
                .setProductPictureClass(new ProductPicture().setId(1L).setTag("testProductPicture1").setLocation("locationTest1"));
        Product testProduct2 = new Product().setId(2L).setName("TestProduct2")
                .setPrice(BigDecimal.valueOf(10)).setNeededBefore(LocalDate.of(2022,8,7))
                .setProductPicture("/images/test.png").setCategory(new ProductCategory().setId(2L).setProductCategory(ProductCategoryEnum.FISH).setDescription("testDescription2"))
                .setProductPictureClass(new ProductPicture().setId(2L).setTag("testProductPicture2").setLocation("locationTest2"));

        User testUser = new User().setId(1L).setUsername("TestUsername")
                .setPassword("TestPassword").setFirstName("TestFirstName")
                .setEmail("TestEmail@TestEmail.com").setApplicationPoints(1)
                .setProfilePicture("/images/test.png").setRoles(List.of(testRole1,testRole2))
                .setProducts(List.of(testProduct1,testProduct2));

        when(mockUserRepository.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        ApplicationUserDetails userDetails = (ApplicationUserDetails) testUserDetailsService.loadUserByUsername(testUser.getUsername());

        Assertions.assertEquals(testUser.getId(),userDetails.getId());
        Assertions.assertEquals(testUser.getUsername(),userDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(),userDetails.getPassword());
        Assertions.assertEquals(testUser.getFirstName(),userDetails.getFirstName());
        Assertions.assertEquals(testUser.getEmail(),userDetails.getEmail());
        Assertions.assertEquals(testUser.getApplicationPoints(),userDetails.getApplicationPoints());
        Assertions.assertEquals(testUser.getRoles().size(),userDetails.getAuthorities().size());

        var authoritiesIter = userDetails.getAuthorities().iterator();
        Assertions.assertEquals("ROLE_" + UserRoleEnum.ADMIN.name(),authoritiesIter.next().getAuthority());
        Assertions.assertEquals("ROLE_" + UserRoleEnum.USER.name(),authoritiesIter.next().getAuthority());
    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist(){

        Assertions.assertThrows(UsernameNotFoundException.class, () -> testUserDetailsService.loadUserByUsername("doesntexist@example.com"));
    }
}





























