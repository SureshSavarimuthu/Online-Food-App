package com.km.onliefoodapp.service;

import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.km.onliefoodapp.dao.CustomerDao;
import com.km.onliefoodapp.entity.Customer;
import com.km.onliefoodapp.exception.CustomerIdNotPresentException;
import com.km.onliefoodapp.util.ResponseStructure;

@Service
public class CustomerService {

	@Autowired
	CustomerDao customerDao;
	
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer)
	{
		ResponseStructure<Customer> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Customer Id created Sucessfully");
		responseStructure.setData(customerDao.saveCustomer(customer));

		ResponseEntity<ResponseStructure<Customer>> entity=new ResponseEntity<ResponseStructure<Customer>>(responseStructure, HttpStatus.CREATED);
		
		return entity;
	}

	
		
	
	public ResponseEntity<ResponseStructure<Customer>> findCustomerById(long customerID)
	{
	 Optional<Customer>	customer=customerDao.findCustomerById(customerID);
	if(customer.isPresent())
	{
	 	ResponseStructure<Customer> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Customer Details are founded Sucessfully");
		responseStructure.setData(customer.get());

		ResponseEntity<ResponseStructure<Customer>> entity=new ResponseEntity<ResponseStructure<Customer>>(responseStructure, HttpStatus.CREATED);
		
		return entity;
	}
	else
		throw new CustomerIdNotPresentException();
}
	
	public ResponseEntity<ResponseStructure<Customer>> findCustomerByPhoneNumber(long customerPhoneNumber)
	{

		Optional<Customer> optional=customerDao.findCustomerByPhoneNumber(customerPhoneNumber);
		if(optional.isPresent()) 
		{
		ResponseStructure<Customer> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Customer Details are founded Sucessfully.");
		responseStructure.setData(optional.get());

		ResponseEntity<ResponseStructure<Customer>> entity=new ResponseEntity<ResponseStructure<Customer>>(responseStructure, HttpStatus.OK);
		
		return entity;
		}
		else
			throw new CustomerIdNotPresentException();	
	}

	
	public ResponseEntity<ResponseStructure<List<Customer>>> findAllCustomer()
	{	
		Optional<List<Customer>> customers=customerDao.findAllCustomer();
		if(customers.isPresent())	{
			List<Customer> list=customerDao.findAllCustomer().get();
		ResponseStructure<List<Customer>> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Customer details are retrived sucessfully.");
		responseStructure.setData(list);

		ResponseEntity<ResponseStructure<List<Customer>>> entity=new ResponseEntity<ResponseStructure<List<Customer>>>(responseStructure, HttpStatus.OK);
		return entity;
		}
	else
		throw new CustomerIdNotPresentException();
	}
	
	
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(long customerId,Customer customer)
	{
		Optional<Customer> optionalCustomer=customerDao.findCustomerById(customerId);
		
		if(optionalCustomer.isPresent())
		{
			customer.setId(customerId);
			
			Customer customerUpdate=customerDao.saveCustomer(customer);
			
			
			ResponseStructure<Customer> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Customer details Update");
			responseStructure.setData(customerUpdate);
			
			ResponseEntity<ResponseStructure<Customer>> entity=new ResponseEntity<ResponseStructure<Customer>>(responseStructure, HttpStatus.OK);
			return entity;
		}
		else 
			throw new CustomerIdNotPresentException();
	
	}
		
	
	public ResponseEntity<ResponseStructure<String>> removeCustomer(long customerId){
	
		Optional<Customer>  customerOptional=customerDao.findCustomerById(customerId);
	if(customerOptional.isPresent())	{
			 String removeCustomer=customerDao.removeCustomer(customerId);
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("Customer details are removed sucessfully.");
		responseStructure.setData(removeCustomer);

		ResponseEntity<ResponseStructure<String>> entity=new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);
		return entity;
		}
	else
		throw new CustomerIdNotPresentException();
	}
	
}
