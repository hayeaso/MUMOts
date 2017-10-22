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

	@RequestMapping(value = { "/editProfile", }, method = RequestMethod.GET)
	public String editProfileFromOnlinetest(@ModelAttribute("loginUser") User user, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User currentUser = userService.findByUsername(name);
		model.addAttribute("user", currentUser);
		String role = currentUser.getAuthorities().get(0).getAuthority();
		if (role.equals("ROLE_COACH"))
			return "redirect:/coach/editProfile";
		if (role.equals("ROLE_ADMIN"))
			return "redirect:/admin/editProfile";
		return "redirect:/dba/editProfile";
	}

	@RequestMapping(value = { "/admin/editProfile", }, method = RequestMethod.GET)
	public String editProfileFromAdmin(@ModelAttribute("loginUser") User user, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User currentUser = userService.findByUsername(name);
		model.addAttribute("user", currentUser);
		return "editProfileFromAdmin";
	}

	@RequestMapping(value = { "/admin/editUser/editProfile",
			"/admin/editStudent/editProfile" }, method = RequestMethod.GET)
	public String redirectFromAdmin(@ModelAttribute("loginUser") User user, Model model) {
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
			redirectAttr.addFlashAttribute("titleMessage", "User EDITED");
			redirectAttr.addFlashAttribute("bodyMessage", "User " + user.getUsername() + " successfully edited!");
		}
		return "redirect:/admin/editProfile";
	}

	@RequestMapping(value = { "/coach/editProfile" }, method = RequestMethod.GET)
	public String editProfileFromCoach(@ModelAttribute("loginUser") User user, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User currentUser = userService.findByUsername(name);
		model.addAttribute("user", currentUser);
		return "editProfileFromCoach";
	}

	@RequestMapping(value = { "/coach/studentAssignmentDetail/editProfile", "coach/editStudent/editProfile",
			"coach/studentAssignmentHistory/editProfile" }, method = RequestMethod.GET)
	public String redirectForCoach(@ModelAttribute("loginUser") User user, Model model) {
		return "redirect:/coach/editProfile";
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
			redirectAttr.addFlashAttribute("titleMessage", "User EDITED");
			redirectAttr.addFlashAttribute("bodyMessage", "User " + user.getUsername() + " successfully edited!");
		}
		return "redirect:/coach/editProfile";
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
			redirectAttr.addFlashAttribute("titleMessage", "User EDITED");
			redirectAttr.addFlashAttribute("bodyMessage", "User " + user.getUsername() + " successfully edited!");
		}
		return "redirect:/dba/editProfile";
	}

}
