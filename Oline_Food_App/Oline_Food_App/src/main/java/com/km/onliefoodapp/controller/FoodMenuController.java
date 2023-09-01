package com.km.onliefoodapp.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.km.onliefoodapp.entity.FoodMenu;
import com.km.onliefoodapp.service.FoodMenuService;
import com.km.onliefoodapp.util.ResponseStructure;

@RestController
public class FoodMenuController {

	@Autowired
	FoodMenuService foodMenuService;
	
	@PostMapping("saveFoodMenu")
	public ResponseEntity<ResponseStructure<FoodMenu>> saveFoodMenu(@RequestBody FoodMenu foodMenu,@RequestParam long userID)
	{
		return foodMenuService.saveFoodMenu(foodMenu,userID);
	}

	@GetMapping("findFoodMenuById")
	public ResponseEntity<ResponseStructure<FoodMenu>> findFoodMenuByID(@RequestParam Long id)
	{
		return foodMenuService.findFoodMenuById(id);
	}
	@GetMapping("findAllFoodMenu")
	public ResponseEntity<ResponseStructure<List<FoodMenu>>> findAllFoodMenu()
	{
		return foodMenuService.findAllFoodMenu();
	}
	@DeleteMapping("deleteFoodMenu")
	public ResponseEntity<ResponseStructure<String>> deleteFoodMenuByID(@RequestParam Long id)
	{
		return foodMenuService.removeById(id);
	}
	
	@PutMapping("updateFoodMenu")
	public ResponseEntity<ResponseStructure<FoodMenu>> updateFoodMenu(@RequestParam long foodMenuId,@RequestBody FoodMenu foodMenu)
	{
		return foodMenuService.updateFoodMenu(foodMenuId, foodMenu);
	}

	@PutMapping("updateFoodMenuByStaff")
	public ResponseEntity<ResponseStructure<List<FoodMenu>>> updateFoodMenuByStaff(@RequestParam long staffId)
	{
		return foodMenuService.updateFoodProduct(staffId);
	}
	
}
