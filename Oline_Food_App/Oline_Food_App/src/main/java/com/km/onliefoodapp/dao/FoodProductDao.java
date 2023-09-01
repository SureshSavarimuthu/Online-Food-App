package com.km.onliefoodapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.km.onliefoodapp.entity.FoodProduct;
import com.km.onliefoodapp.entity.Type;
import com.km.onliefoodapp.repository.FoodProductRepository;


@Repository
public class FoodProductDao {

	@Autowired
	FoodProductRepository foodProductRepository;
	
	public FoodProduct saveFoodProduct(FoodProduct foodProduct)
	{
		return foodProductRepository.save(foodProduct);
	}
	
	public Optional<FoodProduct> findFoodProductById(long id)
	{
		Optional<FoodProduct> opt	=foodProductRepository.findById(id);
		return opt;
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
	
	public List<FoodProduct> findByType(Type type)
	{
		List<FoodProduct> foodProducts=	(List<FoodProduct>) foodProductRepository.findAllByType(type);
	
		return foodProducts;
	}
	
	public FoodProduct updateFoodProduct(FoodProduct foodProduct)
	{
		return foodProductRepository.save(foodProduct);
	}

	public List<FoodProduct> foodProductByStaff(long userId)
	{
		return foodProductRepository.findAll();
	}
}
