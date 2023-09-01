package com.km.onliefoodapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.km.onliefoodapp.entity.FoodOrders;
import com.km.onliefoodapp.service.FoodOrderService;
import com.km.onliefoodapp.util.ResponseStructure;

@RestController
public class FoodOrderController {

	@Autowired
	FoodOrderService foodOrderService;
	
	@PostMapping("saveFoodOrder")
	public ResponseEntity<ResponseStructure<FoodOrders>> saveFoodOrder(@RequestBody FoodOrders foodOrders,@RequestParam long customerId)
	{
		return foodOrderService.saveFoodOrder(foodOrders,customerId);
	}
	
	@GetMapping("findFoodOrderByID")
	public ResponseEntity<ResponseStructure<FoodOrders>> findFoodOrderByID(@RequestParam long foodOrderId)
	{
		return foodOrderService.findFoodOrderByID(foodOrderId);
	}
	
	@GetMapping("findAllFoodOrder")
	public ResponseEntity<ResponseStructure<List<FoodOrders>>> findAllFoodOrders()
	{
		return foodOrderService.findAllFoodOrders();
	}
	
	
	
	
	@DeleteMapping("deleteFoodOrderByID")
	public ResponseEntity<ResponseStructure<String>> removeFoodOrderById(@RequestParam long foodOrderId)
	{
		return foodOrderService.removeFoodOrderById(foodOrderId);
	}
}
