package com.cheops.candidatemanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
  @RequestMapping(value = "/settings")
  public String registerUser(HttpServletRequest request) {
    if (request.isUserInRole("ROLE_ADMIN")) {
      return "admin/settings";
    } else {
      return "base/403";
    }
  }
}
