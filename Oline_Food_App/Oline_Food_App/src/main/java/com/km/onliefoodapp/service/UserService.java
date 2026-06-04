
package com.km.onliefoodapp.service;

import java.util.List; 
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.km.onliefoodapp.dao.FoodOrderDao;
import com.km.onliefoodapp.dao.UserDao;
import com.km.onliefoodapp.entity.Role;
import com.km.onliefoodapp.entity.User;
import com.km.onliefoodapp.exception.InvalidEmailIDException;
import com.km.onliefoodapp.exception.NoSuchDataFoundException;
import com.km.onliefoodapp.util.ResponseStructure;

@Service
public class UserService {

	final static Logger logger = LoggerFactory.getLogger(UserService.class);

	
	@Autowired
	UserDao userDao;

	@Autowired
	FoodOrderDao foodOrderDao;
	/*
	 * =====================================================================================================================
	 * 										USER LOGIC
	 * ===================================================================================================================== 
	 * 
*/
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user)
	{
		logger.info("Entering saveUser(User user) method.");

		User u=userDao.saveUser(user);
		if(u!=null) {
		
			if(u.getRole().equals(Role.STAFF))
			{
				u.setFoodOrders(foodOrderDao.findAllFoodOrders());
			}
			
		ResponseStructure<User> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Data Save Sucessfull");
		responseStructure.setData(u);
		
		ResponseEntity<ResponseStructure<User>> responseEntity= new ResponseEntity<>(responseStructure,HttpStatus.CREATED);

		logger.info("Exiting saveUser(User user) method.");
		
		return responseEntity;
	
		}
		else
	
			throw new NoSuchDataFoundException();
	}
	public ResponseEntity<ResponseStructure<User>> findByEmail(String email)
	{
		logger.info("Entering the User Service class in findByEmail(String email) Method");

		Optional<User> u=userDao.findByEmail(email);
		if(u.isPresent()) {			
		ResponseStructure<User> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Data retrived sucessfull");
		responseStructure.setData(u.get());
		
		ResponseEntity<ResponseStructure<User>> responseEntity= new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
		
		logger.info("Exiting the User Service class in findByEmail(String email) Method");

		return responseEntity;
		}
		else 
			throw new InvalidEmailIDException();
	}

	public ResponseEntity<ResponseStructure<User>> findById(long userId)
	{
		logger.info("Entering the User Service class in findById(long userId) Method");

		Optional<User> u=userDao.findById(userId);
		if(u.isPresent()) {
		
		ResponseStructure<User> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Data retrived Sucessfull");
		responseStructure.setData(u.get());
		
		ResponseEntity<ResponseStructure<User>> responseEntity=new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);

		logger.info("Exiting the User Service class in findById(long userId) Method");

		return responseEntity;
		}
		else

			throw new NoSuchDataFoundException();
	}
	
	
	public ResponseEntity<ResponseStructure<User>> findByNumber(long phoneNumber)
	{
		logger.info("Entering the User Service class in findByNumber(long phoneNumber) Method");

		
		Optional<User> u=userDao.findByNumber(phoneNumber);
		if(u.isPresent()) {
		
		ResponseStructure<User> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Data retrived Sucessfull");
		responseStructure.setData(u.get());
		
		ResponseEntity<ResponseStructure<User>> responseEntity=new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);

		logger.info("Exiting the User Service class in findByNumber(long phoneNumber) Method");

		return responseEntity;
		}
		else

			throw new NoSuchDataFoundException();
	}
	public ResponseEntity<ResponseStructure<List<User>>> findAllUser()
	{		
		logger.info("Entering the User Service class in findAllUser() Method");

		List<User> li=userDao.findAllUser();
		if(li!=null) 
		{
		ResponseStructure<List<User>> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("All user datas are retrived sucessfull");
		responseStructure.setData(li);
		
		ResponseEntity<ResponseStructure<List<User>>> responseEntity=new ResponseEntity<ResponseStructure<List<User>>>(responseStructure, HttpStatus.OK);
		
		logger.info("Exiting the User Service class in findAllUser() Method");

		return responseEntity;
		}
		else
			throw new NoSuchDataFoundException();
	}
	
	public ResponseEntity<ResponseStructure<User>> updateUser(String email,User user)
	{
		logger.info("Entering the User Service class in updateUser(String email,User user) Method");
		
		Optional<User> u=userDao.findByEmail(email);
		if(u.isPresent())
		{
			user.setId(u.get().getId());
		ResponseStructure<User> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Data Update Sucessfull");
		responseStructure.setData(u.get());

		ResponseEntity<ResponseStructure<User>> responseEntity=new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.CREATED);

		logger.info("Exiting the User Service class in updateUser(String email,User user) Method");

		return responseEntity;
		}
		else
			throw new NoSuchDataFoundException();
	}
	
	public ResponseEntity<ResponseStructure<String>> removeUser(String email)
	{
		logger.info("Entering the User Service class in removeUser(String email) Method");

		
		Optional<User> u=userDao.findByEmail(email);
		if(u.isPresent())
		{
			String str=userDao.removeUser(u.get().getId());
			
			ResponseStructure<String> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.NO_CONTENT.value());
			responseStructure.setMessage("Deleted Sucessfull");
			responseStructure.setData(str);

			ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);

			logger.info("Exiting the User Service class in removeUser(String email) Method");

			return responseEntity;
		}
		else
			throw new NoSuchDataFoundException();
	}
}