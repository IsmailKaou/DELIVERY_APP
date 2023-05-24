package com.ensa.deliveryapp.repository;

import com.ensa.deliveryapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

}