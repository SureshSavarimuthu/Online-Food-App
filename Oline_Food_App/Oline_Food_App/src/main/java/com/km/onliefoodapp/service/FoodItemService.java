package com.km.onliefoodapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.km.onliefoodapp.dao.FoodItemDao;
import com.km.onliefoodapp.entity.FoodItems;
import com.km.onliefoodapp.exception.NoSuchDataFoundException;
import com.km.onliefoodapp.util.ResponseStructure;

public class FoodItemService {


	@Autowired
	FoodItemDao foodItemDao;

	public ResponseEntity<ResponseStructure<FoodItems>> saveFoodItem(FoodItems foodItems)
	{
		FoodItems items=foodItemDao.saveFoodItems(foodItems);
		if(items!=null) {
		ResponseStructure<FoodItems> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Data Save Sucessfull");
		responseStructure.setData(items);
		
		ResponseEntity<ResponseStructure<FoodItems>> responseEntity=new ResponseEntity<ResponseStructure<FoodItems>>(responseStructure, HttpStatus.CREATED);

		return responseEntity;
		}
		else
			throw new NoSuchDataFoundException();
	}
	
	public ResponseEntity<ResponseStructure<FoodItems>> findFoodItemsByID(long id) {
		FoodItems foodItems=foodItemDao.findFoodItemsById(id);
		if(foodItems!=null)
		{
			ResponseStructure<FoodItems> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Data Save Sucessfull");
			responseStructure.setData(foodItems);
			
			ResponseEntity<ResponseStructure<FoodItems>> responseEntity=new ResponseEntity<ResponseStructure<FoodItems>>(responseStructure, HttpStatus.OK);
			return responseEntity;
		}
		else
			throw new NoSuchDataFoundException();
	}
	
	public ResponseEntity<ResponseStructure<List<FoodItems>>> findAllFoodItems()
	{		
		List<FoodItems> foodItems=foodItemDao.findAllFoodItems();
		if(foodItems!=null)
		{
		ResponseStructure<List<FoodItems>> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Data Save Sucessfull");
		responseStructure.setData(foodItems);
		
		ResponseEntity<ResponseStructure<List<FoodItems>>> responseEntity=new ResponseEntity<ResponseStructure<List<FoodItems>>>(responseStructure, HttpStatus.OK);
		
		return responseEntity;
		}
		else
			throw new NoSuchDataFoundException();
	}
	
	public ResponseEntity<ResponseStructure<String>> removeFoodItemsById(long id)
	{
		String str=foodItemDao.removeFoodItemsById(id);
		if(str!=null)
		{
			ResponseStructure<String> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Data Save Sucessfull");		
			responseStructure.setData(str);
			
			ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
			return responseEntity;
		}
		else 
			throw new NoSuchDataFoundException();
	}
}
