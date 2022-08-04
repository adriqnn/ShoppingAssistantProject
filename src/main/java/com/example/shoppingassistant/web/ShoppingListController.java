package com.example.shoppingassistant.web;

import com.example.shoppingassistant.model.binding.ProductAddBindingModel;
import com.example.shoppingassistant.model.entity.Product;
import com.example.shoppingassistant.model.service.ProductServiceModel;
import com.example.shoppingassistant.model.userDetails.ApplicationUserDetails;
import com.example.shoppingassistant.model.view.ProductViewModel;
import com.example.shoppingassistant.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/shoppingList")
public class ShoppingListController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ShoppingListController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/profile/{id}")
    public String profileShoppingList(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal ApplicationUserDetails userDetails){

        List<ProductViewModel> shoppingListProductsByUser = productService.getAllProductsInUsersShoppingList(userDetails.getUsername());
        BigDecimal totalSum = productService.getTotalSum(userDetails.getUsername());

        model.addAttribute("shoppingList", shoppingListProductsByUser);
        model.addAttribute("totalSum", totalSum);

        return "shopping-list";
    }

    @GetMapping("/add-groceries")
    public String addGroceries(Model model){
        if(!model.containsAttribute("productAddBindingModel")){
            model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
        }
        return "add-groceries";
    }

    @PostMapping("/add-groceries")
    public String addGroceriesConfirm(@Valid ProductAddBindingModel productAddBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes,
                               @AuthenticationPrincipal UserDetails userDetails){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel", bindingResult);
            return "redirect:add-groceries";
        }

        productService.addProduct(modelMapper.map(productAddBindingModel, ProductServiceModel.class), userDetails.getUsername());

        return "redirect:add-groceries";
    }

    @GetMapping("/remove-one-item/{id}")
    public String removeOneItemFromTheList(@PathVariable("id") Long itemId, @AuthenticationPrincipal ApplicationUserDetails userDetails){

        boolean productHasBeenDeleted = productService.removeOneItemFromTheList(userDetails.getUsername(), itemId);

        return String.format("redirect:/shoppingList/profile/%d",userDetails.getId());
    }

    @GetMapping("/remove-all-items")
    public String removeAllItemsFromTheList(@AuthenticationPrincipal ApplicationUserDetails userDetails){
        productService.removeAllItemsFromTheList(userDetails.getUsername());

        return String.format("redirect:profile/%d",userDetails.getId());
    }

}
