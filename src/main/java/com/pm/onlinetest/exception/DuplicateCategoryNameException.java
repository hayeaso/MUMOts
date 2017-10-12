package com.pm.onlinetest.exception;

public class DuplicateCategoryNameException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateCategoryNameException(String errorMsg) {
		super(errorMsg);
	}
}
