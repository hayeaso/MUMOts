package com.pm.onlinetest.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class EmailScheduler {

 	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
 	
  	
 	private String sendEmailDateTime;
 	
  	@OneToOne(cascade = {CascadeType.ALL})
  	private Assignment assignmentId;
  	
  	private boolean isSend;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Assignment getAssignmentId() {
		return assignmentId;
	}

	public String getSendEmailDateTime() {
		return sendEmailDateTime;
	}

	public void setSendEmailDateTime(String dateTime) {
		this.sendEmailDateTime = dateTime;
	}

	public void setAssignmentId(Assignment assignmentId) {
		this.assignmentId = assignmentId;
	}

	public boolean isSend() {
		return isSend;
	}

	public void setSend(boolean isSend) {
		this.isSend = isSend;
	} 
	
	
}
