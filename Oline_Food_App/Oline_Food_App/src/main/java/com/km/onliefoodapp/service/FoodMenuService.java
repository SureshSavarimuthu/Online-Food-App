package com.km.onliefoodapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.km.onliefoodapp.dao.FoodMenuDao;
import com.km.onliefoodapp.dao.UserDao;
import com.km.onliefoodapp.entity.FoodMenu;
import com.km.onliefoodapp.entity.Role;
import com.km.onliefoodapp.entity.User;
import com.km.onliefoodapp.exception.NoSuchDataFoundException;
import com.km.onliefoodapp.util.ResponseStructure;

@Service
public class FoodMenuService {

	@Autowired
	FoodMenuDao foodMenuDao;
	@Autowired
	UserDao userDao;
	
	public ResponseEntity<ResponseStructure<FoodMenu>> saveFoodMenu(FoodMenu foodMenu, long userId)
	{
		User user= userDao.findById(userId);
		if(user!=null)
		{
			Role role=user.getRole();
			if(role.equals(Role.ADMIN) || role.equals(Role.MANAGER))
			{
				FoodMenu menu=foodMenuDao.saveFoodMenu(foodMenu);
				if(menu!=null) {
				ResponseStructure<FoodMenu> responseStructure=new ResponseStructure<>();
				responseStructure.setStatus(HttpStatus.CREATED.value());
				responseStructure.setMessage("Menu Data saved sucessfully");
				responseStructure.setData(menu);
				
				ResponseEntity<ResponseStructure<FoodMenu>> responseEntity=new ResponseEntity<ResponseStructure<FoodMenu>>(responseStructure,HttpStatus.CREATED);

				return responseEntity;
				}
				else
					throw new NoSuchDataFoundException();
			}
			else
				throw new NoSuchDataFoundException();
		}
		else
			throw new NoSuchDataFoundException();
	}
	
	public ResponseEntity<ResponseStructure<FoodMenu>> findFoodMenuById(Long id)
	{
		FoodMenu menu=foodMenuDao.findFoodMenuById(id);
		if(menu!=null) {
		ResponseStructure<FoodMenu> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Menu Data retrived sucessfully");
		responseStructure.setData(menu);
		
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
		String str=foodMenuDao.removeFoodMenuById(id);
	if(str!=null)
	{
		ResponseStructure<String>  responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Menu removed Sucessfully");
		responseStructure.setData(str);
		
		ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
		return responseEntity;
	}
	else
		throw new NoSuchDataFoundException();
	}

}
