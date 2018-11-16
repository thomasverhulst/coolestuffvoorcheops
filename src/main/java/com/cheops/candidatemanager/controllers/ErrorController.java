package com.cheops.candidatemanager.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

  @RequestMapping("/error")
  public String handleError(HttpServletRequest request) {
    Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

    if (status != null) {
      int statusCode = Integer.parseInt(status.toString());

      if(statusCode == HttpStatus.FORBIDDEN.value()) {
        return "base/403";
      } else if(statusCode == HttpStatus.NOT_FOUND.value()) {
        return "base/404";
      } else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
        return "base/500";
      }
    }

    return "base/error";
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }

}
