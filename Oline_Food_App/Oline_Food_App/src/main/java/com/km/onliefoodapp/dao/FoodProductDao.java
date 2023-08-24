package com.km.onliefoodapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.km.onliefoodapp.entity.FoodProduct;
import com.km.onliefoodapp.repository.FoodProductRepository;


@Repository
public class FoodProductDao {

	@Autowired
	FoodProductRepository foodProductRepository;
	
	public FoodProduct saveFoodProduct(FoodProduct foodProduct)
	{
		return foodProductRepository.save(foodProduct);
	}
	
	public FoodProduct findFoodProductById(long id)
	{
		return foodProductRepository.findById(id).get();
	}
	
	public List<FoodProduct> findAllFoodProduct()
	{
		return foodProductRepository.findAll();
	}
	
	public String removeFoodProductById(long id)
	{
		foodProductRepository.deleteById(id);
		return "FoodProduct removed Sucessfully";
	}
	
}
