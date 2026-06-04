package com.km.onliefoodapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.km.onliefoodapp.dao.CartDao;
import com.km.onliefoodapp.dao.CustomerDao;
import com.km.onliefoodapp.dao.FoodOrderDao;
import com.km.onliefoodapp.dao.FoodProductDao;
import com.km.onliefoodapp.dao.FoodItemDao;
import com.km.onliefoodapp.entity.Cart;
import com.km.onliefoodapp.entity.Customer;
import com.km.onliefoodapp.entity.FoodItems;
import com.km.onliefoodapp.entity.FoodOrders;
import com.km.onliefoodapp.entity.FoodProduct;
import com.km.onliefoodapp.entity.Status;
import com.km.onliefoodapp.exception.CustomerIdNotPresentException;
import com.km.onliefoodapp.exception.FoodItemIdNotFoundException;
import com.km.onliefoodapp.exception.NoSuchDataFoundException;
import com.km.onliefoodapp.util.ResponseStructure;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private FoodProductDao foodProductDao;

    @Autowired
    private FoodOrderDao foodOrderDao;

    @Autowired
    private FoodItemDao foodItemDao;

    private Cart getOrCreateCart(Customer customer) {
        Optional<Cart> optional = cartDao.findCartByCustomer(customer);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            Cart cart = new Cart();
            cart.setCustomer(customer);
            cart.setCartItems(new ArrayList<>());
            return cartDao.saveCart(cart);
        }
    }

    public ResponseEntity<ResponseStructure<Cart>> addToCart(long customerId, FoodItems foodItem) {
        Optional<Customer> customerOpt = customerDao.findCustomerById(customerId);
        if (!customerOpt.isPresent()) {
            throw new CustomerIdNotPresentException();
        }

        Customer customer = customerOpt.get();
        Cart cart = getOrCreateCart(customer);

        FoodProduct foodProduct = foodProductDao.findByName(foodItem.getName());
        if (foodProduct == null) {
            throw new NoSuchDataFoundException();
        }

        double costPerItem = foodProduct.getTotalPrice();
        double discount = foodProduct.getDiscount();
        double finalPrice = costPerItem - ((costPerItem / 100) * discount);

        foodItem.setPrice(finalPrice);
        foodItem.setType(foodProduct.getType());
        foodItem.setDescription(foodProduct.getDescription());

        FoodItems savedItem = foodItemDao.saveFoodItem(foodItem);
        cart.getCartItems().add(savedItem);
        Cart updatedCart = cartDao.saveCart(cart);

        ResponseStructure<Cart> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Item added to cart successfully");
        response.setData(updatedCart);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Cart>> viewCart(long customerId) {
        Optional<Customer> customerOpt = customerDao.findCustomerById(customerId);
        if (!customerOpt.isPresent()) {
            throw new CustomerIdNotPresentException();
        }

        Cart cart = getOrCreateCart(customerOpt.get());

        ResponseStructure<Cart> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Cart retrieved successfully");
        response.setData(cart);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<Cart>> removeFromCart(long customerId, long foodItemId) {
        Optional<Customer> customerOpt = customerDao.findCustomerById(customerId);
        if (!customerOpt.isPresent()) {
            throw new CustomerIdNotPresentException();
        }

        Cart cart = getOrCreateCart(customerOpt.get());
        List<FoodItems> items = cart.getCartItems();
        FoodItems target = null;
        for (FoodItems item : items) {
            if (item.getId() == foodItemId) {
                target = item;
                break;
            }
        }

        if (target != null) {
            items.remove(target);
            cartDao.saveCart(cart);
            foodItemDao.deleteFoodItem(foodItemId);
        } else {
            throw new FoodItemIdNotFoundException();
        }

        ResponseStructure<Cart> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Item removed from cart successfully");
        response.setData(cart);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<ResponseStructure<FoodOrders>> checkoutCart(long customerId) {
        Optional<Customer> customerOpt = customerDao.findCustomerById(customerId);
        if (!customerOpt.isPresent()) {
            throw new CustomerIdNotPresentException();
        }

        Customer customer = customerOpt.get();
        Cart cart = getOrCreateCart(customer);

        if (cart.getCartItems().isEmpty()) {
            throw new NoSuchDataFoundException();
        }

        List<FoodItems> cartItems = cart.getCartItems();
        List<FoodProduct> foodProducts = foodProductDao.findAllFoodProduct();
        List<FoodItems> orderItems = new ArrayList<>();
        double cost = 0;

        for (FoodItems item : cartItems) {
            for (FoodProduct foodProduct : foodProducts) {
                if (item.getName().equalsIgnoreCase(foodProduct.getName())) {
                    if (item.getQuantity() <= foodProduct.getAvalibility() && foodProduct.getAvalibility() > 0) {
                        orderItems.add(item);
                        double finalPrice = foodProduct.getTotalPrice() - ((foodProduct.getTotalPrice() / 100) * foodProduct.getDiscount());
                        cost += finalPrice * item.getQuantity();

                        foodProduct.setAvalibility(foodProduct.getAvalibility() - item.getQuantity());
                        foodProductDao.saveFoodProduct(foodProduct);
                    }
                }
            }
        }

        if (orderItems.isEmpty()) {
            throw new NoSuchDataFoundException();
        }

        FoodOrders foodOrders = new FoodOrders();
        foodOrders.setCustomer(customer);
        foodOrders.setStatus(Status.RECEIVED);
        foodOrders.setPaymentStatus("PENDING");
        foodOrders.setFoodItems(orderItems);
        foodOrders.setTotalcost(cost);

        FoodOrders savedOrder = foodOrderDao.saveFoodOrder(foodOrders);

        cart.getCartItems().clear();
        cartDao.saveCart(cart);

        ResponseStructure<FoodOrders> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Cart checked out and order placed successfully");
        response.setData(savedOrder);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
