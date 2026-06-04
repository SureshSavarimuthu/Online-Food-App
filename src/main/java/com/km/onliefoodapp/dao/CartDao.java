package com.km.onliefoodapp.dao;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.km.onliefoodapp.entity.Cart;
import com.km.onliefoodapp.entity.Customer;
import com.km.onliefoodapp.repository.CartRepository;

@Repository
public class CartDao {

    @Autowired
    private CartRepository cartRepository;

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Optional<Cart> findCartById(long id) {
        return cartRepository.findById(id);
    }

    public Optional<Cart> findCartByCustomer(Customer customer) {
        return cartRepository.findByCustomer(customer);
    }

    public void deleteCart(Cart cart) {
        cartRepository.delete(cart);
    }
}
