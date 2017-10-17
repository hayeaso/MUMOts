package com.pm.onlinetest.exception;

public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String errorMsg) {
		super(errorMsg);
	}
}
