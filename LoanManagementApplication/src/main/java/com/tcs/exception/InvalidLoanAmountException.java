package com.tcs.exception;

public class InvalidLoanAmountException extends RuntimeException{
	public InvalidLoanAmountException(String msg){
		super(msg);
	}
}
