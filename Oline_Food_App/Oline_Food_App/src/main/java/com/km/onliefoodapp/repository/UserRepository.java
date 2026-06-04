package com.km.onliefoodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.km.onliefoodapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmail(String email);
	User findByPhoneNumber(long phoneNumber);
}
