package com.km.onliefoodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.km.onliefoodapp.entity.FoodOrders;

public interface FoodOrderRepository extends JpaRepository<FoodOrders, Long>{

}
