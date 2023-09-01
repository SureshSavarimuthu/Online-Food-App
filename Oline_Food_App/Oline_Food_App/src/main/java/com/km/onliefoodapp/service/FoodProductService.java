package com.km.onliefoodapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.km.onliefoodapp.dao.FoodMenuDao;
import com.km.onliefoodapp.dao.FoodProductDao;
import com.km.onliefoodapp.dao.UserDao;
import com.km.onliefoodapp.entity.FoodItems;
import com.km.onliefoodapp.entity.FoodMenu;
import com.km.onliefoodapp.entity.FoodOrders;
import com.km.onliefoodapp.entity.FoodProduct;
import com.km.onliefoodapp.entity.Role;
import com.km.onliefoodapp.entity.User;
import com.km.onliefoodapp.exception.NoSuchDataFoundException;
import com.km.onliefoodapp.exception.UserDataNotFoundInTheDatabase;
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
		FoodProduct foodProduct=foodProductDao.findFoodProductById(id);
		if(foodProduct!=null)
		{
			ResponseStructure<FoodProduct> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Data Save Sucessfull");
			responseStructure.setData(foodProduct);
			
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
		String str=foodProductDao.removeFoodProductById(id);
		if(str!=null)
		{
			ResponseStructure<String> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.NO_CONTENT.value());
			responseStructure.setMessage("Data Save Sucessfull");		
			responseStructure.setData(str);
			
			ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);
			return responseEntity;
		}
		else 
			throw new NoSuchDataFoundException();
	}
	
//	public List<FoodMenu>  updateFoodProduct(long staffId)
//	{
//		List<FoodMenu> updateFoodMenu = new ArrayList<FoodMenu>();
//		
//		
//		Optional<User> user=userDao.findById(staffId);
//		
//		List<FoodMenu> foodMenus=foodMenuDao.findAllFoodMenu();
//		
//		
//		if(user.isPresent() && user.get().getRole().equals(Role.STAFF))
//		{
//			for(FoodMenu menu:foodMenus)
//			{
//				FoodMenu menu2=new FoodMenu();
//				menu2.setDishes(menu.getDishes());
//				menu2.setId(menu.getId());	
//				List<FoodProduct> products2=new ArrayList<FoodProduct>();
//				
//				
//					List<FoodOrders> orders=user.get().getFoodOrders();
//					List<FoodProduct> products=foodProductDao.findAllFoodProduct();
//					
//					for(FoodOrders foodOrders:orders)
//					{
//					List<FoodItems> foodItems=	foodOrders.getFoodItems();
//					for(FoodItems foodItems2:foodItems)
//					{
//						for(FoodProduct foodProductUpdate:products )
//						{
//							if(foodItems2.getName().equals(foodProductUpdate.getName()))
//							{
//								int foodItemQuantity=foodItems2.getQuantity();
//								int foodProductQuantity=foodProductUpdate.getAvalibility();
//								int foodProductavalability=foodProductQuantity-foodItemQuantity;
//								if(foodProductavalability!=0) {
//									foodProductUpdate.setAvalibility(foodProductavalability);
//									products2.add(foodProductUpdate);
//								}
//								else
//									foodProductDao.removeFoodProductById(foodProductUpdate.getId());
//							}
//							else
//							{
//								products2.add(foodProductUpdate);
//							}
//						}
//					}			
//				}
//						menu2.setFoodProducts(products2);
//					updateFoodMenu.add(menu2);
//			}
//		
//			return updateFoodMenu;
//		}
//		else 
//			throw new UserDataNotFoundInTheDatabase();
//	}	
}
