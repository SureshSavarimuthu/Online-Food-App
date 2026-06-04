package com.km.onliefoodapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.km.onliefoodapp.entity.Cart;
import com.km.onliefoodapp.entity.FoodItems;
import com.km.onliefoodapp.entity.FoodOrders;
import com.km.onliefoodapp.service.CartService;
import com.km.onliefoodapp.util.ResponseStructure;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("add")
    public ResponseEntity<ResponseStructure<Cart>> addToCart(
            @RequestParam long customerId,
            @RequestBody FoodItems foodItem) {
        return cartService.addToCart(customerId, foodItem);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<Cart>> viewCart(@RequestParam long customerId) {
        return cartService.viewCart(customerId);
    }

    @DeleteMapping("remove")
    public ResponseEntity<ResponseStructure<Cart>> removeFromCart(
            @RequestParam long customerId,
            @RequestParam long foodItemId) {
        return cartService.removeFromCart(customerId, foodItemId);
    }

    @PostMapping("checkout")
    public ResponseEntity<ResponseStructure<FoodOrders>> checkoutCart(@RequestParam long customerId) {
        return cartService.checkoutCart(customerId);
    }
}
