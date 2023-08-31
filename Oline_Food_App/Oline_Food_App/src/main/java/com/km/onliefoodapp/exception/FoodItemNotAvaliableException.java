package com.km.onliefoodapp.exception;

public class FoodItemNotAvaliableException extends RuntimeException{

	String message="food Item not avaliable in this time please select others";
	public String getMessage()
	{
		return message;
	}
}
