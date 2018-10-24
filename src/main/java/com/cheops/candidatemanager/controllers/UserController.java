package com.cheops.candidatemanager.controllers;

import java.util.HashSet;
import java.util.Set;

import com.cheops.candidatemanager.models.Role;
import com.cheops.candidatemanager.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cheops.candidatemanager.services.impl.CustomUserDetailService;

//import com.cheops.candidatemanager.services.UserService;
@Controller
public class UserController {

	// @RequestMapping(value="/registerCandidate", method=RequestMethod.POST,
	// params="action=back")
	// public String home() {
	// return "index";
	// }
	// @primary
	@Autowired
	CustomUserDetailService customUserDetailsService;
	/// @Autowired
	/// UserService userService;

	@RequestMapping(value = "/user")
	public String registerUser(Model model) {
		model.addAttribute("user", new User());
		return "user";
	}

	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	public String doRegister(User user) {

		User temp = user;
		temp.setActive(1);

		// tijdelijke fix iedereen admin
		Role r = new Role();
		r.setRole("ADMIN");
		// new HashSet<String>()
		Set<Role> t = new HashSet<Role>();
		t.add(r);
		temp.setRoles(t);

		// temp.setId("Groet");
		customUserDetailsService.addUser(temp);
		// userService.
		return "redirect:/"; // redirect to homepage
	}

}
