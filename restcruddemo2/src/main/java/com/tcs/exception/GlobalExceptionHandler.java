package com.tcs.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(value = IdNotFoundException.class)
	public ErrorInfo handleExceptionForId(IdNotFoundException exception)
	{
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorMessage(exception.getMessage());
		errorInfo.setHttpStatus(HttpStatus.NOT_FOUND);
		errorInfo.setLocalDateTime(LocalDateTime.now());
		
		return errorInfo;
	}
	
	@ExceptionHandler(value = InvalidAgeException.class)
	public String handleExceptionForAge(InvalidAgeException exception)
	{
		return exception.getMessage();
	}
}