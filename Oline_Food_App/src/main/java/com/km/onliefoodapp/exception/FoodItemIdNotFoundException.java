package com.km.onliefoodapp.exception;

public class FoodItemIdNotFoundException extends RuntimeException{

	String message="Food Item not found";
	public String getMessage()
	{
		return message;
	}
}
