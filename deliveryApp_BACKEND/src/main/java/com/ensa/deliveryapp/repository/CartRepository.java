package com.ensa.deliveryapp.repository;

import com.ensa.deliveryapp.model.Cart;
import com.ensa.deliveryapp.model.Product;
import com.ensa.deliveryapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
     List<Cart> findByUser(User user);
     Cart findByUserAndProduct(User user, Product product);
}
