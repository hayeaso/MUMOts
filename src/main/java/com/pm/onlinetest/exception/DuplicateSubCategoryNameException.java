package com.pm.onlinetest.exception;

public class DuplicateSubCategoryNameException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateSubCategoryNameException(String errorMsg) {
		super(errorMsg);
	}
}
