package com.example.shoppingassistant.web;

import com.example.shoppingassistant.model.binding.*;
import com.example.shoppingassistant.model.service.UserServiceModel;
import com.example.shoppingassistant.model.userDetails.ApplicationUserDetails;
import com.example.shoppingassistant.model.view.UserViewModel;
import com.example.shoppingassistant.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public ProfileController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public String showProfile(@PathVariable("id") Long userId, Model model, @AuthenticationPrincipal ApplicationUserDetails userDetails){

        UserViewModel userViewModelByAuthenticatedId = userService.getUserViewModel(userDetails.getId());
        model.addAttribute("user", userViewModelByAuthenticatedId);

        return "profile";
    }

    @GetMapping("/{id}/change-username")
    public String changeUsername(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal ApplicationUserDetails userDetails){
        if(!model.containsAttribute("usernameChangeBindingModel")){
            model.addAttribute("usernameChangeBindingModel", new UsernameChangeBindingModel());
        }
        return "change-username";
    }

    @PostMapping("/{id}/change-username")
    public String changeUsernameConfirm(@PathVariable("id")Long id, @Valid UsernameChangeBindingModel usernameChangeBindingModel,
                                        BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                        @AuthenticationPrincipal ApplicationUserDetails userDetails){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("usernameChangeBindingModel", usernameChangeBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.usernameChangeBindingModel", bindingResult);
            return String.format("redirect:/profile/%d/change-username",userDetails.getId());
        }

        userService.saveUsernameChanges(modelMapper.map(usernameChangeBindingModel, UserServiceModel.class), userDetails.getId());

        return "redirect:/profile/" + userDetails.getId();
    }

    @GetMapping("/{id}/change-password")
    public String changePassword(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal ApplicationUserDetails userDetails){
        if(!model.containsAttribute("passwordChangeBindingModel")){
            model.addAttribute("passwordChangeBindingModel", new PasswordChangeBindingModel());
            model.addAttribute("notMatching", false);
        }
        return "change-password";
    }

    @PostMapping("/{id}/change-password")
    public String changePasswordConfirm(@PathVariable("id")Long id, @Valid PasswordChangeBindingModel passwordChangeBindingModel,
                                        BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                        @AuthenticationPrincipal ApplicationUserDetails userDetails){
        if(bindingResult.hasErrors() || !passwordChangeBindingModel.getNewPassword().equals(passwordChangeBindingModel.getConfirmNewPassword())){
            redirectAttributes.addFlashAttribute("passwordChangeBindingModel", passwordChangeBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.passwordChangeBindingModel", bindingResult);

            if(!passwordChangeBindingModel.getNewPassword().equals(passwordChangeBindingModel.getConfirmNewPassword())){
                redirectAttributes.addFlashAttribute("notMatching", true);
            }

            return String.format("redirect:/profile/%d/change-password",userDetails.getId());
        }

        userService.savePasswordChanges(passwordChangeBindingModel.getNewPassword(), userDetails.getId());

        return "redirect:/profile/" + userDetails.getId();
    }

    @GetMapping("/{id}/change-firstname")
    public String changeFirstName(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal ApplicationUserDetails userDetails){
        if(!model.containsAttribute("changeFirstNameBindingModel")){
            model.addAttribute("changeFirstNameBindingModel", new ChangeFirstNameBindingModel());
        }
        return "change-firstname";
    }

    @PostMapping("/{id}/change-firstname")
    public String changeFirstNameConfirm(@PathVariable("id")Long id, @Valid ChangeFirstNameBindingModel changeFirstNameBindingModel,
                                         BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                         @AuthenticationPrincipal ApplicationUserDetails userDetails){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("changeFirstNameBindingModel", changeFirstNameBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.changeFirstNameBindingModel",bindingResult);

            return String.format("redirect:/profile/%d/change-firstname",userDetails.getId());
        }

        userService.saveFirstNameChanges(modelMapper.map(changeFirstNameBindingModel, UserServiceModel.class), userDetails.getId());

        return "redirect:/profile/" + userDetails.getId();
    }

    @GetMapping("/{id}/change-email")
    public String changeEmail(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal ApplicationUserDetails userDetails){
        if(!model.containsAttribute("emailChangeBindingModel")){
            model.addAttribute("emailChangeBindingModel", new EmailChangeBindingModel());
        }

        return "change-email";
    }

    @PostMapping("/{id}/change-email")
    public String changeEmailConfirm(@PathVariable("id")Long id, @Valid EmailChangeBindingModel emailChangeBindingModel,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                     @AuthenticationPrincipal ApplicationUserDetails userDetails){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("emailChangeBindingModel", emailChangeBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.emailChangeBindingModel", bindingResult);

            return String.format("redirect:/profile/%d/change-email",userDetails.getId());
        }
        userService.saveEmailChanges(emailChangeBindingModel.getEmail(), userDetails.getId());

        return "redirect:/profile/" + userDetails.getId();
    }

    @GetMapping("/{id}/change-picture")
    public String changePicture(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal ApplicationUserDetails userDetails){
        if(!model.containsAttribute("profilePictureChangeBindingModel")){
            model.addAttribute("profilePictureChangeBindingModel", new ProfilePictureChangeBindingModel());
        }

        return "change-picture";
    }

    @PostMapping("/{id}/change-picture")
    public String changePictureConfirm(@PathVariable("id") Long id, @Valid ProfilePictureChangeBindingModel profilePictureChangeBindingModel,
                                       BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                       @AuthenticationPrincipal ApplicationUserDetails userDetails){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("profilePictureChangeBindingModel", profilePictureChangeBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.profilePictureChangeBindingModel",bindingResult);

            return String.format("redirect:/profile/%d/change-picture",userDetails.getId());
        }

        userService.saveProfilePictureChanges(profilePictureChangeBindingModel.getImageUrl(),userDetails.getId());

        return "redirect:/profile/" + userDetails.getId();
    }


}





















