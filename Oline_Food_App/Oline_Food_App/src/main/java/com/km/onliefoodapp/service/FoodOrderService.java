package com.km.onliefoodapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.km.onliefoodapp.dao.FoodOrderDao;
import com.km.onliefoodapp.entity.FoodOrders;
import com.km.onliefoodapp.exception.NoSuchDataFoundException;
import com.km.onliefoodapp.util.ResponseStructure;

@Service
public class FoodOrderService {

	@Autowired
	FoodOrderDao foodOrderDao;

	public ResponseEntity<ResponseStructure<FoodOrders>> saveFoodOrder(FoodOrders foodOrders)
	{
		FoodOrders orders=foodOrderDao.saveFoodOrder(foodOrders);
		if(orders!=null) {
		ResponseStructure<FoodOrders> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Data Save Sucessfull");
		responseStructure.setData(orders);
		
		ResponseEntity<ResponseStructure<FoodOrders>> responseEntity=new ResponseEntity<ResponseStructure<FoodOrders>>(responseStructure, HttpStatus.CREATED);

		return responseEntity;
		}
		else
			throw new NoSuchDataFoundException();
	}
	
	public ResponseEntity<ResponseStructure<FoodOrders>> findFoodOrderByID(long id) {
		FoodOrders foodOrders=foodOrderDao.findFoodOrderByID(id);
		if(foodOrders!=null)
		{
			ResponseStructure<FoodOrders> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Data Save Sucessfull");
			responseStructure.setData(foodOrders);
			
			ResponseEntity<ResponseStructure<FoodOrders>> responseEntity=new ResponseEntity<ResponseStructure<FoodOrders>>(responseStructure, HttpStatus.OK);
			return responseEntity;
		}
		else
			throw new NoSuchDataFoundException();
	}
	
	public ResponseEntity<ResponseStructure<List<FoodOrders>>> findAllFoodOrders()
	{
		
		List<FoodOrders> foodOrders=foodOrderDao.findAllFoodOrders();
		if(foodOrders!=null)
		{
		ResponseStructure<List<FoodOrders>> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Data Save Sucessfull");
		responseStructure.setData(foodOrders);
		
		ResponseEntity<ResponseStructure<List<FoodOrders>>> responseEntity=new ResponseEntity<ResponseStructure<List<FoodOrders>>>(responseStructure, HttpStatus.OK);
		
		return responseEntity;
		}
		else
			throw new NoSuchDataFoundException();
	}
	
	public ResponseEntity<ResponseStructure<String>> removeFoodOrderById(long id)
	{
		String str=foodOrderDao.removeFoodOrderById(id);
		if(str!=null)
		{
			ResponseStructure<String> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.NO_CONTENT.value());
			responseStructure.setMessage("Data Save Sucessfull");		
			responseStructure.setData(str);
			
			ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
			return responseEntity;
		}
		else 
			throw new NoSuchDataFoundException();
	}
	
}
