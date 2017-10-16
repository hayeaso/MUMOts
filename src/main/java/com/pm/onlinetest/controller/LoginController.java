package com.pm.onlinetest.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pm.onlinetest.domain.Student;
import com.pm.onlinetest.domain.User;
import com.pm.onlinetest.service.AssignmentService;
import com.pm.onlinetest.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	 @Autowired
	    private MailSender mailSender;
	 
	 @Autowired
		AssignmentService assignmentService;

	
	@RequestMapping(value = "/exam", method = RequestMethod.GET)
	public String exam(HttpServletRequest request) {
		request.getSession().setAttribute("min", 2);
		request.getSession().setAttribute("sec", 59);
		return "exam";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(@ModelAttribute("loginUser") User user, Model model) {
		model.addAttribute("error", "true");
		return "login";

	}
	
//	ADD by Anita
	@RequestMapping(value = "/postResetPassword", method = RequestMethod.POST)
	public String forgetPass(@ModelAttribute("loginUser") User user, Model model, RedirectAttributes redirectAttributes) {
	
		String email =user.getEmail();

		if(userService.emailExists(user.getEmail())){
			
			String accessCode = assignmentService.generateAccesscode();
			userService.setAccessCodeInPasswordField(email,accessCode);
			
			SimpleMailMessage message = new SimpleMailMessage();
			  message.setTo(email);

			   message.setReplyTo("false");
			   message.setFrom("mumtestlink@gmail.com");
			    message.setSubject("Test Link");
			    message.setText("You have requested to reset your password for your account. To get started, please click this link." + "Access Link: "+"http://localhost:8080/onlinetest/resetPassword/"+accessCode);
			   
			    mailSender.send(message);
			    String result ="success";
			    redirectAttributes.addFlashAttribute("foundEmail", email);
			    System.out.println("IT HAS TO BE RE_DIRECTED");
			    return "redirect:/login";
			
		}else { 
			//System.out.println("NOT FOUND");
			redirectAttributes.addFlashAttribute("notFoundEmail", email);	
			return "redirect:/login";
		}
	

	}
	
	@RequestMapping(value = "/resetPassword/{accessCode}", method = RequestMethod.GET)
	public String resetPassword(Locale locale,Model model, HttpServletRequest request, HttpServletResponse response , @PathVariable("accessCode") String accessCode) {
		
		System.out.println("GET ACCESS IN RESET:"+ accessCode);
		model.addAttribute("accessCode", accessCode);
		
		return "resetPassword";
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String updatePassword(Model model, HttpServletRequest request,RedirectAttributes redirectAttr) {
		
		String userAccessCode = request.getParameter("userAccessCode").toString();
		String newPassword = request.getParameter("newpassword");
		
		User user = userService.findUserByAccessCode(userAccessCode);
		
		if(user != null) {
		user.setPassword(newPassword);
		userService.saveProfile(user);
		
		redirectAttr.addFlashAttribute("changeSuccess", "ChangeSuccess");	
		redirectAttr.addFlashAttribute("titleMessage", "Password Changed");	
		redirectAttr.addFlashAttribute("bodyMessage", "New Password Updated SuccessFully.");
		
		return "redirect:/login";
		}else {
			
			System.out.println("HERE GOES CODE FOR THE ACCES CODE VALIDITY OFF.");
			
			return "redirect:/login";
		}
	}
	

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}
	
	
	
	


	
}
