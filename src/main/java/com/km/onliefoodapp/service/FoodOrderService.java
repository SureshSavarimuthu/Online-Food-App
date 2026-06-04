package com.km.onliefoodapp.service;

import java.util.ArrayList; 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.km.onliefoodapp.dao.CustomerDao;
import com.km.onliefoodapp.dao.FoodItemDao;
import com.km.onliefoodapp.dao.FoodOrderDao;
import com.km.onliefoodapp.dao.FoodProductDao;
import com.km.onliefoodapp.entity.Customer;
import com.km.onliefoodapp.entity.FoodItems;
import com.km.onliefoodapp.entity.FoodOrders;
import com.km.onliefoodapp.entity.FoodProduct;
import com.km.onliefoodapp.exception.CustomerIdNotPresentException;
import com.km.onliefoodapp.exception.NoSuchAFoodOrder;
import com.km.onliefoodapp.exception.NoSuchDataFoundException;
import com.km.onliefoodapp.util.ResponseStructure;

@Service
public class FoodOrderService {

	@Autowired
	FoodOrderDao foodOrderDao;

	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	FoodItemDao foodItemDao;
	
	@Autowired
	FoodProductDao foodProductDao;

	@Autowired
	RazorpayService razorpayService;
	
	public ResponseEntity<ResponseStructure<FoodOrders>> saveFoodOrder(FoodOrders foodOrders,long customerId)
	{
		Optional<Customer> customer=customerDao.findCustomerById(customerId);
		
		ArrayList<FoodItems> foodItems= new ArrayList<FoodItems>();
		
		if(customer.isPresent())
		{	
				foodOrders.setCustomer(customer.get());
				foodOrders.setStatus(com.km.onliefoodapp.entity.Status.RECEIVED);
				foodOrders.setPaymentStatus("PENDING");
				List<FoodItems> foodItems2=foodOrders.getFoodItems();
				List<FoodProduct> foodProducts=	foodProductDao.findAllFoodProduct();
				double cost=0; 
				
				for(FoodItems items: foodItems2)
				{
					for(FoodProduct foodProduct: foodProducts)
					{
						if(items.getName().equalsIgnoreCase(foodProduct.getName())) 
						{
							if(items.getQuantity() <= foodProduct.getAvalibility() && foodProduct.getAvalibility() > 0) 
							{ 
									foodItems.add(items);
									double costPerItem=foodProduct.getTotalPrice();
									double discount=foodProduct.getDiscount();
									double finalPrice=(costPerItem)-((costPerItem/100)*discount);
									
									int quantityPerItem=items.getQuantity();
									
									cost=cost+(finalPrice*quantityPerItem);
									
									// Decrement stock
									foodProduct.setAvalibility(foodProduct.getAvalibility() - items.getQuantity());
									foodProductDao.saveFoodProduct(foodProduct);
							}
						}
					}
				}
				
			foodOrders.setFoodItems(foodItems);
			foodOrders.setTotalcost(cost);
			FoodOrders orders =	foodOrderDao.saveFoodOrder(foodOrders);
			ResponseStructure<FoodOrders> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("Order Placed sucessfully");				
			responseStructure.setData(orders);
			
			ResponseEntity<ResponseStructure<FoodOrders>> entity=new ResponseEntity<ResponseStructure<FoodOrders>>(responseStructure, HttpStatus.CREATED);
		
			return entity;
		}		
		else 
			throw new CustomerIdNotPresentException();
	
	}
	
	public ResponseEntity<ResponseStructure<FoodOrders>> findFoodOrderByID(long id) {
		Optional<FoodOrders> foodOrders=foodOrderDao.findFoodOrderByID(id);
		if(foodOrders.isPresent())
		{
			ResponseStructure<FoodOrders> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Food Order founded sucessfully.");
			responseStructure.setData(foodOrders.get());
			
			ResponseEntity<ResponseStructure<FoodOrders>> responseEntity=new ResponseEntity<ResponseStructure<FoodOrders>>(responseStructure, HttpStatus.OK);
			return responseEntity;
		}
		else
			throw new NoSuchAFoodOrder();
	}
	
	public ResponseEntity<ResponseStructure<List<FoodOrders>>> findAllFoodOrders()
	{
		
		List<FoodOrders> foodOrders=foodOrderDao.findAllFoodOrders();
		if(foodOrders!=null)
		{
		ResponseStructure<List<FoodOrders>> responseStructure=new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Data Save Sucessfull");
		responseStructure.setData(foodOrders);
		
		ResponseEntity<ResponseStructure<List<FoodOrders>>> responseEntity=new ResponseEntity<ResponseStructure<List<FoodOrders>>>(responseStructure, HttpStatus.OK);
		
		return responseEntity;
		}
		else
			throw new NoSuchDataFoundException();
	}
	
	public ResponseEntity<ResponseStructure<String>> removeFoodOrderById(long id)
	{
		Optional<FoodOrders> foodOrders=foodOrderDao.findFoodOrderByID(id);
		if(foodOrders.isPresent())
		{
			String str=foodOrderDao.removeFoodOrderById(id);	
			ResponseStructure<String> responseStructure=new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.NO_CONTENT.value());
			responseStructure.setMessage("Food Order deliverd sucessfully.");		
			responseStructure.setData(str);
			
			ResponseEntity<ResponseStructure<String>> responseEntity=new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
			return responseEntity;
		}
		else 
			throw new NoSuchAFoodOrder();
	}

	public ResponseEntity<ResponseStructure<FoodOrders>> createRazorpayOrder(long orderId) {
		Optional<FoodOrders> optional = foodOrderDao.findFoodOrderByID(orderId);
		if (optional.isPresent()) {
			FoodOrders order = optional.get();
			String rzpOrderId = razorpayService.createRazorpayOrder(order.getTotalcost(), String.valueOf(order.getId()));
			order.setRazorpayOrderId(rzpOrderId);
			foodOrderDao.saveFoodOrder(order);

			ResponseStructure<FoodOrders> responseStructure = new ResponseStructure<>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Razorpay order generated successfully");
			responseStructure.setData(order);

			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new NoSuchAFoodOrder();
		}
	}

	public ResponseEntity<ResponseStructure<FoodOrders>> verifyPayment(long orderId, String razorpayOrderId, String razorpayPaymentId, String razorpaySignature) {
		Optional<FoodOrders> optional = foodOrderDao.findFoodOrderByID(orderId);
		if (optional.isPresent()) {
			FoodOrders order = optional.get();
			boolean isValid = razorpayService.verifyPaymentSignature(razorpayOrderId, razorpayPaymentId, razorpaySignature);
			
			ResponseStructure<FoodOrders> responseStructure = new ResponseStructure<>();
			if (isValid) {
				order.setRazorpayPaymentId(razorpayPaymentId);
				order.setRazorpaySignature(razorpaySignature);
				order.setPaymentStatus("PAID");
				foodOrderDao.saveFoodOrder(order);

				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setMessage("Payment verified and order status updated to PAID");
				responseStructure.setData(order);
				return new ResponseEntity<>(responseStructure, HttpStatus.OK);
			} else {
				order.setPaymentStatus("FAILED");
				foodOrderDao.saveFoodOrder(order);

				responseStructure.setStatus(HttpStatus.BAD_REQUEST.value());
				responseStructure.setMessage("Payment signature verification failed");
				responseStructure.setData(order);
				return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
			}
		} else {
			throw new NoSuchAFoodOrder();
		}
	}
}
