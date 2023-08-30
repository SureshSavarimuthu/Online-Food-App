package com.km.onliefoodapp.dao;


import java.util.List;
import java.util.Optional;

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
	
	public Optional<FoodMenu> findFoodMenuById(long foodMenuId)
	{
		Optional<FoodMenu> opt=	foodMenuRepository.findById(foodMenuId);
		return opt;
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
	
	public FoodMenu updateFoodMenu(FoodMenu foodMenu)
	{
		FoodMenu menu=foodMenuRepository.save(foodMenu);
		
		return menu;
	}
}
