package com.cheops.candidatemanager.controllers;

import com.cheops.candidatemanager.exceptions.UserAlreadyExistException;
import com.cheops.candidatemanager.exceptions.UserDoesNotExistException;
import com.cheops.candidatemanager.models.Role;
import com.cheops.candidatemanager.models.User;
import com.cheops.candidatemanager.services.impl.CustomUserDetailsService;
import com.cheops.candidatemanager.services.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
@SessionAttributes({AdminController.USER})
public class AdminController {

  static final String USER = "user";

  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private MessageSource messageSource;

  @GetMapping("/admin")
  public String adminView(Model model) {
    model.addAttribute("users", customUserDetailsService.getAllUsers());
    model.addAttribute("user", new User());
    model.addAttribute("allRoles", roleService.getAllRoles());
    return "admin/settings";
  }

  @PostMapping("/admin")
  public String addUser(Locale locale, Model model, @Validated @ModelAttribute("user") User user, BindingResult result, RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      model.addAttribute("allRoles", roleService.getAllRoles());
      model.addAttribute("formErrorMessage", messageSource.getMessage("form.error.submission", null, locale));
      return "admin/settings";
    }

    try {
      User newUser = customUserDetailsService.addUser(user);
      redirectAttributes.addFlashAttribute("formSuccessMessage", messageSource.getMessage("user.added", new Object[]{newUser.getName(), newUser.getLastName()}, locale));
    } catch (final UserAlreadyExistException e) {
      redirectAttributes.addFlashAttribute("formErrorMessage", messageSource.getMessage("user.alreadyExists", new Object[]{user.getUsername()}, locale));
    }

    return "redirect:/admin";
  }

  @GetMapping("/admin/user-delete/{userId}")
  public String deleteUser(Locale locale, @PathVariable("userId") User user, RedirectAttributes redirectAttributes) {
    if (user == null) {
      redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("user.doesNotExists", null, locale));
      return "redirect:/admin";
    }

    try {
      customUserDetailsService.deleteUser(user);
      redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("user.deleted", new Object[]{user.getUsername()}, locale));
    } catch (final UserDoesNotExistException e) {
      redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("user.doesNotExists", null, locale));
    }

    return "redirect:/admin";
  }

  @GetMapping("/admin/user-edit/{userId}")
  public String editUserView(Locale locale, Model model, @PathVariable("userId") User user, RedirectAttributes redirectAttributes) {
    if (user == null) {
      redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("user.doesNotExists", null, locale));
      return "redirect:/admin";
    }

    model.addAttribute(USER, user);
    model.addAttribute("allRoles", roleService.getAllRoles());
    return "admin/user-edit";
  }

  @PostMapping("/admin/user-edit/{userId}")
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
      redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("user.doesNotExists", null, locale));
    }

    return "redirect:/admin";
  }

}
