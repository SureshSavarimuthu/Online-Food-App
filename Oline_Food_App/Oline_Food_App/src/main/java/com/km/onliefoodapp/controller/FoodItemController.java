package com.km.onliefoodapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.km.onliefoodapp.entity.FoodItems;
import com.km.onliefoodapp.service.FoodItemService;
import com.km.onliefoodapp.util.ResponseStructure;

public class FoodItemController {

	@Autowired
	FoodItemService foodItemService;

	@PostMapping("saveFoodProduct")
	public ResponseEntity<ResponseStructure<FoodItems>> saveFoodProduct(@RequestBody FoodItems foodItems)
	{
		return foodItemService.saveFoodItem(foodItems);
	}
	
	@GetMapping("findFoodProductByID")
	public ResponseEntity<ResponseStructure<FoodItems>> findFoodProductById(@RequestParam long id)
	{
		return foodItemService.findFoodItemsByID(id);
	}
	
	@GetMapping("findAllFoodProduct")
	public ResponseEntity<ResponseStructure<List<FoodItems>>> findAllFoodProduct()
	{
		return foodItemService.findAllFoodItems();
	}
	
	@DeleteMapping("removeFoodProductById")
	public ResponseEntity<ResponseStructure<String>> removeFoodItem(@RequestParam long id)
	{
		return foodItemService.removeFoodItemsById(id);
	}
}
