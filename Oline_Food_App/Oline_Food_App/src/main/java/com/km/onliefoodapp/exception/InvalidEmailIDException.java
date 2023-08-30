package com.km.onliefoodapp.exception;

public class InvalidEmailIDException extends RuntimeException{
	String message="Invalid Email Id. Please check your Email id.";
	public String getMessage()
	{
		return message;
	}
	

}
