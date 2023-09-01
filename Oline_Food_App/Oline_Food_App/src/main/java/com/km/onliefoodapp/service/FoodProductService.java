package com.km.onliefoodapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.km.onliefoodapp.dao.FoodMenuDao;
import com.km.onliefoodapp.dao.FoodProductDao;
import com.km.onliefoodapp.dao.UserDao;
import com.km.onliefoodapp.entity.FoodProduct;
import com.km.onliefoodapp.exception.NoSuchDataFoundException;
import com.km.onliefoodapp.util.ResponseStructure;

@Service
public class FoodProductService {


	@Autowired
	FoodProductDao foodProductDao;

	@Autowired
	UserDao userDao; 
	
	@Autowired
	FoodMenuDao foodMenuDao;

	public ResponseEntity<ResponseStructure<FoodProduct>> saveFoodProduct(FoodProduct foodProduct)
	{
		FoodProduct orders=foodProductDao.saveFoodProduct(foodProduct);
		if(orders!=null) {
		ResponseStructure<FoodProduct> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("Data Save Sucessfull");
		responseStructure.setData(orders);
		
		ResponseEntity<ResponseStructure<FoodProduct>> responseEntity=new ResponseEntity<ResponseStructure<FoodProduct>>(responseStructure, HttpStatus.CREATED);

		return responseEntity;
		}
		else
			throw new NoSuchDataFoundException();
	}
	
	public ResponseEntity<ResponseStructure<FoodProduct>> findFoodProductByID(long id) {
		Optional<FoodProduct> foodProduct=foodProductDao.findFoodProductById(id);
		if(foodProduct.isPresent())
		{
			ResponseStructure<FoodProduct> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Data Save Sucessfull");
			responseStructure.setData(foodProduct.get());
			
			ResponseEntity<ResponseStructure<FoodProduct>> responseEntity=new ResponseEntity<ResponseStructure<FoodProduct>>(responseStructure, HttpStatus.OK);
			return responseEntity;
		}
		else
			throw new NoSuchDataFoundException();
	}
	
	public ResponseEntity<ResponseStructure<List<FoodProduct>>> findAllFoodProduct()
	{		
		List<FoodProduct> foodProduct=foodProductDao.findAllFoodProduct();
		if(foodProduct!=null)
		{
		ResponseStructure<List<FoodProduct>> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Data Save Sucessfull");
		responseStructure.setData(foodProduct);
		
		ResponseEntity<ResponseStructure<List<FoodProduct>>> responseEntity=new ResponseEntity<ResponseStructure<List<FoodProduct>>>(responseStructure, HttpStatus.OK);
		
		return responseEntity;
		}
		else
			throw new NoSuchDataFoundException();
	}
	
	public ResponseEntity<ResponseStructure<String>> removeFoodProductById(long id)
	{
		Optional<FoodProduct> foodProduct=foodProductDao.findFoodProductById(id);
		if(foodProduct.isPresent())
		{
			String str=foodProductDao.removeFoodProductById(id);
			ResponseStructure<String> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.NO_CONTENT.value());
			responseStructure.setMessage("food Product removed Sucessfull");		
			responseStructure.setData(str);
			
			ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);

			return responseEntity;
		}
		else 
			throw new NoSuchDataFoundException();
	}
}
