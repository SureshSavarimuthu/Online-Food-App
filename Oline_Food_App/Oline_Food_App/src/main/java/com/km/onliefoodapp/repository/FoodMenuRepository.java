package com.km.onliefoodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.km.onliefoodapp.entity.FoodMenu;

public interface FoodMenuRepository extends JpaRepository<FoodMenu, Long>{

	
}
