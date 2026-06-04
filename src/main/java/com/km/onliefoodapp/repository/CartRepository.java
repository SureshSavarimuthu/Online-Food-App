package com.km.onliefoodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.km.onliefoodapp.entity.Cart;
import com.km.onliefoodapp.entity.Customer;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomer(Customer customer);
}
