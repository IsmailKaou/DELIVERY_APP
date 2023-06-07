package com.ensa.deliveryapp.repository;

import com.ensa.deliveryapp.model.Order;
import com.ensa.deliveryapp.model.Product;
import com.ensa.deliveryapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<List<Order>> findByUser(User user);
}
