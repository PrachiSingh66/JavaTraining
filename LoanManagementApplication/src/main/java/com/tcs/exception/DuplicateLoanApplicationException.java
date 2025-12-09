package com.tcs.exception;

public class DuplicateLoanApplicationException extends RuntimeException{
	public DuplicateLoanApplicationException(String msg){
		super(msg);
	}
}
