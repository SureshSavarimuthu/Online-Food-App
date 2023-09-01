package com.km.onliefoodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository; 

import com.km.onliefoodapp.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long>{

	public Customer findByPhoneNumber(long phoneNumber);
}
