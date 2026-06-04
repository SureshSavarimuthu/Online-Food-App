package com.km.onliefoodapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.km.onliefoodapp.entity.Customer;
import com.km.onliefoodapp.repository.CustomerRepository;

@Repository
public class CustomerDao {

	@Autowired
	CustomerRepository customerRepository;
	
	public Customer saveCustomer(Customer customer)
	{
		return customerRepository.save(customer);
	}
	
	public Optional<Customer> findCustomerById(long customerId)
	{
		Optional<Customer> custOptional=customerRepository.findById(customerId);
		return custOptional;
	}
	
	public Optional<Customer> findCustomerByPhoneNumber(long customerPhoneNumber)
	{
		Customer customer =customerRepository.findByPhoneNumber(customerPhoneNumber);
		return Optional.ofNullable(customer);	
	}
	
	public Optional<List<Customer>> findAllCustomer()
	{
		List<Customer> customers=customerRepository.findAll();
		return Optional.ofNullable(customers);
	}
	
	public Customer updateCustomer(Customer customer)
	{
		return customerRepository.save(customer);
	}
	
	
	
	public String removeCustomer(long customerId)
	{
		customerRepository.deleteById(customerId);
		return "Customer Detail removed sucessfully";
	}
	
	
	
	
	

}
