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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
public class AdminController {

  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private MessageSource messageSource;


  @GetMapping("/admin")
  public String adminView(Model model) {
    List<User> users = customUserDetailsService.getAllUsers();
    List<Role> allRoles = roleService.getAllRoles();
    model.addAttribute("users", users);
    model.addAttribute("user", new User());
    model.addAttribute("allRoles", allRoles);
    return "admin/settings";
  }

  @PostMapping("/admin")
  public String addUser(Locale locale, Model model, @Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      List<Role> allRoles = roleService.getAllRoles();
      model.addAttribute("allRoles", allRoles);
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

  @GetMapping("/admin/userdelete/{userId}")
  public String userDelete(Locale locale, @PathVariable("userId") User user, RedirectAttributes redirectAttributes) {
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
  public String userEditView(Locale locale, Model model, @PathVariable("userId") User user, RedirectAttributes redirectAttributes, HttpSession session) {
    if (user == null) {
      redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("user.doesNotExists", null, locale));
      return "redirect:/admin";
    }

    List<Role> allRoles = roleService.getAllRoles();
    model.addAttribute("user", user);
    model.addAttribute("allRoles", allRoles);
    return "user-edit";
  }

  @PostMapping("/admin/user-edit/{userId}")
  public String userEdit(Locale locale, @ModelAttribute User user, @PathVariable("userId") int userId, @RequestParam(value = "resetPassword", required = false) boolean resetPassword, RedirectAttributes redirectAttributes) {
    try {

      // (Re)Set password before saving cause it gets lost otherwise.
      if (resetPassword) {
        user.setPassword(user.getUsername());
      } else {
        User tempUser = customUserDetailsService.getUserById(userId);
        user.setPassword(tempUser.getPassword());
      }

      customUserDetailsService.saveUser(user);
      redirectAttributes.addFlashAttribute("successMessage", messageSource.getMessage("user.edited", new Object[]{user.getUsername()}, locale));
    } catch (final UserDoesNotExistException e) {
      redirectAttributes.addFlashAttribute("errorMessage", messageSource.getMessage("user.doesNotExists", null, locale));
    }

    return "redirect:/admin";
  }

}
