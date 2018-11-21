package com.cheops.candidatemanager.controllers;

import com.cheops.candidatemanager.exceptions.UserDoesNotExistException;
import com.cheops.candidatemanager.models.User;
import com.cheops.candidatemanager.services.IRoleService;
import com.cheops.candidatemanager.services.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller
@RequestMapping("/user")
@SessionAttributes({AdminController.USER})
public class UserController {

  static final String USER = "user";

  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Autowired
  private IRoleService roleService;

  @Autowired
	private MessageSource messageSource;

//  @GetMapping("/login")
//	public String loginView(Locale locale, Model model, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
//		// When user is anonymous authenticated, show login page, otherwise redirect /login to /.
//		if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
//		  // If login has params logout or error, show message.
//      if (error != null) {
//        model.addAttribute("errorMessage", messageSource.getMessage("form.error.login", null, locale));
//      }
//      if (logout != null) {
//        model.addAttribute("successMessage", messageSource.getMessage("user.logout", null, locale));
//      }
//			return "user/login";
//		} else {
//			return "redirect:/";
//		}
//	}

	@GetMapping("")
  public String userView(Model model, @AuthenticationPrincipal UserDetails currentUser) {
    User user = customUserDetailsService.getUserByUsername(currentUser.getUsername());
    model.addAttribute("user", user);
    return "user/user";
  }

  @GetMapping("/change-password")
  public String changePasswordView(Model model, @AuthenticationPrincipal UserDetails currentUser) {
    User user = customUserDetailsService.getUserByUsername(currentUser.getUsername());
    model.addAttribute("user", user);
    return "user/change-password";
  }

  @PostMapping("/change-password")
  public String changePassword() {
    // Todo: change password + error + success handlers
    return "user";
  }

  @GetMapping("/delete/{userId}")
  public String deleteUser(Locale locale, @PathVariable("userId") User user, RedirectAttributes redirectAttributes) {
    if (user == null) {
      redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("user.doesNotExist", null, locale));
      return "redirect:/admin";
    }

    try {
      customUserDetailsService.deleteUser(user);
      redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("user.deleted", new Object[]{user.getUsername()}, locale));
    } catch (final UserDoesNotExistException e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }

    return "redirect:/admin";
  }

  @GetMapping("/edit/{userId}")
  public String editUserView(Locale locale, Model model, @PathVariable("userId") User user, RedirectAttributes redirectAttributes) {
    if (user == null) {
      redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("user.doesNotExist", null, locale));
      return "redirect:/admin";
    }

    model.addAttribute(USER, user);
    model.addAttribute("allRoles", roleService.getAllRoles());
    return "user/edit";
  }

  @PostMapping("/edit/{userId}")
  public String saveUser(Locale locale, Model model, @Validated @ModelAttribute(USER) User user, BindingResult result, @RequestParam(value = "resetPassword", required = false) boolean resetPassword, RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      model.addAttribute("allRoles", roleService.getAllRoles());
      model.addAttribute("formErrorMessage", messageSource.getMessage("form.error.submission", null, locale));
      return "admin/settings";
    }

    try {
      // ReSet password if checked.
      if (resetPassword) user.setPassword(user.getUsername());
      customUserDetailsService.saveUser(user);
      redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("user.edited", new Object[]{user.getUsername()}, locale));
    } catch (final UserDoesNotExistException e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }

    return "redirect:/admin";
  }


}
