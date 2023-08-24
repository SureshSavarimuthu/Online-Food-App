package com.km.onliefoodapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.km.onliefoodapp.entity.FoodItems;
import com.km.onliefoodapp.repository.FoodItemRepository;

public class FoodItemDao {


	@Autowired
	FoodItemRepository foodItemRepository;
	
	public FoodItems saveFoodItems(FoodItems foodItems)
	{
		return foodItemRepository.save(foodItems);
	}
	
	public FoodItems findFoodItemsById(long id)
	{
		return foodItemRepository.findById(id).get();
	}
	
	public List<FoodItems> findAllFoodItems()
	{
		return foodItemRepository.findAll();
	}
	
	public String removeFoodItemsById(long id)
	{
		foodItemRepository.deleteById(id);
		return "FoodItems removed Sucessfully";
	}
}
