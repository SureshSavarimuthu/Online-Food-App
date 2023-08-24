package com.km.onliefoodapp.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.km.onliefoodapp.entity.FoodMenu;
import com.km.onliefoodapp.repository.FoodMenuRepository;

@Repository
public class FoodMenuDao {

	@Autowired
	FoodMenuRepository foodMenuRepository;

	public FoodMenu saveFoodMenu(FoodMenu foodMenu)
	{
		return foodMenuRepository.save(foodMenu);

	}
	
	public FoodMenu findFoodMenuById(long foodMenuId)
	{
		
		return foodMenuRepository.findById(foodMenuId).get();
	}
	
	public List<FoodMenu> findAllFoodMenu()
	{
	
		return foodMenuRepository.findAll();
	}
	
	public String removeFoodMenuById(long foodMenuById)
	{
		foodMenuRepository.deleteById(foodMenuById);
		return "Data removed sucessfully";
	}
}
