package com.km.onliefoodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.km.onliefoodapp.entity.FoodProduct;
import com.km.onliefoodapp.entity.Type;

public interface FoodProductRepository extends JpaRepository<FoodProduct, Long>{

	public FoodProduct findByName(String name);
	public FoodProduct findAllByType(Type type);
}
