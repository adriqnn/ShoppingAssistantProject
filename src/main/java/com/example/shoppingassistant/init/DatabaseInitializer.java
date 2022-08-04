package com.example.shoppingassistant.init;

import com.example.shoppingassistant.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UserRoleService userRoleService;
    private final UserService userService;
    private final ProfilePictureService profilePictureService;
    private final ProductPictureService productPictureService;
    private final ProductCategoryService productCategoryService;
    private final DiscountService discountService;
    private final ActiveDiscountService activeDiscountService;


    public DatabaseInitializer(UserRoleService userRoleService, UserService userService, ProfilePictureService profilePictureService, ProductPictureService productPictureService, ProductCategoryService productCategoryService, DiscountService discountService, ActiveDiscountService activeDiscountService) {
        this.userRoleService = userRoleService;
        this.userService = userService;
        this.profilePictureService = profilePictureService;
        this.productPictureService = productPictureService;
        this.productCategoryService = productCategoryService;
        this.discountService = discountService;
        this.activeDiscountService = activeDiscountService;
    }

    @Override
    public void run(String... args) throws Exception {
        //initialize all the information for the application's database
        this.userRoleService.initializeUserRoles(); //initialize all user roles;
        this.profilePictureService.initializeGenericProfilePictures(); //initialize all 3 generic profile pictures;
        this.productPictureService.initializeProductCategoriesPictures(); //initialize all pictures for product categories;
        this.userService.createAdminAccount(); //create first account - admin account;
        this.productCategoryService.initializeAllMainProductCategories(); //initialize all main product categories;
        this.discountService.initializeAllDiscountsForTheApplication(); //initialize all discounts for the application
        this.activeDiscountService.initializeTheVeryFirstActiveDiscounts(); //initializes first 3 active discounts;

    }
}
