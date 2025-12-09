package com.tcs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(InvalidLoanAmountException.class)
	public ResponseEntity<ErrorResponse> handleInvalidLoanAmount(InvalidLoanAmountException ex) {
		ErrorResponse error= new ErrorResponse("InvalidLoanAmountException",ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	@ExceptionHandler(DuplicateLoanApplicationException.class)
	public ResponseEntity<ErrorResponse> handleDuplicateApplication(DuplicateLoanApplicationException ex) {
		ErrorResponse error= new ErrorResponse("DuplicateLoanApplicationException",ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	@ExceptionHandler(LoanNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(LoanNotFoundException ex) {
		ErrorResponse error= new ErrorResponse("LoanNotFoundException",ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
		ErrorResponse error= new ErrorResponse("IllegalArgumentException",ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
		ErrorResponse error= new ErrorResponse(ex.getClass().getSimpleName(),"An unexpected error come.");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
	
}
