package com.pm.onlinetest.controller;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;	
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pm.onlinetest.domain.User;
import com.pm.onlinetest.service.AssignmentService;
import com.pm.onlinetest.service.UserService;
import com.pm.onlinetest.service.impl.UserServiceImpl;

import helpers.PasswordDto;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	private MailSender mailSender;

	@Autowired
	AssignmentService assignmentService;	
	@Value("${application.context.url}")
	private String appContextUrl;

	
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
	public String forgetPass(@ModelAttribute("loginUser") User user, Model model,
			RedirectAttributes redirectAttributes) {

		String email = user.getEmail();

		User myUser = userService.findByEmail(user.getEmail());
		if (myUser != null && myUser.getUsername() != null) {

			String token = UUID.randomUUID().toString();
			userService.createPasswordResetTokenForUser(myUser, token);
			String accessCode = token + "_" + myUser.getUserId();

			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email);

			message.setReplyTo("false");
			message.setFrom("mumtestlink@gmail.com");
			message.setSubject("Password Reset - MUM Self Assessment");
			message.setText(
					"You have requested to reset your password for your account.\r\nTo reset your password, visit the following address.\r\n"
							+ appContextUrl + "/resetPassword/" + accessCode
							+ "\r\nThe above link will expire in 60 minutes.");

			mailSender.send(message);
			redirectAttributes.addFlashAttribute("foundEmail", email);
			return "redirect:/login";
		} else {
			redirectAttributes.addFlashAttribute("notFoundEmail", email);
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value = "/resetPassword/{accessCode}", method = RequestMethod.GET)
	public String resetPassword(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response , @PathVariable("accessCode") String accessCode) {
		String[] token = accessCode.split("_"); // token and id		
		if (token.length > 1) {
			Integer userId = Integer.parseInt(token[1]);
			String tokenStatus = userService.validatePasswordResetToken(userId, token[0]);
			if (tokenStatus.equals(UserServiceImpl.TOKEN_VALID)) {
				model.addAttribute("token", token[0]);
				model.addAttribute("id", token[1]);
			} else {
				model.addAttribute("exipredToken", token);				
			}
		}
		return "resetPassword";
	}
	
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String updatePassword(@ModelAttribute("passwordDto") PasswordDto passwordDto, Model model, HttpServletRequest request, RedirectAttributes redirectAttr) {	
		
		String tokenStatus = userService.validatePasswordResetToken(passwordDto.getId(), passwordDto.getToken());
		User user = userService.getUserFromToken(passwordDto.getToken());
		if (tokenStatus == UserServiceImpl.TOKEN_VALID && user != null) {

			user.setPassword(passwordDto.getNewPassword());
			userService.saveProfile(user);
			// set expiryDate to now
			userService.setTokenExipredTime(passwordDto.getToken(), LocalDateTime.now());
			redirectAttr.addFlashAttribute("changeSuccess", "ChangeSuccess");
			redirectAttr.addFlashAttribute("titleMessage", "Password Changed");
			redirectAttr.addFlashAttribute("bodyMessage", "New Password Updated SuccessFully.");

			return "redirect:/login";
		} else {			
			model.addAttribute("exipredToken", tokenStatus);
			return "resetPassword";
		}
	}
	

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {			
			new SecurityContextLogoutHandler().logout(request, response, auth);			
			cancelCookie(request, response, "remember-me"); //clear remember me cookie
			//cancelCookie(request, response, "JSESSIONID"); //clear JSESSIONID cookie (logout)
		}
		return "redirect:/login";
	}
	
	//Start Remember me blocks	
	void cancelCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {		
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		cookie.setPath(StringUtils.hasLength(request.getContextPath()) ? request.getContextPath() : "/");
		response.addCookie(cookie);
	}
	
	/*
	 * //** This probably need to implements when you want to put restriction on
	 * some request that need fullyAuthenticate save targetURL in session
	 *//*
	private void setRememberMeTargetUrlToSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.setAttribute("targetUrl", "/admin/editStudent");
		}
	}

	// End of Remember me blocks
*/
}
