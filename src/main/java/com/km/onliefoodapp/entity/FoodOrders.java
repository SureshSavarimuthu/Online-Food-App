package com.km.onliefoodapp.entity;

import java.util.List; 


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String description;
	private double totalcost;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private String paymentStatus;
	private String razorpayOrderId;
	private String razorpayPaymentId;
	private String razorpaySignature;

	@ManyToOne
	private Customer customer;
	@OneToMany(cascade = CascadeType.ALL)
	private List<FoodItems> foodItems;	
}
