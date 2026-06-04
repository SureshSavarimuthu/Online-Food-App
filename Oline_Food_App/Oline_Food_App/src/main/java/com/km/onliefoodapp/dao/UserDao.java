package com.km.onliefoodapp.dao;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.km.onliefoodapp.controller.UserController;
import com.km.onliefoodapp.entity.User;
import com.km.onliefoodapp.repository.UserRepository;

@Repository
public class UserDao {

	final static Logger logger = LoggerFactory.getLogger(UserDao.class);

	
	@Autowired
	private UserRepository userRepository;
	
	/*
	 * =====================================================================================================================
	 * 										USER LOGIC
	 * ===================================================================================================================== 
	 * 
	*/	
	public User saveUser(User user)
	{
		logger.info("Entering the User DAO class in saveUser(User user) Method");

		User user2= userRepository.save(user);
	
		logger.info("Exiting the User DAO class in saveUser(User user) Method");

		return user2;
	}

	public Optional<User> findById(long userId)
	{
		logger.info("Entering the User DAO class in findById(long userId Method");

		Optional<User> u=userRepository.findById(userId);
	
		logger.info("Exiting the User DAO class in findById(long userId) Method");
		
		return u;
	}
	
	
	public Optional<User> findByEmail(String email)
	{
		logger.info("Entering the User DAO class in findByEmail(String email) Method");

		Optional<User> u=Optional.ofNullable(userRepository.findByEmail(email));

		logger.info("Exiting the User DAO class in findByEmail(String email) Method");

		return u;
	}

	public Optional<User> findByNumber(long phoneNumber)
	{
		logger.info("Entering the User DAO class in findByNumber(long phoneNumber) Method");
		
		User u=	userRepository.findByPhoneNumber(phoneNumber);

		logger.info("Exiting the User DAO class in findByNumber(long phoneNumber) Method");
		
		return Optional.ofNullable(u);
	}
	
	public List<User> findAllUser()
	{
		logger.info("Entering the User DAO class in findAllUser() Method");

		List<User> list= userRepository.findAll();

		logger.info("Exiting the User DAO class in findAllUser() Method");

		return list;
	}
	
	public User updateUser(User user)
	{
		logger.info("Entering the User DAO class in updateUser(User user) Method");

		User userUpdate= userRepository.save(user);

		logger.info("Exiting the User DAO class in updateUser(User user) Method");

		return userUpdate;
		
	}
	
	
	public String removeUser(long id)
	{
		logger.info("Entering the User DAO class in removeUser(long id) Method");

		userRepository.deleteById(id);

		logger.info("Exiting the User DAO class in removeUser(long id) Method");
		
		return "User details removed";
	
	}

	
}
