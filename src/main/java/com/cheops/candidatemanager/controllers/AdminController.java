package com.cheops.candidatemanager.controllers;

import com.cheops.candidatemanager.exceptions.UserAlreadyExistException;
import com.cheops.candidatemanager.exceptions.UserDoesNotExistException;
import com.cheops.candidatemanager.models.User;
import com.cheops.candidatemanager.services.IRoleService;
import com.cheops.candidatemanager.services.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller
@RequestMapping("/admin")
@SessionAttributes({AdminController.USER})
public class AdminController {

  static final String USER = "user";

  @Autowired
  private CustomUserDetailsService customUserDetailsService;

  @Autowired
  private IRoleService roleService;

  @Autowired
  private MessageSource messageSource;

  @GetMapping("")
  public String adminView(Model model) {
    model.addAttribute("users", customUserDetailsService.getAllUsers());
    model.addAttribute("user", new User());
    model.addAttribute("allRoles", roleService.getAllRoles());
    return "admin/settings";
  }

  @PostMapping("")
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
      redirectAttributes.addFlashAttribute("formErrorMessage", e.getMessage());
    }

    return "redirect:/admin";
  }


}
