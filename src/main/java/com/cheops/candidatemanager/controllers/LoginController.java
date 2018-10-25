package com.cheops.candidatemanager.controllers;

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
    // When user is authenticated, redirect the /login page to index, otherwise show /login page.
    if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
      return "redirect:/";

    } else {
      return "login";
    }
  }

  @RequestMapping(value = "login", method = RequestMethod.POST)
  public String login(@ModelAttribute Model login) {
    return "home";
  }
}