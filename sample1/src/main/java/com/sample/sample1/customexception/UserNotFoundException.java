package com.sample.sample1.customexception;

public class UserNotFoundException extends RuntimeException {

	private String message;
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
//	public String getMessage() {
//		return message;
//	}
}


