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
import com.pm.onlinetest.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	 @Autowired
	    private MailSender mailSender;

	
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
	
//	ADD Anita
	@RequestMapping(value = "/postResetPassword", method = RequestMethod.POST)
	public String forgetPass(@ModelAttribute("loginUser") User user, Model model) {
	
		String email =user.getEmail();

		if(userService.emailExists(user.getEmail())){
			int id= userService.findByUseremail(email);
			System.out.println(id);
			
			SimpleMailMessage message = new SimpleMailMessage();
			  message.setTo(email);
//			  System.out.println("EMAIL: " + email);
			   message.setReplyTo("false");
			   message.setFrom("mumtestlink@gmail.com");
			    message.setSubject("Test Link");
			    message.setText("You have requested to reset your password for your account. To get started, please click this link." + "Access Link: "+"http://localhost:8080/onlinetest/resetPassword/"+id);
			   
			    mailSender.send(message);
			    String result ="success";
			    
//			    System.out.println("result");
			
		}else {
			
		System.out.println("Not Found");
	}
		return "login";
	}
	
	@RequestMapping(value = "/resetPassword/{id}", method = RequestMethod.GET)
	public String resetPassword(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
		
		System.out.println("ID:"+ id);
		
		return "resetPassword";
	}
	
//	@RequestMapping(value = "/resetPassword/{id}", method = RequestMethod.POST)
//	public int updatePassword(HttpServletRequest request) {
//		
//		String id = request.getParameter("userid").toString();
//		return userService.updatepassword(Integer.parseInt(id));
//			
//	}
	

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}
	
	
	
	


	
}
