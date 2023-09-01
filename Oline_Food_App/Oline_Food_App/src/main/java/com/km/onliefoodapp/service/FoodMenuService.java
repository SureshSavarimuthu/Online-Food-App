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
import com.km.onliefoodapp.exception.FoodMenuIdIsNotPresent;
import com.km.onliefoodapp.exception.NoSuchDataFoundException;
import com.km.onliefoodapp.exception.UserDataNotFoundInTheDatabase;
import com.km.onliefoodapp.exception.UserRoleDoesNotMatch;
import com.km.onliefoodapp.util.ResponseStructure;

@Service
public class FoodMenuService {

	@Autowired
	FoodMenuDao foodMenuDao;
	@Autowired
	UserDao userDao;
	
	@Autowired
	FoodProductDao foodProductDao;
	
	public ResponseEntity<ResponseStructure<FoodMenu>> saveFoodMenu(FoodMenu foodMenu, long userId)
	{
		Optional<User> user= userDao.findById(userId);
		if(user.isPresent())
		{
			Role role=user.get().getRole();
			if(role.equals(Role.ADMIN) || role.equals(Role.MANAGER))
			{
				FoodMenu menu=foodMenuDao.saveFoodMenu(foodMenu);
				ResponseStructure<FoodMenu> responseStructure=new ResponseStructure<>();
				responseStructure.setStatus(HttpStatus.CREATED.value());
				responseStructure.setMessage("Menu Data saved sucessfully");
				responseStructure.setData(menu);
				
				ResponseEntity<ResponseStructure<FoodMenu>> responseEntity=new ResponseEntity<ResponseStructure<FoodMenu>>(responseStructure,HttpStatus.CREATED);

				return responseEntity;
			}
			else
				throw new UserRoleDoesNotMatch();
		}
		else
			throw new UserDataNotFoundInTheDatabase();
	}
	
	public ResponseEntity<ResponseStructure<FoodMenu>> findFoodMenuById(Long id)
	{
		Optional<FoodMenu> menu=foodMenuDao.findFoodMenuById(id);
		if(menu.isPresent()) {
		ResponseStructure<FoodMenu> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Menu Data retrived sucessfully");
		responseStructure.setData(menu.get());
		
		ResponseEntity<ResponseStructure<FoodMenu>> responseEntity=new ResponseEntity<ResponseStructure<FoodMenu>>(responseStructure,HttpStatus.OK);

		return responseEntity;
		}
		else
			throw new NoSuchDataFoundException();
	}
	
	
	public ResponseEntity<ResponseStructure<List<FoodMenu>>> findAllFoodMenu()
	{
		List<FoodMenu> menu=foodMenuDao.findAllFoodMenu();
		if(menu!=null) {
		ResponseStructure<List<FoodMenu>> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Menu Data saved sucessfully");
		responseStructure.setData(menu);
		
		ResponseEntity<ResponseStructure<List<FoodMenu>>> responseEntity=new ResponseEntity<ResponseStructure<List<FoodMenu>>>(responseStructure, HttpStatus.OK);

		return responseEntity;
		}
		else
			throw new NoSuchDataFoundException();
	}
		
	public ResponseEntity<ResponseStructure<String>> removeById(Long id)
	{
		Optional<FoodMenu> foodMenu=foodMenuDao.findFoodMenuById(id);
		
		if(foodMenu.isPresent()) {
					String str=foodMenuDao.removeFoodMenuById(id);
				if(str!=null)
				{
					ResponseStructure<String>  responseStructure=new ResponseStructure<>();
					responseStructure.setStatus(HttpStatus.NO_CONTENT.value());
					responseStructure.setMessage("Menu removed Sucessfully");
					responseStructure.setData(str);
					
					ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NO_CONTENT);
					return responseEntity;
				}
				else
					throw new NoSuchDataFoundException();
				}
		else {
			throw new NoSuchDataFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<FoodMenu>> updateFoodMenu(long foodMenuId,FoodMenu foodMenu)
	{
		Optional<FoodMenu> optional=foodMenuDao.findFoodMenuById(foodMenuId);
		
		if(optional.isPresent())
		{
			foodMenu.setId(foodMenuId);
			ResponseStructure<FoodMenu>  responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Menu Updated Sucessfully");
			responseStructure.setData(foodMenu);
			
			ResponseEntity<ResponseStructure<FoodMenu>> responseEntity=new ResponseEntity<ResponseStructure<FoodMenu>>(responseStructure, HttpStatus.OK);
			return responseEntity;
	
		}
		else
			throw new FoodMenuIdIsNotPresent();
	}
	

	
	public ResponseEntity<ResponseStructure<List<FoodMenu>>>  updateFoodProduct(long staffId)
	{
		
		
		Optional<User> user=userDao.findById(staffId);
		
		List<FoodMenu> foodMenus=foodMenuDao.findAllFoodMenu();

		List<FoodOrders> orders=user.get().getFoodOrders();
						
		
		List<FoodMenu> updateFoodMenu = new ArrayList<FoodMenu>();
		
		
		if(user.isPresent() && user.get().getRole().equals(Role.STAFF))
		{
			for(FoodMenu menu:foodMenus)
			{
				List<FoodProduct> oldFoodProduct=menu.getFoodProducts();
				List<FoodProduct> newFoodProduct=new ArrayList<FoodProduct>();
				FoodMenu menu2=new FoodMenu();
				
				menu2.setDishes(menu.getDishes());
				menu2.setId(menu.getId());
				menu2.setFoodProducts(newFoodProduct);
				
				for(FoodProduct foodProductUpdate:oldFoodProduct )
				{	
						FoodProduct foodProduct=new FoodProduct();
						foodProduct.setAvalibility(foodProductUpdate.getAvalibility());
						foodProduct.setDescription(foodProductUpdate.getDescription());	
						foodProduct.setDiscount(foodProductUpdate.getDiscount());
						foodProduct.setId(foodProductUpdate.getId());
						foodProduct.setName(foodProductUpdate.getName());
						foodProduct.setTotalPrice(foodProductUpdate.getTotalPrice());
						foodProduct.setType(foodProductUpdate.getType());
						
						
						newFoodProduct.add(foodProduct);
												
						
					for(FoodOrders foodOrders:orders)
					{
							List<FoodItems> foodItems=	foodOrders.getFoodItems();
						for(FoodItems foodItems2:foodItems)
						{
						
							if(foodItems2.getName().equals(foodProductUpdate.getName()))
							{
								int foodItemQuantity=foodItems2.getQuantity();
								int foodProductQuantity=foodProductUpdate.getAvalibility();
								int foodProductavalability=foodProductQuantity-foodItemQuantity;
								if(foodProductavalability>=1) 
								{
									foodProduct.setAvalibility(	foodProductavalability);
									foodProduct.setDescription(foodProductUpdate.getDescription());	
									foodProduct.setDiscount(foodProductUpdate.getDiscount());
									foodProduct.setId(foodProductUpdate.getId());
									foodProduct.setName(foodProductUpdate.getName());
									foodProduct.setTotalPrice(foodProductUpdate.getTotalPrice());
									foodProduct.setType(foodProductUpdate.getType());
									
									newFoodProduct.add(foodProduct);
								}
								else
									foodProductDao.removeFoodProductById(foodProductUpdate.getId());
							}			
						}
					}		
				}
				updateFoodMenu.add(menu2);
				
			}
			
			ResponseStructure<List<FoodMenu>> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("New Food Menu Updated ");
			responseStructure.setData(updateFoodMenu);
			
			ResponseEntity<ResponseStructure<List<FoodMenu>>> entity=new ResponseEntity<ResponseStructure<List<FoodMenu>>>(responseStructure, HttpStatus.OK);

			return entity;
		}
		else 
			throw new UserDataNotFoundInTheDatabase();
	}


}
