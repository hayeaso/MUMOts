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
	private AssignmentService assgnmtService;

	@Autowired
	private MailSender mailSender;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:00");
	private LocalDateTime dateTime = LocalDateTime.now();
	private String curDate = dateTime.format(formatter);
	private LocalDateTime newDateNow = LocalDateTime.parse(curDate, formatter);

	/*
	 * Tasks scheduler method 1.Seconds; 2.Minutes; 3.Hours; 4.Day-of-Month;
	 * 5.Month; 6.Day-of-Week; 7.Year (optional field) set the timezone if
	 * needed zone="America/Chicago"
	 * 
	 * @Scheduled(cron = "* /10 * * * * *", zone="America/Chicago")
	 * https://www.freeformatter.com/cron-expression-generator-quartz.html 0 1 *
	 * ? * * At second :00 of minute :01 of every hour 10 0 * ? * * At second
	 * :10 of minute :00 of every hour 0/10 0 * ? * * => Every 10 seconds
	 * starting at second 00, at minute :00, of every hour
	 */
	@Scheduled(fixedDelay = 180000) // 3min
	// @Scheduled(fixedDelay = 50000)
	// @Scheduled(cron = "10 0 * ? * *", zone="America/Chicago") // every 1 hour
	@Override
	public void generateEmailsToBeSend() {

		// resetting assignments that passed 24h period and were not started
		notStartedIn24Hours();

		// start sending the email cron job
		List<EmailScheduler> dateList = new ArrayList<>();
		dateList = findDateToSend(newDateNow);

		if (dateList.isEmpty()) {
			System.out.println("****************No dates found************");
			return;
		} else {
			for (EmailScheduler date : dateList) {
				String accessCode = date.getAssignmentId().getAccesscode();
				if (accessCode != null) {
					String email = date.getAssignmentId().getStudentId().getEmail();
					String studentId = date.getAssignmentId().getStudentId().getStudentId();
					String accessLink = date.getAccessLink();
					sendEmail(studentId, accessLink, accessCode, email);
					date.setSend(true);// as email sent, set isSend to true, so
										// it wont be picked up by the DB next
										// time the job runs
				}
			}
		}
	}

	/*
	 * Implements the functionality to allow the coach to regenerate the
	 * assignment if the test was not started
	 */
	private void notStartedIn24Hours() {
		// start reset assignments passed 24 h cron job
		List<EmailScheduler> past24hMap = new ArrayList<EmailScheduler>();
		past24hMap = findAllNotStartedWithin24h(newDateNow);

		if (!past24hMap.isEmpty()) {

			for (EmailScheduler assignment : past24hMap) {
				long hours = calculate24hours(assignment.getSendEmailDateTime(), newDateNow);
				Assignment assgnmt = assignment.getAssignmentId();
				if (hours >= 24) {
					System.out.println(
							" -------------------------wasn't started within 24 h: Allow to reassign/regenerate a new test for assignment id --------------------------"
									+ assignment.getId());
					assgnmt.setCount(99);
					assgnmtService.updateAccessCount(assgnmt);
					//set24pastAssignmentEmailSchedulerToNull(assignment.getAssignmentId().getId());
					
				}
			}
		}
	}

	/*
	 * Sends email to the student
	 */
	@Override
	public String sendEmail(String userId, String accessLink, String accessCode, String email) {
		try {
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
			return "success";
		} catch (Exception e) {
			return "failure";
		}
	}

	/*
	 * Calculates how many hours passed from the date the test was sent
	 */
	public long calculate24hours(LocalDateTime emailschedulerDate, LocalDateTime currentDate) {
		// using Java 8's time utils to calculate hours between two dates
		long hours = ChronoUnit.HOURS.between(emailschedulerDate, currentDate);
		return hours;
	}

	@Override
	public List<EmailScheduler> findAllNotStartedWithin24h(LocalDateTime newDateNow) {
		return emailSchedulerRepository.findAllNotStartedWithin24h(newDateNow);
	}

	// @Transactional(isolation=Isolation.READ_COMMITTED)
//	@Override
//	public void set24pastAssignmentEmailSchedulerToNull(Integer id) {
//		emailSchedulerRepository.set24pastAssignmentToNull(id);
//	}

	/*
	 * Find the EmailScheduler obj by assignment Id
	 */
	@Override
	public EmailScheduler findByAssignmentId(Assignment assignmentId) {
		return emailSchedulerRepository.findByAssignmentId(assignmentId);
	}

	@Override
	public void updateOnEmailSend(Assignment assignmentId) {
		emailSchedulerRepository.updateOnEmailSend(assignmentId);
	}

	@Override
	public List<EmailScheduler> findDateToSend(LocalDateTime newDateNow) {
		return emailSchedulerRepository.findDateToSend(newDateNow);
	}

	@Override
	public void saveEmailScheduler(EmailScheduler emailScheduler) {
		emailSchedulerRepository.save(emailScheduler);
	}

}
