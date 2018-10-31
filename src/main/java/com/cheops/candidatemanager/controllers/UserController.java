package com.cheops.candidatemanager.controllers;

import com.cheops.candidatemanager.models.Role;
import com.cheops.candidatemanager.models.User;
import com.cheops.candidatemanager.services.impl.CustomUserDetailsService;
import com.cheops.candidatemanager.services.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@Controller
public class UserController {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
  private RoleService roleService;

  @Autowired
	private MessageSource messageSource;

  @GetMapping("/login")
	public String loginView(Locale locale, Model model, @RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
		// When user is anonymous authenticated, show login page, otherwise redirect /login to /.
		if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
		  // If login has params logout or error, show message.
      if (error != null) {
        model.addAttribute("errorMessage", messageSource.getMessage("error.login", null, locale));
      }
      if (logout != null) {
        model.addAttribute("successMessage", messageSource.getMessage("success.logout", null, locale));
      }
			return "user/login";
		} else {
			return "redirect:/";
		}
	}

  @GetMapping("/admin")
  public String adminView(Model model) {
    List<User> users = customUserDetailsService.getAllUsers();
    List<Role> allRoles = roleService.getAllRoles();
    model.addAttribute("users", users);
    model.addAttribute("user", new User());
    model.addAttribute("allRoles", allRoles);
    return "admin/settings";
  }

  @PostMapping("/admin/adduser")
  public String addUser(Locale locale, Model model, @Valid User user, BindingResult result) {
//    if (result.hasErrors()) {
//      model.addAttribute("errorMessage", messageSource.getMessage("error.submissionError", null, locale));
//      return "admin/settings";
//    }
//
//    customUserDetailsService.saveUser(user);
//    model.addAttribute("successMessage", messageSource.getMessage("success.addUser", new Object[] {user.getName(), user.getLastName()}, locale));
    return "admin/settings";
  }

  @GetMapping("/admin/user/edit/{userId}")
  public String userEditView(Model model, @PathVariable("userId") User user) {
	  List<Role> allRoles = roleService.getAllRoles();
	  model.addAttribute("user", user);
	  model.addAttribute("allRoles", allRoles);
    return "user/edit";
  }

//  @PostMapping("/admin/user/edit/{userId}")
//  public String updateUser(Locale locale, Model model, @Valid User user, BindingResult result, HttpSession session) {
//	  if (result.hasErrors()) {
//      model.addAttribute("errorMessage", messageSource.getMessage("error.submissionError", null, locale));
//      return "user/edit";
//    }
//    //result.get
//    user.setId(user.getId());
//	  User tmp = (User) session.getAttribute("user");
//    user.setPassword(tmp.getPassword());
//	  customUserDetailsService.updateUser(user);
//    model.addAttribute("successMessage", messageSource.getMessage("success.editUser", new Object[] {user.getName(), user.getLastName()}, locale));
//    return "user/edit";
//  }


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
