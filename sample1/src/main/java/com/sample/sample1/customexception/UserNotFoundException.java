package com.sample.sample1.customexception;

import org.springframework.http.HttpStatus;



public class UserNotFoundException extends RuntimeException {

	private String message;
	private Long userId;
	
	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Long userId) {
		this.userId=userId;
	}
	
//	public String getMessage() {
//		return message;
//	}
}


