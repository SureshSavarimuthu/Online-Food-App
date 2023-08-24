package com.km.onliefoodapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.km.onliefoodapp.entity.User;
import com.km.onliefoodapp.repository.UserRepository;

@Repository
public class UserDao {

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
		return userRepository.save(user);
	}

	public User findById(long userId)
	{
		return userRepository.findById(userId).get();
	}
	
	
	public User findByEmail(String email)
	{
		return userRepository.findByEmail(email);
	}

	public User findByNumber(long phoneNumber)
	{
		return userRepository.findByPhoneNumber(phoneNumber);
	}
	
	public List<User> findAllUser()
	{
		return userRepository.findAll();
	}
	
	public User updateUser(User user)
	{
		return userRepository.save(user);
	}
	
	
	public String removeUser(long id)
	{
		userRepository.deleteById(id);

		return "User details removed";
	}
}
