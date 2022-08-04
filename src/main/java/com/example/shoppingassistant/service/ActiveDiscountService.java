package com.example.shoppingassistant.service;

import com.example.shoppingassistant.model.entity.ActiveDiscount;
import com.example.shoppingassistant.model.entity.Discount;
import com.example.shoppingassistant.model.entity.User;
import com.example.shoppingassistant.model.entity.UserRole;
import com.example.shoppingassistant.model.entity.enums.UserRoleEnum;
import com.example.shoppingassistant.model.view.ActiveDiscountViewModel;
import com.example.shoppingassistant.repository.ActiveDiscountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ActiveDiscountService {
    private static final int GENERATE_ACTIVE_DISCOUNTS_RANDOM_GENERATOR_VALUE_LOWER_BOUND = 1;
    private static final int GENERATE_ACTIVE_DISCOUNTS_RANDOM_GENERATOR_VALUE_UPPER_BOUND = 51;

    private final ActiveDiscountRepository activeDiscountRepository;
    private final UserService userService;
    private final DiscountService discountService;
    private final ModelMapper modelMapper;

    public ActiveDiscountService(ActiveDiscountRepository activeDiscountRepository, UserService userService, DiscountService discountService, ModelMapper modelMapper) {
        this.activeDiscountRepository = activeDiscountRepository;
        this.userService = userService;
        this.discountService = discountService;
        this.modelMapper = modelMapper;
    }

    public void rollDiscountTokens(Long id) {

        User user = this.userService.getById(id);
        List<UserRole> hasAdminRoleInUserRoles = user.getRoles()
                .stream()
                .filter(role -> role.getUserRole().equals(UserRoleEnum.ADMIN)).collect(Collectors.toList());

        if(!hasAdminRoleInUserRoles.isEmpty()){
            getThreeRandomDiscountsAndMakeThemActiveDiscounts();
        }
    }

    public long getCountOfAllActiveDiscounts() {
        return this.activeDiscountRepository.count();
    }

    public List<ActiveDiscountViewModel> getAllActiveDiscounts() {
        return this.activeDiscountRepository.findAll().stream().map(activeDiscount -> modelMapper.map(activeDiscount, ActiveDiscountViewModel.class)).collect(Collectors.toList());
    }

    public void initializeTheVeryFirstActiveDiscounts() {
        if(this.activeDiscountRepository.count() == 0){
            getThreeRandomDiscountsAndMakeThemActiveDiscounts();
        }
    }

    public void getThreeRandomDiscountsAndMakeThemActiveDiscounts(){
        Random random = new Random();
        this.activeDiscountRepository.deleteAll();
        for (int i = 1; i <= 3; i++) {
            long randomDiscountBetweenOneAndFifty = random.nextLong(GENERATE_ACTIVE_DISCOUNTS_RANDOM_GENERATOR_VALUE_UPPER_BOUND - GENERATE_ACTIVE_DISCOUNTS_RANDOM_GENERATOR_VALUE_LOWER_BOUND) + GENERATE_ACTIVE_DISCOUNTS_RANDOM_GENERATOR_VALUE_LOWER_BOUND;
            Discount discount = this.discountService.findById(randomDiscountBetweenOneAndFifty);
            ActiveDiscount activeDiscount = new ActiveDiscount(discount.getDiscountType(), discount.getDiscountValue(),
                    discount.getDiscountPicture(), discount.getDescription());
            this.activeDiscountRepository.save(activeDiscount);
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void reRollActiveDiscountsAt12EachDay(){
        getThreeRandomDiscountsAndMakeThemActiveDiscounts();
    }
}
