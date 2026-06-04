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
	
	public Optional<FoodOrders> findFoodOrderByID(long id)
	{
	Optional<FoodOrders> foodOrders	= foodOrderRepository.findById(id);
	return foodOrders;
	}
	
	public List<FoodOrders> findAllFoodOrders()
	{
		return foodOrderRepository.findAll();
	}
	
	public FoodOrders updateFoodOrders(FoodOrders foodOrders)
	{
			
		return foodOrderRepository.save(foodOrders);
				
	}
	
	
	
	public String removeFoodOrderById(long id)
	{
		foodOrderRepository.deleteById(id);
		return "Order removed Sucessfully";
	}

	public Double getTotalRevenue()
	{
		Double revenue = foodOrderRepository.getTotalRevenue();
		return revenue != null ? revenue : 0.0;
	}

	public Long countByStatus(com.km.onliefoodapp.entity.Status status)
	{
		return foodOrderRepository.countByStatus(status);
	}

	public java.util.List<FoodOrders> findByStatus(com.km.onliefoodapp.entity.Status status)
	{
		return foodOrderRepository.findByStatus(status);
	}
}
