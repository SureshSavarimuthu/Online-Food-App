package com.km.onliefoodapp.dao;

import java.util.List;
import java.util.Optional;

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

	public Optional<User> findById(long userId)
	{
	Optional<User> u=userRepository.findById(userId);
		return u;
	}
	
	
	public Optional<User> findByEmail(String email)
	{
		Optional<User> u=Optional.of(userRepository.findByEmail(email));
		return u;
	}

	public Optional<User> findByNumber(long phoneNumber)
	{
		User u=	userRepository.findByPhoneNumber(phoneNumber);
		return Optional.ofNullable(u);
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
