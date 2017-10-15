package com.pm.onlinetest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pm.onlinetest.domain.User;
import com.pm.onlinetest.service.UserService;

@Controller
public class ProfileController {
	@Autowired
	UserService userService;

	@RequestMapping(value = { "/admin/editProfile", }, method = RequestMethod.GET)
	public String editProfileFromAdmin(@ModelAttribute("loginUser") User user, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User currentUser = userService.findByUsername(name);
		model.addAttribute("user", currentUser);
		return "editProfileFromAdmin";
	}
	
	
	@RequestMapping(value = { "/admin/editUser/editProfile", }, method = RequestMethod.GET)
	public String  redirect(@ModelAttribute("loginUser") User user, Model model) {
	    System.out.println("in redirect method");
		return "redirect:/admin/editProfile";
	}


	@RequestMapping(value = "/admin/editProfile", method = RequestMethod.POST)
	public String editProfileFromAdmin(@Valid @ModelAttribute("loginUser") User user, BindingResult result,
			RedirectAttributes redirectAttr) {
		if (result.hasErrors()) {
			return "editProfileFromAdmin";
		}

		if (null != userService.findByUsernameExceptThis(user.getUsername(), user.getUserId())) {
			redirectAttr.addFlashAttribute("error", "Error");
		} else {
			user.setEnabled(true);
			userService.saveProfile(user);
			redirectAttr.addFlashAttribute("success", "Success");
		}
		return "editProfileFromAdmin";
	}
	
	@RequestMapping(value = { "/coach/editProfile" }, method = RequestMethod.GET)
	public String editProfileFromCoach(@ModelAttribute("loginUser") User user, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User currentUser = userService.findByUsername(name);
		model.addAttribute("user", currentUser);
		return "editProfileFromCoach";
	}

	@RequestMapping(value = "/coach/editProfile", method = RequestMethod.POST)
	public String editProfileFromCoach(@Valid @ModelAttribute("loginUser") User user, BindingResult result,
			RedirectAttributes redirectAttr) {
		if (result.hasErrors()) {
			return "editProfileFromCoach";
		}

		if (null != userService.findByUsernameExceptThis(user.getUsername(), user.getUserId())) {
			redirectAttr.addFlashAttribute("error", "Error");
		} else {
			user.setEnabled(true);
			userService.saveProfile(user);
			redirectAttr.addFlashAttribute("success", "Success");
		}
		 return "editProfileFromCoach";
	}
	
	@RequestMapping(value = { "/dba/editProfile" }, method = RequestMethod.GET)
	public String editProfileFromDba(@ModelAttribute("loginUser") User user, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User currentUser = userService.findByUsername(name);
		model.addAttribute("user", currentUser);
		return "editProfileFromDba";
	}

	@RequestMapping(value = "/dba/editProfile", method = RequestMethod.POST)
	public String editProfileFromDba(@Valid @ModelAttribute("loginUser") User user, BindingResult result,
			RedirectAttributes redirectAttr) {
		if (result.hasErrors()) {
			return "editProfileFromDba";
		}

		if (null != userService.findByUsernameExceptThis(user.getUsername(), user.getUserId())) {
			redirectAttr.addFlashAttribute("error", "Error");
		} else {
			user.setEnabled(true);
			userService.saveProfile(user);
			redirectAttr.addFlashAttribute("success", "Success");
		}
		 return "editProfileFromDba";
	}

}
