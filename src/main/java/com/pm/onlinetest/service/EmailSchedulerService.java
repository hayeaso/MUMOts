package com.pm.onlinetest.service;

import java.time.LocalDateTime;
import java.util.List;

import com.pm.onlinetest.domain.Assignment;
import com.pm.onlinetest.domain.EmailScheduler;
import com.pm.onlinetest.domain.Student;
/**
 * @author Diana Yamaletdinova
 *
 * Oct 9, 2017
 */
public interface EmailSchedulerService {
	public void generateEmailsToBeSend();
	public void saveEmailScheduler(EmailScheduler emailScheduler);
	public List<EmailScheduler> findDateToSend(LocalDateTime newDateNow);
	public List<EmailScheduler> findAllNotStartedWithin24h(LocalDateTime newDateNow);
    public EmailScheduler findByAssignmentId(Assignment assignment);
    public String sendEmail(String userId, String accessLink, String accessCode, String email);
    void updateOnEmailSend(Assignment assignment);
    public List<Assignment> findAllAssignmentsSendAndNotStarted (/*Student studentId*/);

}
