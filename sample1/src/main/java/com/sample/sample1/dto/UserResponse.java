package com.sample.sample1.dto;

import java.util.List;

import com.sample.sample1.Entity.User;

public class UserResponse {
	
	private User user;
	private List<String> logMessages;
	
	public UserResponse(User user, List<String> logMessages) {
        this.user = user;
        this.logMessages = logMessages;
    }
	
	 public User getUser() {
	        return user;
	    }

	    public List<String> getLogMessages() {
	        return logMessages;
	    }
	}

