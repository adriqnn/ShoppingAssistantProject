package com.example.shoppingassistant.service;


import com.example.shoppingassistant.exceptions.UserRoleNotFoundException;
import com.example.shoppingassistant.model.entity.UserRole;
import com.example.shoppingassistant.model.entity.enums.UserRoleEnum;
import com.example.shoppingassistant.repository.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRoleServiceTest {

    @Mock
    private UserRoleRepository userRoleRepository;

    private UserRoleService userRoleService;

    @BeforeEach
    void setUp(){
        this.userRoleService = new UserRoleService(userRoleRepository);
    }

    @Test
    void testGetUserRole_returnUserRole(){

        UserRole userRole1 = new UserRole(1L,UserRoleEnum.USER);
        UserRole userRole2 = new UserRole().setId(2L).setUserRole(UserRoleEnum.ADMIN);

        when(userRoleRepository.findByUserRole(UserRoleEnum.USER)).thenReturn(Optional.of(userRole1));
        when(userRoleRepository.findByUserRole(UserRoleEnum.ADMIN)).thenReturn(Optional.of(userRole2));

        Assertions.assertEquals("USER",userRoleService.getUserRole(UserRoleEnum.USER).getUserRole().name());
        Assertions.assertEquals(1L,userRoleService.getUserRole(UserRoleEnum.USER).getId());
        Assertions.assertEquals("ADMIN",userRoleService.getUserRole(UserRoleEnum.ADMIN).getUserRole().name());
        Assertions.assertEquals(2L,userRoleService.getUserRole(UserRoleEnum.ADMIN).getId());

    }

    @Test
    void testGetUserRole_throwError(){

        Assertions.assertThrows(UserRoleNotFoundException.class,() -> userRoleService.getUserRole(UserRoleEnum.ADMIN));
    }
}
