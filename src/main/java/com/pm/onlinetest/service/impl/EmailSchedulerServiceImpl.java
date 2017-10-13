package com.pm.onlinetest.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pm.onlinetest.domain.EmailScheduler;
import com.pm.onlinetest.repository.EmailSchedulerRepository;
import com.pm.onlinetest.service.EmailSchedulerService;

/*
 * Method that 
 * 
 */

@Service
@Transactional
public class EmailSchedulerServiceImpl implements EmailSchedulerService{

	@Autowired
	private EmailSchedulerRepository emailSchedulerRepository;

    @Autowired
    private MailSender mailSender;
    
	/* Tasks scheduler method
	 * 1.Seconds; 2.Minutes; 3.Hours; 4.Day-of-Month; 5.Month; 6.Day-of-Week; 7.Year (optional field)
	 * set the timezone if needed zone="America/Chicago"
	 * @Scheduled(cron = "* /10 * * * * *", zone="America/Chicago")
	 * 
	* +------------------ second (0 - 59)
	|  +----------------- minute (0 - 59)
	|  |  +-------------- hour (0 - 23)
	|  |  |  +----------- day of month (1 - 31)
	|  |  |  |  +-------- month (1 - 12)
	|  |  |  |  |  +----- day of week (0 - 6) (Sunday=0 or 7)
	|  |  |  |  |  |  +-- year [optional]
	|  |  |  |  |  |  |
	*  *  *  *  *  *  * 
	 * https://www.freeformatter.com/cron-expression-generator-quartz.html
	 * 0 1 * ? * * At second :00 of minute :01 of every hour
	 * 10 0 * ? * * At second :10 of minute :00 of every hour
	 * 0/10 0 * ? * * => Every 10 seconds starting at second 00, at minute :00, of every hour
	 */
	@Scheduled(fixedDelay = 180000)
    //@Scheduled(cron = "10 0 * ? * *", zone="America/Chicago") // every 1 hour
	@Override
	public void generateEmailsToBeSend() {		

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:00");
		LocalDateTime dateTime = LocalDateTime.now();
		String curDate = dateTime.format(formatter);
		LocalDateTime newDateNow = LocalDateTime.parse(curDate, formatter);
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
		Date date2 = new Date();
		System.out.println("****************Time now************" + dateFormat.format(date2)); //2016/11/16 12:08:43
		
		//System.out.println("****************Time now************" + newDateNow.toString());
		List<EmailScheduler> dateList = new ArrayList<>();
		dateList = findDate(newDateNow);
		
		if ( dateList.isEmpty() ){
			System.out.println("****************No dates found************");
			return;
		}else{
			for (EmailScheduler date : dateList ){
				String accessCode = date.getAssignmentId().getAccesscode();
				String email = date.getAssignmentId().getStudentId().getEmail();
				String studentId = date.getAssignmentId().getStudentId().getStudentId();
				String accessLink = date.getAccessLink();
				System.out.println("***************sending email: emailSchedulerID***************" + date.getId());
				System.out.println("***************sending email: accesscode***************" + accessCode);
				System.out.println("***************sending email: email***************" + email);
				System.out.println("***************sending email: studentId***************" + studentId);
				System.out.println("***************sending email: accessLink***************" + accessLink);
				System.out.println("***************sending email: date***************" + date);
				
				sendEmail(studentId,accessLink, accessCode, email);
				date.setSend(true);
				
				System.out.println("***************isSend after sending emaile***************" + date.isSend());
				System.out.println("-------------------------------------------------------" + date);
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
	
	public String sendEmail(String userId, String accessLink, String accessCode, String email) {
		System.out.println("_____________________email sending form Email Service Impl_____________________________");
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
	}
	
}
