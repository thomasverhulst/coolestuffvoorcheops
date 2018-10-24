package com.cheops.candidatemanager.controllers;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageOfTheDayController {
	@GetMapping("/mod")
	@PreAuthorize("hasAuthority('Everyone') || #oauth2.hasScope('email')")
	public String getMessageOfTheDay(Principal principal) {
		return "The message of the day is boring for user: " + principal.getName();
	}
}
