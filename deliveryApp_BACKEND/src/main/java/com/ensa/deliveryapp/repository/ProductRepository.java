package com.ensa.deliveryapp.repository;

import com.ensa.deliveryapp.model.Category;
import com.ensa.deliveryapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<List<Product>> findByCategory_Name(String name);
    Optional<Product> findByName(String name);

}
