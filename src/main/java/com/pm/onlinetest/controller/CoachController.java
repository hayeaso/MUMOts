package com.pm.onlinetest.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pm.onlinetest.domain.Assignment;
import com.pm.onlinetest.domain.EmailScheduler;
import com.pm.onlinetest.domain.Student;
import com.pm.onlinetest.domain.User;
import com.pm.onlinetest.repository.EmailSchedulerRepository;
import com.pm.onlinetest.service.AssignmentService;
import com.pm.onlinetest.service.CoachService;
import com.pm.onlinetest.service.EmailSchedulerService;
import com.pm.onlinetest.service.StudentService;
import com.pm.onlinetest.service.UserService;

@Controller
public class CoachController {

	@Autowired
	CoachService coachService;

	@Autowired
	UserService userService;

	@Autowired
	AssignmentService assignmentService;

	@Autowired
	StudentService studentService;
	
	@Autowired
	EmailSchedulerService emailScheduleService; //added by diana yamaletdinova

	/*@Autowired
	private MailSender mailSender;*/

	@RequestMapping(value = "/coach/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		List<Student> students = coachService.findStudentByAcitveJobStatus();
		model.addAttribute("students", students);
		return "coach";

	}

	@RequestMapping(value = "/coach/home1", method = RequestMethod.POST)
	public String home1(Locale locale, Model model) {
		List<Student> students = coachService.findStudentByAcitveJobStatus();
		model.addAttribute("students", students);
		return "coach";	
	}

	@RequestMapping(value = "/coach/studentAssignmentDetail/{userId}", method = RequestMethod.GET)
	public String studentAssignmentDetail(@PathVariable("userId") String userId, Locale locale, Model model) {

		System.out.println("Student id in detail1: " + userId);

		Integer studentId = Integer.parseInt(userId);
		Student student = coachService.findStudentById(studentId);
		Assignment assignment = assignmentService.findByStudentIdByFinish(student);
		EmailScheduler emailScheduler = emailScheduleService.findByAssignmentId(assignment);//added by Diana Yamaletdinova
 
		if(assignment !=null)
		System.out.println("assignment of finish false is: "+assignment.isFinished());
		//added by Diana Yamaletdinova//remove later
		if (emailScheduler != null){
			System.out.println("emailScheduler already exists for this assignment");			
		}			
		model.addAttribute("student",student);		
		if(assignment !=null && assignment.getAccesscode()!=null){
			System.out.println("emailScheduler date " + emailScheduler.getSendEmailDateTime());	
			System.out.println("assignment details accessCode: "+ assignment.getAccesscode());			
			model.addAttribute("assignment",assignment);	
			model.addAttribute("emailScheduler", emailScheduler);//added by Diana Yamaletdinova
			System.out.println("assignment and emailScheduler are not null");
		}
		else{
			assignment=null;
			emailScheduler = null;
			model.addAttribute("assignment",assignment);
			model.addAttribute("emailScheduler", emailScheduler);//added by Diana Yamaletdinova
		}		
		return "studentAssignmentDetail";	
	}	
	@RequestMapping(value = "/coach/studentAssignmentHistory/{userId}", method = RequestMethod.GET)
	public String studentAssignmentHistory(@PathVariable("userId") String userId, Locale locale, Model model) {
		Student student = studentService.findByStudentId(Integer.parseInt(userId));
		List<Assignment> assignments = assignmentService.findByStudent(student);
		model.addAttribute("assignments",assignments);
		model.addAttribute("student",student);
		return "studentAssignmentHistory";	
	}

	@RequestMapping(value = "/coach/saveAssignment", method = RequestMethod.POST)
	public @ResponseBody String saveAssignment(RedirectAttributes redirectAttr,@RequestParam("userId") String userId,
			@RequestParam("accessLink") String accessLink,@RequestParam("accessCode") String accessCode,
			@RequestParam("dateTime") String dateTime) {		
		
		try {

			Assignment assignment;
			EmailScheduler emailScheduler = null;
			String coachName = SecurityContextHolder.getContext().getAuthentication().getName();
			User coachModel = userService.findByUsername(coachName);

			Student student = studentService.findByStudentId(Integer.parseInt(userId));

			assignment = assignmentService.findByAccesscode(accessCode);
			if (assignment != null) {
				System.out.println("Assignment Already exist");
				return "exist"; // added by Diana Yamaletdinova
			} else {
				assignment = new Assignment();
				emailScheduler = new EmailScheduler();
				System.out.println("Assignment not exist create new one");
			}
			/*
			 * Added by Diana Yamaletdinova Format string from user input to
			 * LocalDateTime
			 */
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:00");
			LocalDateTime dateTimeUserInput = LocalDateTime.parse(dateTime, formatter);

			/*
			 * saving a new assignment and data into the emailscheduler table
			 * that associated with this assignment
			 */
			emailScheduler.setAssignmentId(assignment);
			emailScheduler.setSend(false);
			emailScheduler.setSendEmailDateTime(dateTimeUserInput);
			emailScheduler.setAccessLink(accessLink);
			/* End added by Diana Yamaletdinova */

			assignment.setAccesscode(accessCode);
			assignment.setCoachId(coachModel);
			assignment.setStudentId(student);
			assignment.setCount(0);
			assignment.setFinished(false);
			emailScheduleService.saveEmailScheduler(emailScheduler);
			assignmentService.saveAssignment(assignment);

			/* Added by Diana Yamaletdinova */
			/* send the email if user selected the current time */
			LocalDateTime now = LocalDateTime.now();
			String curDate = now.format(formatter);
			LocalDateTime curDateTime = LocalDateTime.parse(curDate, formatter);

			System.out.println("-------------------------------Date NOW-------" + curDateTime);

			if (curDateTime.equals(dateTimeUserInput)) {
				emailScheduleService.sendEmail(userId, accessLink, accessCode, student.getEmail());
				emailScheduleService.updateOnEmailSend(assignment);
				emailScheduler.setSend(true);
			}
			/* End added by Diana Yamaletdinova */
			return "success";
		} catch (Exception e) {
			System.out.println("failed" + e);
			return "failure";
			// TODO: handle exception
		}
	}

	//this method is commented out because the email scheduler is implemented in a service class
	//now when the user selects a day, email will be sent on this specific date. 

	/*@RequestMapping(value = "/coach/sendEmail", method = RequestMethod.GET)
	public @ResponseBody String sendEmail(@RequestParam("userId") String userId,
			@RequestParam("accessLink") String accessLink, @RequestParam("accessCode") String accessCode,
			@RequestParam("email") String email, @RequestParam("dateTime") String dateTime, Locale locale,
			Model model) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
	    message.setReplyTo("false");
	    message.setFrom("mumtestlink@gmail.com");
	    message.setSubject("Test Link");
	    message.setText("The test you can take at this particular link. To access the test you need to enter the access code provided below. "
	    		+ " Please find the link and the access code below: \n"+ "Access Link: "+"https://ots.cs.mum.edu/onlinetest/test" +"\n"+"Access Code: "+ accessCode +"\nAll the best!");  	
	    mailSender.send(message);	    
	    String result ="success";
	    return result;
	}*/

	@RequestMapping(value = "/coach/changeStudentJobSearchStatus", method = RequestMethod.POST)
	public @ResponseBody String changeStudentJobStatus(@RequestParam("userId") String studentId,
			@RequestParam("jobSearchStatus") String jobSearchStatus) {
		System.out.println("Inside job chnange status in coach controller studentId" + studentId);
		System.out.println("job search status is: " + jobSearchStatus);
		boolean jobStatus = false;
		if (jobSearchStatus.equals("active")) {
			jobStatus = true;
		}
		System.out.println("Inside job chnange status in coach controller studentId" + studentId);
		System.out.println("job search status is: " + jobSearchStatus);
		Student student = studentService.findByStudentId(Integer.parseInt(studentId));
		student.setJobSearchStatus(jobStatus);
		studentService.save(student);
		return "success";
	}
}
