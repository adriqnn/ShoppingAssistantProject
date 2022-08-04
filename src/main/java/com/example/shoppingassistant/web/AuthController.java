package com.example.shoppingassistant.web;

import com.example.shoppingassistant.model.binding.UserRegisterBindingModel;
import com.example.shoppingassistant.model.service.UserServiceModel;
import com.example.shoppingassistant.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class AuthController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public AuthController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login-error")
    public String onFailedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
                                RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("bad_credentials",true);

        return "redirect:login";
    }

    @GetMapping("/register")
    public String register(Model model){
        if(!model.containsAttribute("userRegisterBindingModel")){
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("usernameTaken", false);
            model.addAttribute("emailTaken",false);
            model.addAttribute("noMatch", false);
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirmAndLogin(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                          BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            if(!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword()) && !bindingResult.hasErrors()){
                redirectAttributes.addFlashAttribute("noMatch", true);
            }

            return "redirect:register";
        }

        UserServiceModel userServiceModel = modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        String userRegisterProcessOutcome = userService.register(userServiceModel);

        if(!userRegisterProcessOutcome.equals("userSaved")){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);

            if(userRegisterProcessOutcome.equals("bothUsernameAndEmailAreTaken")){
                redirectAttributes.addFlashAttribute("usernameTaken", true);
                redirectAttributes.addFlashAttribute("emailTaken", true);
            }else if(userRegisterProcessOutcome.equals("usernameTaken")){
                redirectAttributes.addFlashAttribute("usernameTaken", true);
            }else if(userRegisterProcessOutcome.equals("emailTaken")){
                redirectAttributes.addFlashAttribute("emailTaken", true);
            }

            return "redirect:register";
        }

        userService.login(userServiceModel);
        return "redirect:/";
    }




}
