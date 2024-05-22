package com.km.onliefoodapp.exception;

public class UserRoleDoesNotMatch extends RuntimeException{
	
	String message="User Role Not Match. So Can't provide further access";
	
	public String getMessage()
	{
		return message;
	}

}
