package com.km.onliefoodapp.exception;

public class FoodMenuIdIsNotPresent extends RuntimeException{
	String message="The Given Food Menu Id is not present in the data base";
	public String getMessage()
	{
		return message;
	}

}
