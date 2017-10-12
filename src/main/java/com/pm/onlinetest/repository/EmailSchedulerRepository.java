package com.pm.onlinetest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pm.onlinetest.domain.EmailScheduler;

@Repository
public interface EmailSchedulerRepository extends CrudRepository<EmailScheduler, Integer>{

	@Query ("SELECT d from EmailScheduler d WHERE d.sendEmailDateTime =:dateTime")
	List<EmailScheduler> findDate(@Param("dateTime") String dateTime);
}
