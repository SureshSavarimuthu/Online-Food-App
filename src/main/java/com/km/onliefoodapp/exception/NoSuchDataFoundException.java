package com.km.onliefoodapp.exception;

public class NoSuchDataFoundException extends RuntimeException{
	
	private String message="Given Data Not Found in the database";
	public String getMessage()
	{
		return message;
	}


}
