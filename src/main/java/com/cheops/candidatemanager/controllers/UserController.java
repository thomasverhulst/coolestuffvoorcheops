package com.cheops.candidatemanager.controllers;

import com.cheops.candidatemanager.models.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {


	@GetMapping(value = "login")
	public String loginView() {
		// When user is anonymous authenticated, show login page, otherwise redirect /login to /.
		if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
			return "login";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/settings")
	public String registerUser(HttpServletRequest request) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			return "admin/settings";
		} else {
			return "base/403";
		}
	}

	@PostMapping(value = "login")
	public String login(@ModelAttribute Model login) {
		return "home";
	}



	@RequestMapping(value = "/user")
	public String registerUser(Model model) {
		model.addAttribute("user", new User());
		return "user/user";
	}

	@PostMapping(value = "/registeruser")
	public String doRegister(User user) {


//		//customUserDetailsService.addUser(temp);
//		// userService.
		return "redirect:/"; // redirect to homepage
	}


}
