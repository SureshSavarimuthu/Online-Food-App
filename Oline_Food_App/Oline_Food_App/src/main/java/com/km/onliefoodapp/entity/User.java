package com.km.onliefoodapp.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private	long id;

@NotEmpty(message = "User Name Can't be Empty")
private String name;

@Column(unique = true)
@Email(message = "Invalid email Id")
private String email;

@Column(unique = true)
@Min(value = 6000000000l)
@Max(value = 9999999999l)
private long phoneNumber;

private String password;

@Enumerated(EnumType.STRING)
private Role role;

@OneToMany
private List<FoodOrders> foodOrders;

}
