package com.cheops.candidatemanager.controllers;

import com.cheops.candidatemanager.models.User;
import com.cheops.candidatemanager.services.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginView() {
		// When user is anonymous authenticated, show login page, otherwise redirect /login to /.
		if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
			return "login";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@ModelAttribute Model login) {
		return "home";
	}

	@RequestMapping(value = "/403")
	public String accessDeniedView() {
		return "base/403";
	}

  @RequestMapping(value = "/admin")
  public String adminView(Model model) {
    List<User> users = customUserDetailsService.getAllUsers();
    model.addAttribute("users", users);
    return "admin/settings";
  }

  @RequestMapping(value = "/admin/user/edit/{userId}", method = RequestMethod.GET)
  public String userEditView(Model model, @PathVariable("userId") int userId) {
//    List<User> users = customUserDetailsService.getAllUsers();
//    model.addAttribute("users", users);
//    return "admin/settings";
    return "admin/user";
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
