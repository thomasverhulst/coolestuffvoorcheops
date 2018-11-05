package com.cheops.candidatemanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
public class UserController {

  @Autowired
	private MessageSource messageSource;

  @GetMapping("/login")
	public String loginView(Locale locale, Model model, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
		// When user is anonymous authenticated, show login page, otherwise redirect /login to /.
		if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
		  // If login has params logout or error, show message.
      if (error != null) {
        model.addAttribute("errorMessage", messageSource.getMessage("form.error.login", null, locale));
      }
      if (logout != null) {
        model.addAttribute("successMessage", messageSource.getMessage("user.logout", null, locale));
      }
			return "user/login";
		} else {
			return "redirect:/";
		}
	}

	// Todo cleanup below

	// @RequestMapping(value="/registerCandidate", method=RequestMethod.POST,
	// params="action=back")
	// public String home() {
	// return "index";
	// }
	// @primary
	//@Autowired
	//CustomUserDetailsService customUserDetailsService;
	/// @Autowired
	/// UserService userService;

//	@RequestMapping(value = "/user")
//	public String registerUser(Model model) {
//		model.addAttribute("user", new User());
//		return "user/user";
//	}

//	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
//	public String doRegister(User user) {
//
////		User temp = user;
////		temp.setActive(1);
////
////		// tijdelijke fix iedereen admin
////		Role r = new Role();
////		r.setRole("ADMIN");
////		// new HashSet<String>()
////		Set<Role> t = new HashSet<Role>();
////		t.add(r);
////		temp.setRoles(t);
////
////		// temp.setId("Groet");
////		//customUserDetailsService.addUser(temp);
////		// userService.
//		return "redirect:/"; // redirect to homepage
//	}

//	@RequestMapping(value = "/signup", method = RequestMethod.GET)
//	public ModelAndView signup() {
//		ModelAndView modelAndView = new ModelAndView();
//		User user = new User();
//		modelAndView.addObject("user", user);
//		modelAndView.setViewName("signup");
//		return modelAndView;
//	}


//	@RequestMapping(value = "/signup", method = RequestMethod.POST)
//	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
//		ModelAndView modelAndView = new ModelAndView();
//		User userExists = userService.findUserByEmail(user.getEmail());
//		if (userExists != null) {
//			bindingResult
//					.rejectValue("email", "error.user",
//							"There is already a user registered with the username provided");
//		}
//		if (bindingResult.hasErrors()) {
//			modelAndView.setViewName("signup");
//		} else {
//			userService.saveUser(user);
//			modelAndView.addObject("successMessage", "User has been registered successfully");
//			modelAndView.addObject("user", new User());
//			modelAndView.setViewName("login");
//
//		}
//		return modelAndView;
//	}
}
