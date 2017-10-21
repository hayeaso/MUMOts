package com.pm.onlinetest.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

@Entity
public class PasswordResetToken {
	private static final int EXPIRATION = 60;// in minutes

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String token;
	@OneToOne( fetch = FetchType.EAGER)	
	private User user;
	@Type(type="com.pm.onlinetest.util.LocalDateTimeUserType")
	private LocalDateTime expiryDate;

	public PasswordResetToken() {		
	}
	
	public PasswordResetToken(String token, User user) {
		this.user = user;
		this.token = token;
		this.populateExpireDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public void populateExpireDate() {
		this.expiryDate = LocalDateTime.now().plusMinutes(EXPIRATION); 
	}
}
