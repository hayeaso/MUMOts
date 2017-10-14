package com.pm.onlinetest.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pm.onlinetest.domain.Assignment;
import com.pm.onlinetest.domain.EmailScheduler;
import com.pm.onlinetest.repository.EmailSchedulerRepository;
import com.pm.onlinetest.service.AssignmentService;
import com.pm.onlinetest.service.EmailSchedulerService;

/**
 * @author Diana Yamaletdinova
 *
 *         Method will send emails automatically based on the scheduling in
 *         Scheduled annotation Oct 12, 2017
 */

@Service
@Transactional
public class EmailSchedulerServiceImpl implements EmailSchedulerService {

	@Autowired
	private EmailSchedulerRepository emailSchedulerRepository;

	@Autowired
	private MailSender mailSender;

	// @Autowired
	// @Qualifier("assignmentServiceB")
	// AssignmentService assignmentServiceB = new AssignmentServiceImpl();

	// @Autowired
	// @Qualifier("emailScheduleServiceB")
	// EmailSchedulerService emailScheduleServiceB = new
	// EmailSchedulerServiceImpl();

	/*
	 * Tasks scheduler method 1.Seconds; 2.Minutes; 3.Hours; 4.Day-of-Month;
	 * 5.Month; 6.Day-of-Week; 7.Year (optional field) set the timezone if
	 * needed zone="America/Chicago"
	 * 
	 * @Scheduled(cron = "* /10 * * * * *", zone="America/Chicago")
	 * 
	 * +------------------ second (0 - 59) | +----------------- minute (0 - 59)
	 * | | +-------------- hour (0 - 23) | | | +----------- day of month (1 -
	 * 31) | | | | +-------- month (1 - 12) | | | | | +----- day of week (0 - 6)
	 * (Sunday=0 or 7) | | | | | | +-- year [optional] | | | | | | | * * * * * *
	 * https://www.freeformatter.com/cron-expression-generator-quartz.html 0 1 *
	 * ? * * At second :00 of minute :01 of every hour 10 0 * ? * * At second
	 * :10 of minute :00 of every hour 0/10 0 * ? * * => Every 10 seconds
	 * starting at second 00, at minute :00, of every hour
	 */
	@Scheduled(fixedDelay = 180000)
	// @Scheduled(fixedDelay = 50000)
	// @Scheduled(cron = "10 0 * ? * *", zone="America/Chicago") // every 1 hour
	@Override
	public void generateEmailsToBeSend() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:00");
		LocalDateTime dateTime = LocalDateTime.now();
		String curDate = dateTime.format(formatter);
		LocalDateTime newDateNow = LocalDateTime.parse(curDate, formatter);

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
		Date date2 = new Date();
		System.out.println("****************Time now************" + dateFormat.format(date2)); // 2016/11/16
																								// 12:08:43

		List<EmailScheduler> dateList = new ArrayList<>();
		dateList = findDate(newDateNow);

		if (dateList.isEmpty()) {
			System.out.println("****************No dates found************");
			return;
		} else {
			for (EmailScheduler date : dateList) {

				String accessCode = date.getAssignmentId().getAccesscode();
				if (accessCode != null) {
					String email = date.getAssignmentId().getStudentId().getEmail();
					// throws an exception b/c we set student id to null
					String studentId = date.getAssignmentId().getStudentId().getStudentId();
					String accessLink = date.getAccessLink();

					/*
					 * set assignment to null if 24 hours passed and test wasn't
					 * taken
					 */
					// get assignment by student obj and emailscheduler by this
					// assignment

					// Assignment assignment =
					// assignmentServiceB.findByStudentIdByFinish(date.getAssignmentId().getStudentId());
					// EmailScheduler emailScheduler =
					// emailScheduleServiceB.findByAssignmentId(assignment);

					// if(assignment !=null && emailScheduler != null){
					// now check if 24 hours passed
					long hoursCheck = calculate24hours(date.getSendEmailDateTime(), newDateNow);
					System.out.println("---------------------------------------------hours passed from "
							+ date.getSendEmailDateTime() + " and " + newDateNow + "===== " + hoursCheck);

					// }else{
					// System.out.println("hours passed from else " +
					// date.getSendEmailDateTime() + " and " + newDateNow);
					// }

					System.out.println("***************sending email: emailSchedulerID***************" + date.getId());
					System.out.println("***************sending email: accesscode***************" + accessCode);
					System.out.println("***************sending email: email***************" + email);
					System.out.println("***************sending email: studentId***************" + studentId);
					System.out.println("***************sending email: accessLink***************" + accessLink);
					System.out.println("***************sending email: date***************" + date);

					sendEmail(studentId, accessLink, accessCode, email);
					date.setSend(true);// as email sent, set isSend to true, so
										// it wont be picked up by the DB

					System.out.println("***************isSend after sending emaile***************" + date.isSend());
					System.out.println("-------------------------------------------------------" + date);
				}
			}
		}
	}

	@Override
	public List<EmailScheduler> findDate(LocalDateTime newDateNow) {
		return emailSchedulerRepository.findDate(newDateNow);
	}

	@Override
	public void saveEmailScheduler(EmailScheduler emailScheduler) {
		emailSchedulerRepository.save(emailScheduler);
	}

	/*
	 * Sends email to the student
	 */
	public String sendEmail(String userId, String accessLink, String accessCode, String email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setReplyTo("false");
		message.setFrom("mumtestlink@gmail.com");
		message.setSubject("Test Link");
		message.setText(
				"The test you can take at this particular link. To access the test you need to enter the access code provided below. "
						+ " Please find the link and the access code below: \n" + "Access Link: "
						+ "https://ots.cs.mum.edu/onlinetest/test" + "\n" + "Access Code: " + accessCode
						+ "\nAll the best!");

		mailSender.send(message);
		String result = "success";
		return result;
	}

	/* 
	 */
	@Override
	public EmailScheduler findByAssignmentId(Assignment assignmentId) {
		return emailSchedulerRepository.findByAssignmentId(assignmentId);
	}

	/*
	 * Method that calculates 24 hours from the date the test was sent this
	 * method will be used to set the assignment and emailscheduler to null if
	 * test wasnt taken within 24 hours
	 */
	public long calculate24hours(LocalDateTime emailschedulerDate, LocalDateTime currentDate) {
		// using Java 8's time utils to calculate hours between two dates
		long hours = ChronoUnit.HOURS.between(emailschedulerDate, currentDate);
		return hours;
	}

}
