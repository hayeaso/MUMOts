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
	//@Query("SELECT e FROM EmailScheduler e, Assignment a WHERE e.sendEmailDateTime <= :dateTime and e.isSend = true and a.started=false and a.finished=false and a.regenerateTest=false and e.assignmentId=a.id")
	@Query("SELECT e FROM EmailScheduler e, Assignment a WHERE e.sendEmailDateTime <= :dateTime and e.isSend = true and a.started=false and a.count!=99 and e.assignmentId=a.id")
	List<EmailScheduler> findAllNotStartedWithin24h(@Param("dateTime") LocalDateTime dateTime);

	/*Update the assignment table, by setting finished=true if the test wasnt started in 24 h
	* that way this would not be picked up by the controller and coach can generate a new test for the same student
	* however, it is better to add one more field in this table 'reset' (boolean) and use this one to do a reset
	* in this cae we will need to change the findByStudentIdByFinish() method the the AssignmentRepo to also check if reset is false 
	*/
//	@Modifying(clearAutomatically = true)
//	@Transactional
//	@Query("UPDATE Assignment a SET a.regenerateTest=true WHERE a.id=:id")
//	void set24pastAssignmentToNull (@Param("id") Integer id);
//	
	/*
	 * Update email scheduler for the same assignment as in the set24pastAssignmentToNull()
	 * not sure if i need to reset isSend
	 */
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE EmailScheduler e SET e.isSend=true WHERE e.assignmentId=:assignmentId")
	void updateOnEmailSend (@Param("assignmentId") Assignment assignmentId);

	
}
