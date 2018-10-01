package com.tv.tutorials.coolestuffvoorcheops.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OpenIdController {

	@RequestMapping("/openid")
	@ResponseBody
	public String home() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return "Welcome, " + username;
	}

}
