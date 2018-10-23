package com.tv.tutorials.coolestuffvoorcheops.controllers;

import com.tv.tutorials.coolestuffvoorcheops.models.User;
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
    return "login";
  }

  @RequestMapping(value = "login", method = RequestMethod.POST)
  public String login(@ModelAttribute Model login) {
    return "home";
  }

  @RequestMapping(value = "/")
  public String home(Model model) {
    User currentUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    model.addAttribute("user", currentUser);
    return "index";
  }
}