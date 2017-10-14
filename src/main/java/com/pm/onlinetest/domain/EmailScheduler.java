package com.pm.onlinetest.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;
/**
 * @author Diana Yamaletdinova
 *
 * Oct 9, 2017
 */
@Entity
public class EmailScheduler {

 	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
 	
 	@Type(type="com.pm.onlinetest.util.LocalDateTimeUserType")
 	private LocalDateTime sendEmailDateTime;
 	
  	@OneToOne(cascade = {CascadeType.ALL})
  	private Assignment assignmentId;
  	
  	private String accessLink;
  	
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

	public LocalDateTime getSendEmailDateTime() {
		return sendEmailDateTime;
	}

	public void setSendEmailDateTime(LocalDateTime dateTimeNew) {
		this.sendEmailDateTime = dateTimeNew;
	}

	public void setAssignmentId(Assignment assignmentId) {
		this.assignmentId = assignmentId;
	}
	
	public String getAccessLink() {
		return accessLink;
	}

	public void setAccessLink(String accessLink) {
		this.accessLink = accessLink;
	}

	public boolean isSend() {
		return isSend;
	}

	public void setSend(boolean isSend) {
		this.isSend = isSend;
	}

	
	
}
