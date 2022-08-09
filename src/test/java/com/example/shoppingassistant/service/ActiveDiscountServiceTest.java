package com.example.shoppingassistant.service;

import com.example.shoppingassistant.model.entity.ActiveDiscount;
import com.example.shoppingassistant.model.entity.enums.UserRoleEnum;
import com.example.shoppingassistant.model.userDetails.ApplicationUserDetails;
import com.example.shoppingassistant.model.view.ActiveDiscountViewModel;
import com.example.shoppingassistant.repository.ActiveDiscountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ActiveDiscountServiceTest {

    @Mock
    private ActiveDiscountRepository activeDiscountRepository;
    @Mock
    private UserService userService;
    @Mock
    private DiscountService discountService;

    private ActiveDiscountService testActiveDiscountService;

    private ModelMapper modelMapper = new ModelMapper();


    ApplicationUserDetails userDetailsForTestingAdmin = new ApplicationUserDetails(10L, "test", "test",
            "test","test@test.com",0,"test.png", List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));

    @BeforeEach
    void setUp(){
        testActiveDiscountService = new ActiveDiscountService(activeDiscountRepository,userService,discountService,modelMapper);
    }

    @Test
    void testGetCountOfAllActiveDiscounts_testResult() {

        when(activeDiscountRepository.count()).thenReturn(3L);

        Assertions.assertEquals(testActiveDiscountService.getCountOfAllActiveDiscounts(),activeDiscountRepository.count());
    }

    @Test
    void testGetAllActiveDiscounts_testResult() {
        when(activeDiscountRepository.findAll()).thenReturn(List.of(
                new ActiveDiscount("10%",10,"test.png","testDescription1").setId(1L),
                new ActiveDiscount().setId(2L).setDiscountType("20%").setDiscountValue(20).setDiscountPicture("test.png").setDescription("testDescription2")
        ));

        List<ActiveDiscountViewModel> allActiveDiscounts = testActiveDiscountService.getAllActiveDiscounts();

        Assertions.assertEquals(allActiveDiscounts.size(), activeDiscountRepository.findAll().size());
        Assertions.assertEquals(allActiveDiscounts.size(), 2);
    }
}
