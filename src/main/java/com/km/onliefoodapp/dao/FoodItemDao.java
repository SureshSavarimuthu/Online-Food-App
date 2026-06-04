package com.km.onliefoodapp.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.km.onliefoodapp.entity.FoodItems;
import com.km.onliefoodapp.repository.FoodItemRepository;

@Repository
public class FoodItemDao {

	@Autowired
	FoodItemRepository foodItemRepository;
	
	public FoodItems saveFoodItem(FoodItems foodItems)
	{
		return foodItemRepository.save(foodItems);
	}
	
	public Optional<FoodItems> findFoodItemById(long foodItemId)
	{
		 Optional<FoodItems> foodItems	=foodItemRepository.findById(foodItemId);
		 return foodItems;
	}
	
	public FoodItems findByName(String name)
	{
		FoodItems foodItems=	foodItemRepository.findByName(name);
		return  foodItems ;
		
	}
	
	public String deleteFoodItem(long foodItemId)
	{
		foodItemRepository.deleteById(foodItemId);
		
	return "food Item deleted Sucessfully.";
	}
	
	
}
