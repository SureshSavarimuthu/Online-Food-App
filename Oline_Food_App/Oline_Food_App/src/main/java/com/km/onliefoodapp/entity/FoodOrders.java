package com.km.onliefoodapp.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class FoodOrders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Status status;
	@CreationTimestamp
	private LocalDateTime orderCreatedTime;
	private LocalDateTime orderDeliveryTime;
	private double totalPrice;
	@OneToMany(cascade = CascadeType.ALL)
	private List<FoodProduct> products;
}
