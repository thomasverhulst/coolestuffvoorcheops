package com.tv.tutorials.coolestuffvoorcheops.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/test")
	public String showtest() {
		return "test";
	}
}
