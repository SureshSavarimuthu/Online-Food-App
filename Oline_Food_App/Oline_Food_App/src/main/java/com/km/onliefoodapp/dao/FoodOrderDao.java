package com.km.onliefoodapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.km.onliefoodapp.entity.FoodOrders;
import com.km.onliefoodapp.repository.FoodOrderRepository;

@Repository
public class FoodOrderDao {

	@Autowired
	FoodOrderRepository foodOrderRepository;
	
	public FoodOrders saveFoodOrder(FoodOrders foodOrders)
	{
		return foodOrderRepository.save(foodOrders);
	}
	
	public FoodOrders findFoodOrderByID(long id)
	{
		return foodOrderRepository.findById(id).get();
	}
	
	public List<FoodOrders> findAllFoodOrders()
	{
		return foodOrderRepository.findAll();
	}
	
	public FoodOrders updateFoodOrders(FoodOrders foodOrders)
	{
			FoodOrders foodOrders2=foodOrderRepository.save(foodOrders)
		return foodOrders2;
				
	}
	
	
	
	public String removeFoodOrderById(long id)
	{
		foodOrderRepository.deleteById(id);
		return "Order removed Sucessfully";
	}
	
}
