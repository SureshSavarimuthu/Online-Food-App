package com.km.onliefoodapp.exception;

public class CustomerIdNotPresentException extends RuntimeException{
	String message="Incorrect Customer Id or Phone Number";
	
	public String getMessage()
	{
		return message;
	}
}
