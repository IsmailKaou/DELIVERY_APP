package com.ensa.deliveryapp.service;

import com.ensa.deliveryapp.dto.CategoryDto;
import com.ensa.deliveryapp.dto.ProductDto;
import com.ensa.deliveryapp.model.Category;
import com.ensa.deliveryapp.model.Order;
import com.ensa.deliveryapp.model.Product;
import com.ensa.deliveryapp.repository.CategoryRepository;
import com.ensa.deliveryapp.repository.OrderRepository;
import com.ensa.deliveryapp.repository.ProductRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data

@RequiredArgsConstructor
public class AdminService {
    public final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OrderRepository orderRepository;



    public Product createProduct(ProductDto productDto) {
        // Check if the category exists
        Category category = categoryRepository.findByName(productDto.getCategory()).orElseThrow(() -> new IllegalArgumentException("Catgory not found"));
        Product product = new Product();
            product.setName(productDto.getName());
            product.setImageURL(productDto.getImageURL());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
//            product.setCategory(category);
        return productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        // Check if the product exists
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        productRepository.delete(product);
    }

    public Product updateProduct(Long productId, ProductDto productDto) {
        // Check if the product exists
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // Check if the category exists
        var category = categoryRepository.findByName(productDto.getCategory()).orElseThrow(() -> new IllegalArgumentException("Category not found"));
            product.setName(productDto.getName());
            product.setImageURL(productDto.getImageURL());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setCategory(category);
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }
    public Category createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setImageUrl(categoryDto.getImageUrl());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public Category updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setImageUrl(categoryDto.getImageUrl());

        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public List<Order> getOrdersByCustomer(String customerName) {
        return orderRepository.findByUser_Name(customerName).orElseThrow(()->new IllegalArgumentException("Customer not found"));
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.setOrderStatus(status);

        return orderRepository.save(order);
    }

}
