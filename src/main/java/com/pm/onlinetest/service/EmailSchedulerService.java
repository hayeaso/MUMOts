package com.pm.onlinetest.service;

import java.time.LocalDateTime;
import java.util.List;

import com.pm.onlinetest.domain.Assignment;
import com.pm.onlinetest.domain.EmailScheduler;
/**
 * @author Diana Yamaletdinova
 *
 * Oct 9, 2017
 */
public interface EmailSchedulerService {
	public void generateEmailsToBeSend();
	public void saveEmailScheduler(EmailScheduler emailScheduler);
	public List<EmailScheduler> findDate(LocalDateTime newDateNow);
	public EmailScheduler findByAssignmentId(Assignment assignment);
}
