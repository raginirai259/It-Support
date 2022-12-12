package com.support.it.exception;

import com.support.it.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Exception handler for the REST endpoints
@RestControllerAdvice
public class ExceptionControllerAdvice {
 
	@ExceptionHandler(Exception.class)
	public String  exceptionHandler(Exception ex) {
		 
		return  ex.getMessage();
	}
	
	//Handler for handling exception of type NoSuchTicketException
	@ExceptionHandler(NoSuchTicketException.class)
	public ResponseEntity<ErrorMessage> exceptionHandler2(NoSuchTicketException ex) {
		 ErrorMessage error = new ErrorMessage();
	        error.setErrorCode(HttpStatus.BAD_GATEWAY.value());
	        error.setMessage(ex.getMessage());
	        return new ResponseEntity<>(error, HttpStatus.OK);
		 
	}
} 