package com.pm.onlinetest.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pm.onlinetest.domain.Assignment;
import com.pm.onlinetest.domain.EmailScheduler;
/**
 * @author Diana Yamaletdinova
 *
 * Oct 9, 2017
 */
@Repository
public interface EmailSchedulerRepository extends CrudRepository<EmailScheduler, Integer>{

	@Query ("SELECT d from EmailScheduler d WHERE d.sendEmailDateTime <= :dateTime AND accessLink != NULL and d.isSend = false")
	List<EmailScheduler> findDateToSend(@Param("dateTime") LocalDateTime newDateNow);
	
	@Query ("SELECT d from EmailScheduler d WHERE d.assignmentId =:assignmentId")
	EmailScheduler findByAssignmentId(@Param("assignmentId") Assignment assignmentId);
	
	/*select all assignments that were not started within 24 hours but email was sent
	* technicaly I can pas the date that is exact 24 h ago considering that i am checking/running tasks every hour, 
	* but in case something goes wrong, I will leave this way, where I get all the date that is smaller that current date 
	*and will do the check if its >= than 24 hours in the Service class
	*/
	@Query("SELECT e FROM EmailScheduler e, Assignment a WHERE e.sendEmailDateTime <= :dateTime and e.isSend = true and a.started=false and a.count!=99 and e.assignmentId=a.id")
	List<EmailScheduler> findAllNotStartedWithin24h(@Param("dateTime") LocalDateTime dateTime);

	/*
	 * Update email scheduler for the same assignment as in the set24pastAssignmentToNull()
	 * not sure if i need to reset isSend
	 */
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE EmailScheduler e SET e.isSend=true WHERE e.assignmentId=:assignmentId")
	void updateOnEmailSend (@Param("assignmentId") Assignment assignmentId);

	
}
