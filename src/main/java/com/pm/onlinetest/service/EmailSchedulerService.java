package com.pm.onlinetest.service;

import java.time.LocalDateTime;
import java.util.List;

import com.pm.onlinetest.domain.EmailScheduler;

public interface EmailSchedulerService {
	
	public void generateEmailsToBeSend();
	//public List<EmailScheduler> findDate(String dateTime);
	public void saveEmailScheduler(EmailScheduler emailScheduler);
	public List<EmailScheduler> findDate(LocalDateTime newDateNow);
}
