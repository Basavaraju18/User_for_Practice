package com.sample.sample1.dto;

import com.sample.sample1.Entity.User;

public class UserResponse {

	private User user;
	private String message; // Only user-friendly messages, no logs

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserResponse(User user, String message) {
		this.user = user;
		this.message = message;
	}



}

