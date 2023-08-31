package com.km.onliefoodapp.exception;

public class FoodOrderCustomerNotFoundException extends RuntimeException{

	String message="customer details are not found";
	public String getMessage()
	{
		return message;
	}
}
