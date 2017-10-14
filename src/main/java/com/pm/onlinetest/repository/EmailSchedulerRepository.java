package com.pm.onlinetest.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pm.onlinetest.domain.Assignment;
import com.pm.onlinetest.domain.EmailScheduler;
/**
 * @author Diana Yamaletdinova
 *
 * Oct 9, 2017
 */
@Repository
public interface EmailSchedulerRepository extends CrudRepository<EmailScheduler, Integer>{

	@Query ("SELECT d from EmailScheduler d WHERE d.sendEmailDateTime <= :dateTime and d.isSend = false")
	List<EmailScheduler> findDate(@Param("dateTime") LocalDateTime newDateNow);

	@Query ("SELECT d from EmailScheduler d WHERE d.assignmentId =:assignmentId")
	EmailScheduler findByAssignmentId(@Param("assignmentId") Assignment assignmentId);
	
	//select all assignments that were not started within 24 hours but email was sent

}
