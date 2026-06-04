package com.km.onliefoodapp.exceptionhandling;

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.km.onliefoodapp.exception.CustomerIdNotPresentException;
import com.km.onliefoodapp.exception.FoodItemIdNotFoundException;
import com.km.onliefoodapp.exception.FoodItemIdNotPresent;
import com.km.onliefoodapp.exception.FoodItemNotAvaliableException;
import com.km.onliefoodapp.exception.FoodMenuIdIsNotPresent;
import com.km.onliefoodapp.exception.FoodOrderCustomerNotFoundException;
import com.km.onliefoodapp.exception.FoodProductIdIsNotPresent;
import com.km.onliefoodapp.exception.InCorrectPasswordException;
import com.km.onliefoodapp.exception.InvalidEmailIDException;
import com.km.onliefoodapp.exception.NoSuchAFoodOrder;
import com.km.onliefoodapp.exception.NoSuchDataFoundException;
import com.km.onliefoodapp.exception.UserDataNotFoundInTheDatabase;
import com.km.onliefoodapp.exception.UserRoleDoesNotMatch;
import com.km.onliefoodapp.util.ResponseStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(NoSuchDataFoundException.class)
	public ResponseEntity<ResponseStructure<String>> dataNotFound(NoSuchDataFoundException noSuchDataFoundException)
	{
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("No such data found in the data base");
		responseStructure.setData(noSuchDataFoundException.getMessage());
		
		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);

		return responseEntity;
	}
	
	@ExceptionHandler(UserDataNotFoundInTheDatabase.class)
	public ResponseEntity<ResponseStructure<String>> userDataNotFound(UserDataNotFoundInTheDatabase userDataNotFoundInTheDatabase)
	{
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("User Data Not Found In The Database ");
		responseStructure.setData(userDataNotFoundInTheDatabase.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}

	@ExceptionHandler(UserRoleDoesNotMatch.class)
	public ResponseEntity<ResponseStructure<String>> roleDataNotFound(UserRoleDoesNotMatch userRoleDoesNotMatch)
	{
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("User Role not match with database");
		responseStructure.setData(userRoleDoesNotMatch.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
	
	@ExceptionHandler(InvalidEmailIDException.class)
	public ResponseEntity<ResponseStructure<String>> invalidEmailId(InvalidEmailIDException invalidEmailIDException)
	{
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Invalid EmailId");
		responseStructure.setData(invalidEmailIDException.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
		return responseEntity;
		
	}

	@ExceptionHandler(InCorrectPasswordException.class)
	public ResponseEntity<ResponseStructure<String>> incorrectPassword(InCorrectPasswordException inCorrectPasswordException)
	{
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Incorrect Password");
		responseStructure.setData(inCorrectPasswordException.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
		return responseEntity;
	}
	
	/*
	 * =====================================================================================================================
	 * 										FOOD ITEMS EXCEPTION
	 * ===================================================================================================================== 
	 * 
	*/	
	
	
	
	@ExceptionHandler(FoodItemIdNotPresent.class)
	public ResponseEntity<ResponseStructure<String>> foodItemIdNoFound(FoodItemIdNotPresent foodItemIdNotPresent)
	{
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Food Item id is Incorrect.");
		responseStructure.setData(foodItemIdNotPresent.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
		return responseEntity;
			
	}

	
	/*
	 * =====================================================================================================================
	 * 										FOOD MENU EXCEPTION
	 * ===================================================================================================================== 
	 * 
	*/	
	
	@ExceptionHandler(FoodMenuIdIsNotPresent.class)
	public ResponseEntity<ResponseStructure<String>> foodMenuIdNoFound(FoodMenuIdIsNotPresent foodMenuIdIsNotPresent)
	{
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Food Menu id is Incorrect either Invalid.");
		responseStructure.setData(foodMenuIdIsNotPresent.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
		return responseEntity;
			
	}
	
	/*
	 * =====================================================================================================================
	 * 										FOOD PRODUCT EXCEPTION
	 * ===================================================================================================================== 
	 * 
	*/	
	@ExceptionHandler(FoodProductIdIsNotPresent.class)
	public ResponseEntity<ResponseStructure<String>> foodProductIdNoFound(FoodProductIdIsNotPresent foodProductIdIsNotPresent)
	{
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Food product id is Incorrect.");
		responseStructure.setData(foodProductIdIsNotPresent.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
		return responseEntity;
			
	}

	
	/*
	 * =====================================================================================================================
	 * 										Customer EXCEPTION
	 * ===================================================================================================================== 
	 * 
	*/	
	@ExceptionHandler(CustomerIdNotPresentException.class)
	public ResponseEntity<ResponseStructure<String>> customerNotFound(CustomerIdNotPresentException  customerIdNotPresentException)
	{
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Customer detail is Incorrect.");
		responseStructure.setData(customerIdNotPresentException.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
		return responseEntity;
			
	}	
	
	/*
	 * =====================================================================================================================
	 * 										FOOD ORDER EXCEPTION
	 * ===================================================================================================================== 
	 * 
	*/	
	
	@ExceptionHandler(FoodOrderCustomerNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> foodMenuIdIsNotPresent(FoodOrderCustomerNotFoundException foodOrderCustomerNotFoundException )
	{
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Invalid Customer details.");
		responseStructure.setData(foodOrderCustomerNotFoundException.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
		return responseEntity;
			
	}	
	
	@ExceptionHandler(NoSuchAFoodOrder.class)
	public ResponseEntity<ResponseStructure<String>> foodMenuIdIsNotPresent(NoSuchAFoodOrder noSuchAFoodOrder )
	{
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Food Order Not found");
		responseStructure.setData(noSuchAFoodOrder.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
		return responseEntity;
			
	}	
	
	
	/*
	 * =====================================================================================================================
	 * 										FOOD ITEM EXCEPTION
	 * ===================================================================================================================== 
	 * 
	*/	
	
	@ExceptionHandler(FoodItemIdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> foodItemIdIsNotPresent(FoodItemIdNotFoundException foodItemIdNotFoundException )
	{
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Invalid Item details.");
		responseStructure.setData(foodItemIdNotFoundException.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
		return responseEntity;
			
	}	
	

	@ExceptionHandler(FoodItemNotAvaliableException.class)
	public ResponseEntity<ResponseStructure<String>> foodItemIdIsNotPresent(FoodItemNotAvaliableException foodItemNotAvaliableException )
	{
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage("Invalid Item details.");
		responseStructure.setData(foodItemNotAvaliableException.getMessage());

		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.NOT_FOUND);
		
		return responseEntity;
			
	}
}
