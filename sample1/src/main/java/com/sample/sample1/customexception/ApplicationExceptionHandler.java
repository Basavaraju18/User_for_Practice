package com.sample.sample1.customexception;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sample.sample1.dto.ErrorResponse;

@RestControllerAdvice
public class ApplicationExceptionHandler  {

	
	
//	 public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
//	        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
//	    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
    	ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
    	
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        
    }
}
