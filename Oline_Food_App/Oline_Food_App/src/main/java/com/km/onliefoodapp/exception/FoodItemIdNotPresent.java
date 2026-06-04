package com.km.onliefoodapp.exception;

public class FoodItemIdNotPresent extends RuntimeException{

	String message="The given Food Item Id is not present in the data base";
	public String getMessage()
	{
		return message;
	}
}
