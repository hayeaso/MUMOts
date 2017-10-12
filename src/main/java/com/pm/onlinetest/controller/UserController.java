package com.pm.onlinetest.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pm.onlinetest.domain.User;
import com.pm.onlinetest.service.UserService;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	UserService userService;
	
	@RequestMapping(value = {"/*/editProfile"}, method = RequestMethod.GET)
	public String editProfile(@ModelAttribute("loginUser") User user, Model model){
		System.out.println("GET: enter into EditProfile	");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User currentUser = userService.findByUsername(name);
		model.addAttribute("currentUser", currentUser);
		return "redirect:/editProfile/" + currentUser.getUserId();
	}
	
	@RequestMapping(value = "/editProfile/{id}", method = RequestMethod.GET)
	public String editUser(@ModelAttribute("loginUser") User user, Model model, @PathVariable("id") int id) {
		System.out.println("GET: enter into Edit User	");
		System.out.println("userid=" + id);
		model.addAttribute("user", userService.findByUserId(id));// assign user										
		return "editProfile";
	}

	
	@RequestMapping(value = "/editProfile", method = RequestMethod.POST)
	public String editUser(@Valid @ModelAttribute("loginUser") User user, BindingResult result,
			RedirectAttributes redirectAttr) {
		System.out.println("POST: enter into Edit User");
		if (result.hasErrors()) {
			return "editProfile";
		}

		if (null != userService.findByUsernameExceptThis(user.getUsername(), user.getUserId())) {
			redirectAttr.addFlashAttribute("error", "Error");
		} else {
			user.setEnabled(true);
			userService.saveProfile(user);
			redirectAttr.addFlashAttribute("success", "Success");
		}
		return "redirect:/editProfile/" + user.getUserId();
	}

	


}
