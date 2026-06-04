package com.km.onliefoodapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.km.onliefoodapp.dao.FoodItemDao;
import com.km.onliefoodapp.entity.FoodItems;
import com.km.onliefoodapp.exception.FoodItemIdNotFoundException;
import com.km.onliefoodapp.util.ResponseStructure;

@Service
public class FoodItemService {
	
	@Autowired
	FoodItemDao foodItemDao;
	
	public ResponseEntity<ResponseStructure<FoodItems>> saveFoodItem(FoodItems foodItems,long id)
	{	
		ResponseStructure<FoodItems> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("FoodItemSaved sucessfully.");
		responseStructure.setData(foodItems);
		
		ResponseEntity<ResponseStructure<FoodItems>> responseEntity=new  ResponseEntity<ResponseStructure<FoodItems>>(responseStructure, HttpStatus.CREATED);
		return responseEntity;
	}

	
	public ResponseEntity<ResponseStructure<FoodItems>> findFoodItemById(long foodItemId)
	{
		Optional<FoodItems> foodItems=foodItemDao.findFoodItemById(foodItemId);
		if(foodItems.isPresent()) {
			
		ResponseStructure<FoodItems> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("FoodItemSaved sucessfully.");
		responseStructure.setData(foodItems.get());
		
		ResponseEntity<ResponseStructure<FoodItems>> responseEntity=new  ResponseEntity<ResponseStructure<FoodItems>>(responseStructure, HttpStatus.CREATED);
		return responseEntity;
		}
		else
			throw new FoodItemIdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<FoodItems>> findByName(String name)
	{
				
				if(Optional.ofNullable(foodItemDao.findByName(name)).isPresent())
				{
					ResponseStructure<FoodItems> responseStructure= new ResponseStructure<>();
					responseStructure.setStatus(HttpStatus.OK.value());
					responseStructure.setMessage("Retrived Sucessfully");
					responseStructure.setData(foodItemDao.findByName(name));
				
					ResponseEntity<ResponseStructure<FoodItems>> entity=new ResponseEntity<ResponseStructure<FoodItems>>(responseStructure, HttpStatus.OK);
					return entity;
				}
				else 
					throw new FoodItemIdNotFoundException();
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public ResponseEntity<ResponseStructure<String>> deleteFoodItem(long foodItemId)
	{
		Optional<FoodItems> optional	=foodItemDao.findFoodItemById(foodItemId);
		if(optional.isPresent())
		{	
		ResponseStructure<String> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.NO_CONTENT.value());
		responseStructure.setMessage("Food Item removed sucessfully.");
		responseStructure.setData(foodItemDao.deleteFoodItem(foodItemId));
		
		ResponseEntity<ResponseStructure<String>> responseEntity=new  ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);
		return responseEntity;
		}
		else 
			throw new FoodItemIdNotFoundException();
	}
}
