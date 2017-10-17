package com.pm.onlinetest.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> deleteUserHandler(Exception ex) {
		logger.debug("deleteUserHandler exception: " + ex.getMessage());
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.OK);		
	}
}
