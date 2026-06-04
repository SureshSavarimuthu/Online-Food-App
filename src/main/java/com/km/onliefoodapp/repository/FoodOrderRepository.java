package com.km.onliefoodapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.km.onliefoodapp.entity.FoodOrders;
import com.km.onliefoodapp.entity.Status;

public interface FoodOrderRepository extends JpaRepository<FoodOrders, Long> {

	List<FoodOrders> findByStatus(Status status);

	List<FoodOrders> findByPaymentStatus(String paymentStatus);

	@Query("SELECT SUM(f.totalcost) FROM FoodOrders f WHERE f.paymentStatus = 'PAID'")
	Double getTotalRevenue();

	@Query("SELECT COUNT(f) FROM FoodOrders f WHERE f.status = :status")
	Long countByStatus(Status status);
}
