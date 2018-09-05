package com.tv.tutorials.coolestuffvoorcheops.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;

public class MessageOfTheDayController {
	@GetMapping("/mod")
	   public String getMessageOfTheDay(Principal principal) {
	       return "The message of the day is boring for user: " + principal.getName();
	   }
}
