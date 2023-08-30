package com.km.onliefoodapp.exception;

public class InCorrectPasswordException extends RuntimeException {
	String message="Incorrect Password!.Please check your Password";
	public String getMessage()
	{
		return message;
	}
	

}
