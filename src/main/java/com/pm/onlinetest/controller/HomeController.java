package com.pm.onlinetest.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pm.onlinetest.domain.User;
import com.pm.onlinetest.domain.Authority;
import com.pm.onlinetest.domain.Contactus;
import com.pm.onlinetest.service.AuthorityService;
import com.pm.onlinetest.service.ContactUsService;
import com.pm.onlinetest.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	ContactUsService contactService;
	
	@Autowired
	UserService userService;
	@Autowired
	AuthorityService authorityService;
	@Autowired
	private MailSender mailSender;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { "/", "home" }, method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		logger.debug("HomeController.clazz home() ");		
		return request.getRequestURI().indexOf("home") != -1 ? "home" : this.getAccessUrlRoleBase(request, false);
	}
	@RequestMapping(value = { "/contactus" }, method = RequestMethod.GET)
	public String coantactus(@ModelAttribute("contactus")Contactus contactus) {
		return "contactus";
	}
	@RequestMapping(value = { "/help" }, method = RequestMethod.GET)
	public String help() {
		return "help";
	}
	
	@RequestMapping(value = { "/contactus" }, method = RequestMethod.POST)
	public String coantacted(@ModelAttribute("contactus")Contactus contactus, Model model) {
		if (sendEmail(contactus.getEmail(), "MUM Self Assessment - Contact Us", contactus.getComment() + "\r\n \r\nRegards, \r\n \r\n" + contactus.getName(), mailSender)) {
			contactService.save(contactus);			
			model.addAttribute("success", "Success");
			model.addAttribute("contactus", new Contactus());
		} else {
			model.addAttribute("error", "Error");			
		}		
		return "contactus";
	}

	@RequestMapping(value = "/rolecheck", method = RequestMethod.GET)
	public String roleCheck(Locale locale, Model model, HttpServletRequest request) {
		return this.getAccessUrlRoleBase(request, true);
	}
	
	private String getAccessUrlRoleBase(HttpServletRequest request, boolean isFromLogin) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		User user = userService.findByUsername(name);
		if (user != null && (isFromLogin || this.isRememberMeAuthenticated())) {
			for (Authority authority : user.getAuthorities()) {
				if (authority.getAuthority().equals("ROLE_ADMIN")) {
					request.getSession().setAttribute("role", "admin");
					return "redirect:/admin/users";
				} else if (authority.getAuthority().equals("ROLE_COACH")) {
					request.getSession().setAttribute("role", "coach");
					return "redirect:/coach/home";
				} else if (authority.getAuthority().equals("ROLE_DBA")) {
					request.getSession().setAttribute("role", "dba");
					return "redirect:/dba/viewquestions";
				}
			}
		}
		return "home";
	}
	
	/**
	 * If the login in from remember me cookie, refer
	 * org.springframework.security.authentication.AuthenticationTrustResolverImpl
	 */
	private boolean isRememberMeAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}				
		return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
	}

	/**
	 * This is not really recommend as the email content can be seen It should
	 * be revisit and config on javamailproperties using more secure way eg,
	 * using port 465
	 * 
	 * @param toEmail
	 * @param subject
	 * @param body
	 * @param emailSender
	 * @return
	 */
	public boolean sendEmail(String toEmail, String subject, String body, MailSender emailSender) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(toEmail);
			message.setReplyTo("false");
			message.setFrom("mumtestlink@gmail.com");
			message.setSubject(subject);
			message.setText(body);
			emailSender.send(message);
		} catch (Exception ex) {
			logger.error("Can't send email " + ex.getMessage());
			return false;
		}
		return true;
	}
}
