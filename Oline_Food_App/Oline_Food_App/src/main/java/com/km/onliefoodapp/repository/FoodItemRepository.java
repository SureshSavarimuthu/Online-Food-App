package com.km.onliefoodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.km.onliefoodapp.entity.FoodItems;

public interface FoodItemRepository  extends JpaRepository<FoodItems, Long>{

	public FoodItems findByName(String name);
}
