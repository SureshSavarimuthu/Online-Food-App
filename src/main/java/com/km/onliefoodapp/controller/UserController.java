package com.km.onliefoodapp.controller;

import java.util.List; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;

	@Autowired
	private org.springframework.security.authentication.AuthenticationManager authenticationManager;

	@Autowired
	private com.km.onliefoodapp.util.JwtUtil jwtUtil;

	@PostMapping("login")
	public ResponseEntity<ResponseStructure<String>> login(@RequestBody java.util.Map<String, String> request) {
		String email = request.get("email");
		String password = request.get("password");
		
		try {
			authenticationManager.authenticate(
				new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(email, password)
			);
		} catch (Exception e) {
			ResponseStructure<String> response = new ResponseStructure<>();
			response.setStatus(org.springframework.http.HttpStatus.UNAUTHORIZED.value());
			response.setMessage("Invalid email or password");
			response.setData(null);
			return new ResponseEntity<>(response, org.springframework.http.HttpStatus.UNAUTHORIZED);
		}
		
		User user = userService.findByEmail(email).getBody().getData();
		String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
		
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatus(org.springframework.http.HttpStatus.OK.value());
		response.setMessage("Login successful");
		response.setData(token);
		return new ResponseEntity<>(response, org.springframework.http.HttpStatus.OK);
	}

	@PostMapping("saveUser")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user)
	{
		logger.info("Entering the User controller save URL");
			
		ResponseEntity<ResponseStructure<User>> u=userService.saveUser(user);
		
		logger.info("Exit the User controller save URL");
		
		return u;
		
	}
	

	@GetMapping("findById")
	public ResponseEntity<ResponseStructure<User>> findById(@RequestParam long userId)
	{
		logger.info("Entering the User controller findById URL");
		
		ResponseEntity<ResponseStructure<User>> user	=userService.findById(userId);
		
		logger.info("Exit the User controller findById URL");
		
		return user;
		
	}
	
	@GetMapping("findByEmail")
	public ResponseEntity<ResponseStructure<User>> findByEmail(@RequestParam String email)
	{
		logger.info("Entering the User controller findByEmail URL");
		
		ResponseEntity<ResponseStructure<User>> user	= userService.findByEmail(email);
		
		logger.info("Exit the User controller findByEmail URL");
		
		return user;
	}
	
	@GetMapping("findByNumber")
	public ResponseEntity<ResponseStructure<User>> findByNumber(@RequestParam long phoneNumber)
	{
		logger.info("Entering the User controller findByNumber URL");

		ResponseEntity<ResponseStructure<User>> user	=	userService.findByNumber(phoneNumber);
			
		logger.info("Exit the User controller findByNumber URL");
			
		return user;	
	}
	
	@GetMapping("findAllUser")
	public ResponseEntity<ResponseStructure<List<User>>> findAllUser()
	{
		logger.info("Entering the User controller findAllUser URL");
		
		ResponseEntity<ResponseStructure<List<User>>> user= userService.findAllUser();
		
		logger.info("Entering the User controller findAllUser URL");
		
		return user;
	}
	
	@PutMapping("updateUser")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestParam String email,@RequestBody User user)
	{
		logger.info("Entering the User controller updateUser URL");
		
		ResponseEntity<ResponseStructure<User>> userUpdate= userService.updateUser(email, user);
	
		logger.info("Exit the User controller updateUser URL");
		
		return userUpdate;
	}
	
	@DeleteMapping("deleteUser")
	public ResponseEntity<ResponseStructure<String>> removeUser(@RequestParam String email)
	{
		logger.info("Entering the User controller removerUser URL");
		
		ResponseEntity<ResponseStructure<String>> user = userService.removeUser(email);
	
		logger.info("Exit the User controller removeUser URL");
	
		return user;
	}
}
