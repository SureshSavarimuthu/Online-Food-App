package com.km.onliefoodapp.exception;

public class NoSuchAFoodOrder extends RuntimeException{

	String message="The Given Food Order id is not present.";
	public String getMessage()
	{
		return message;
	}
			
}
