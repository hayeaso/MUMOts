package com.pm.onlinetest.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResetPassword {
	@RequestMapping(value = "/postResetPassword", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("INSIDE RESET PASSWORD"); 
		return "resetpassword";
	}

//@RequestMapping(value = "/generateTest", method = RequestMethod.GET)
//public @ResponseBody Map<String,String> generateTest(Locale locale, Model model, HttpServletResponse response) {
//	
//
//	String accessCode = assignmentService.generateAccesscode();
//	String accessLink="www.test.com";
//	model.addAttribute("accessCode",accessCode);
//	model.addAttribute("accessLink","www.test.com");	
//	
//	Map<String,String> map = new HashMap<String, String>();
//	
//	map.put("accessCode",accessCode);
//	map.put("accessLink",accessLink);
//	
//	return map;
//}
}