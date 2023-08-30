package com.km.onliefoodapp.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.km.onliefoodapp.controller.UserController;
import com.km.onliefoodapp.dao.UserDao;
import com.km.onliefoodapp.entity.User;
import com.km.onliefoodapp.exception.InCorrectPasswordException;
import com.km.onliefoodapp.exception.InvalidEmailIDException;
import com.km.onliefoodapp.exception.NoSuchDataFoundException;
import com.km.onliefoodapp.util.ResponseStructure;

@Service
public class UserService {

	final static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserDao userDao;
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
		Optional<User> u=userDao.findByEmail(email);
		if(u.isPresent()) {			
		ResponseStructure<User> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Data retrived sucessfull");
		responseStructure.setData(u.get());
		
		ResponseEntity<ResponseStructure<User>> responseEntity= new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
		
		return responseEntity;
		}
		else 
			throw new InvalidEmailIDException();
	}

	public ResponseEntity<ResponseStructure<User>> findById(long userId)
	{
		Optional<User> u=userDao.findById(userId);
		if(u.isPresent()) {
		
		ResponseStructure<User> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Data retrived Sucessfull");
		responseStructure.setData(u.get());
		
		ResponseEntity<ResponseStructure<User>> responseEntity=new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
		
		return responseEntity;
		}
		else

			throw new NoSuchDataFoundException();
	}
	
	
	public ResponseEntity<ResponseStructure<User>> findByNumber(long phoneNumber)
	{
		Optional<User> u=userDao.findByNumber(phoneNumber);
		if(u.isPresent()) {
		
		ResponseStructure<User> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Data retrived Sucessfull");
		responseStructure.setData(u.get());
		
		ResponseEntity<ResponseStructure<User>> responseEntity=new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
		
		return responseEntity;
		}
		else

			throw new NoSuchDataFoundException();
	}
	public ResponseEntity<ResponseStructure<List<User>>> findAllUser()
	{		
		List<User> li=userDao.findAllUser();
		if(li!=null) 
		{
		ResponseStructure<List<User>> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("All user datas are retrived sucessfull");
		responseStructure.setData(li);
		
		ResponseEntity<ResponseStructure<List<User>>> responseEntity=new ResponseEntity<ResponseStructure<List<User>>>(responseStructure, HttpStatus.OK);
		
		return responseEntity;
		}
		else
			throw new NoSuchDataFoundException();
	}
	
	public ResponseEntity<ResponseStructure<User>> updateUser(String email,User user)
	{
		Optional<User> u=userDao.findByEmail(email);
		if(u.isPresent())
		{
			user.setId(u.get().getId());
		ResponseStructure<User> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Data Update Sucessfull");
		responseStructure.setData(u.get());

		ResponseEntity<ResponseStructure<User>> responseEntity=new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.CREATED);

		return responseEntity;
		}
		else
			throw new NoSuchDataFoundException();
	}
	
	public ResponseEntity<ResponseStructure<String>> removeUser(String email)
	{
		Optional<User> u=userDao.findByEmail(email);
		if(u.isPresent())
		{
			String str=userDao.removeUser(u.get().getId());
			
			ResponseStructure<String> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.NO_CONTENT.value());
			responseStructure.setMessage("Deleted Sucessfull");
			responseStructure.setData(str);

			ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);
			
			return responseEntity;
		}
		else
			throw new NoSuchDataFoundException();
	}
}