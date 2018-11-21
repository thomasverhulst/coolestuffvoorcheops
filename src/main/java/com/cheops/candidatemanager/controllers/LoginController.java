package com.cheops.candidatemanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
public class LoginController {

  @Autowired
  private MessageSource messageSource;

  @GetMapping("/login")
  public String loginView(Locale locale, Model model, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
    // When user is anonymous authenticated, show login page, otherwise redirect /login to /.
    if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
      // If login has params logout or error, show message.
      if (error != null) model.addAttribute("errorMessage", messageSource.getMessage("form.error.login", null, locale));
      if (logout != null) model.addAttribute("successMessage", messageSource.getMessage("user.logout", null, locale));
      return "user/login";
    } else {
      return "redirect:/";
    }
  }

}
