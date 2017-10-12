package com.pm.onlinetest.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.onlinetest.domain.EmailScheduler;
import com.pm.onlinetest.repository.EmailSchedulerRepository;
import com.pm.onlinetest.service.EmailSchedulerService;

@Service
@Transactional
public class EmailSchedulerServiceImpl implements EmailSchedulerService{

	@Autowired
	private EmailSchedulerRepository emailSchedulerRepository;

    @Autowired
    private MailSender mailSender;
    
	/* Tasks scheduler method
	 * 1.Seconds; 2.Minutes; 3.Hours; 4.Day-of-Month; 5.Month; 6.Day-of-Week; 7.Year (optional field)
	 * @Scheduled(cron = "* * * * * *")
	 */
	//@Scheduled(fixedDelay = 5000)
	@Override
	public void generateEmailsToBeSend() {		

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:00");
		LocalDateTime dateTime = LocalDateTime.now();
		String curDate = dateTime.format(formatter);
		LocalDateTime newDateNow = LocalDateTime.parse(curDate, formatter);
		 
		System.out.println("***************Current date******* " + newDateNow);
		
		List<EmailScheduler> dateList = new ArrayList<>();
		dateList = findDate(newDateNow);
		
		if ( dateList.isEmpty() ){
			System.out.println("****************No dates found************");
			return;
		}else{
			for (EmailScheduler date : dateList ){
				if (date.getSendEmailDateTime().equals(newDateNow)){
					//System.out.println("***************  DB date in if loop ********** " + date.getSendEmailDateTime());
					//System.out.println("***************Current date in if loop ******* " + newDateNow);
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
	
	/*public @ResponseBody String sendEmail(String userId, String accessLink, String accessCode, String email, String dateTime) {
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
	}*/
	
}
