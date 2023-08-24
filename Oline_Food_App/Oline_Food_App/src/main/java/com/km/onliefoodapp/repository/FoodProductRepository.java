package com.km.onliefoodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.km.onliefoodapp.entity.FoodProduct;

public interface FoodProductRepository extends JpaRepository<FoodProduct, Long>{

}
