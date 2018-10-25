package com.cheops.candidatemanager.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
  @RequestMapping(value = "login", method = RequestMethod.GET)
  public String loginView() {
    // When user is anonymous authenticated, show login page, otherwise redirect /login to /.
    if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
      return "login";
    } else {
      return "redirect:/";
    }
  }

  @RequestMapping(value = "login", method = RequestMethod.POST)
  public String login(@ModelAttribute Model login) {
    return "home";
  }
}