package com.km.onliefoodapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.km.onliefoodapp.entity.User;
import com.km.onliefoodapp.service.UserService;
import com.km.onliefoodapp.util.ResponseStructure;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("saveUser")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user)
	{
		return userService.saveUser(user);
	}
	

	@GetMapping("findById")
	public ResponseEntity<ResponseStructure<User>> findById(@RequestParam long userId)
	{
		return userService.findById(userId);
	}
	
	@GetMapping("findByEmail")
	public ResponseEntity<ResponseStructure<User>> findByEmail(@RequestParam String email)
	{
		return userService.findByEmail(email);
	}
	
	@GetMapping("findByNumber")
	public ResponseEntity<ResponseStructure<User>> findByEmail(@RequestParam long phoneNumber)
	{
		return userService.findByNumber(phoneNumber);
	}
	
	@GetMapping("findAllUser")
	public ResponseEntity<ResponseStructure<List<User>>> findAllUser()
	{
		return userService.findAllUser();
	}
	
	@PutMapping("updateUser")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestParam String email,@RequestBody User user)
	{
		return userService.updateUser(email, user);
	}
	
	@DeleteMapping("deleteUser")
	public ResponseEntity<ResponseStructure<String>> removeUser(@RequestParam String email)
	{
		return userService.removeUser(email);
	}
}
