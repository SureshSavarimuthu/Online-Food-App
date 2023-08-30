package com.km.onliefoodapp.exception;

public class FoodProductIdIsNotPresent extends RuntimeException{
	String message="The Given Food Product Id is not present in the Data base";

	public String getMessage()
	{
		return message;
	}
}
