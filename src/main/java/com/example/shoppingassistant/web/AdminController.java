package com.example.shoppingassistant.web;

import com.example.shoppingassistant.model.binding.ChangeCategoryDiscountsInStoreBindingModel;
import com.example.shoppingassistant.model.binding.StoreAddBindingModel;
import com.example.shoppingassistant.model.entity.ActiveDiscount;
import com.example.shoppingassistant.model.service.ProductCategoriesDiscountChangesServiceModel;
import com.example.shoppingassistant.model.service.StoreServiceModel;
import com.example.shoppingassistant.model.userDetails.ApplicationUserDetails;
import com.example.shoppingassistant.model.view.ActiveDiscountViewModel;
import com.example.shoppingassistant.model.view.StoreViewModel;
import com.example.shoppingassistant.service.ActiveDiscountService;
import com.example.shoppingassistant.service.StoreService;
import com.example.shoppingassistant.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final StoreService storeService;
    private final ModelMapper modelMapper;
    private final ActiveDiscountService activeDiscountService;

    public AdminController(UserService userService, StoreService storeService, ModelMapper modelMapper, ActiveDiscountService activeDiscountService) {
        this.userService = userService;
        this.storeService = storeService;
        this.modelMapper = modelMapper;
        this.activeDiscountService = activeDiscountService;
    }

    @GetMapping("/control-panel")
    public String controlPanel(Model model){

        List<String> registeredUsers = userService.getAllUsersUsernames();
        long countOfRegisteredUsers = userService.getCountOfAllUsers();

        List<String> storesInTheDatabase = storeService.getAllStoresNames();
        long countOfStoresInTheDatabase = storeService.getCountOfAllStores();

        List<StoreViewModel> allStores = this.storeService.getAllStores();
        long countAllDiscountedCategories = allStores.stream().filter(e -> e.getDiscountPercentage() > 0).count();

        List<ActiveDiscountViewModel> activeDiscountsForTheDay = this.activeDiscountService.getAllActiveDiscounts();
        long countActiveDiscounts = activeDiscountService.getCountOfAllActiveDiscounts();

        model.addAttribute("users", registeredUsers);
        model.addAttribute("allUsers", countOfRegisteredUsers);

        model.addAttribute("stores", storesInTheDatabase);
        model.addAttribute("allStores", countOfStoresInTheDatabase);

        model.addAttribute("allStoresDiscounts", allStores);
        model.addAttribute("allDiscounts",countAllDiscountedCategories);

        model.addAttribute("activeDiscounts",activeDiscountsForTheDay);
        model.addAttribute("activeDiscountsCount", countActiveDiscounts);

        return "admin";
    }

    @GetMapping("/add-new-store")
    public String addNewStore(Model model){

        if(!model.containsAttribute("storeAddBindingModel")){
            model.addAttribute("storeAddBindingModel", new StoreAddBindingModel());
            model.addAttribute("storeNameInUse", false);
        }

        return "add-new-store";
    }

    @PostMapping("/add-new-store")
    public String addNewStoreConfirm(@Valid StoreAddBindingModel storeAddBindingModel,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("storeAddBindingModel",storeAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.storeAddBindingModel",bindingResult);

            return "redirect:add-new-store";
        }

        this.storeService.saveAddedNewStore(modelMapper.map(storeAddBindingModel, StoreServiceModel.class));

        return "redirect:add-new-store";
    }

    @GetMapping("/change-category-discounts-in-store")
    public String changeCategoryDiscountsInStore(Model model){
        if(!model.containsAttribute("changeCategoryDiscountsInStoreBindingModel")){
            model.addAttribute("changeCategoryDiscountsInStoreBindingModel", new ChangeCategoryDiscountsInStoreBindingModel());
        }

        return "change-category-discounts-in-store";
    }

    @PostMapping("/change-category-discounts-in-store")
    public String changeCategoryDiscountsInStore(@Valid ChangeCategoryDiscountsInStoreBindingModel changeCategoryDiscountsInStoreBindingModel,
                                                 BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("changeCategoryDiscountsInStoreBindingModel",changeCategoryDiscountsInStoreBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.changeCategoryDiscountsInStoreBindingModel",bindingResult);

            return "redirect:change-category-discounts-in-store";
        }

        this.storeService.updateDiscountPercentagesOfProductCategoriesForTheStore(modelMapper.map(changeCategoryDiscountsInStoreBindingModel, ProductCategoriesDiscountChangesServiceModel.class));

        return "redirect:change-category-discounts-in-store";
    }

    @GetMapping("/re-roll-active-discount-tokens")
    public String reRollActiveDiscountTokens(@AuthenticationPrincipal ApplicationUserDetails userDetails){

        activeDiscountService.rollDiscountTokens(userDetails.getId());

        return "redirect:control-panel";
    }

}
