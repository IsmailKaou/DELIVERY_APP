package com.ensa.deliveryapp.controller;

import com.ensa.deliveryapp.dto.CategoryDto;
import com.ensa.deliveryapp.dto.ProductDto;
import com.ensa.deliveryapp.model.Category;
import com.ensa.deliveryapp.model.Order;
import com.ensa.deliveryapp.model.Product;
import com.ensa.deliveryapp.service.AdminService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/")
@Data
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Admin", description = "The Admin API. Contains all the operations that can be performed on a admin.")
public class AdminController {
    private final AdminService adminService;
    @PostMapping("products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        Product createdProduct = adminService.createProduct(productDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        adminService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) {
        Product updatedProduct = adminService.updateProduct(productId, productDto);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @GetMapping("products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = adminService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = adminService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @PostMapping("categories")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto) {
        Category createdCategory = adminService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("categories/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        adminService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("categories/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDto categoryDto) {
        Category updatedCategory = adminService.updateCategory(categoryId, categoryDto);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @GetMapping("categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = adminService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("categories/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        Category category = adminService.getCategoryById(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = adminService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long orderId) {
        Order order = adminService.getOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<Order> updateOrderStatus(
            @PathVariable("id") Long orderId,
            @RequestParam("status") String status
    ) {
        Order updatedOrder = adminService.updateOrderStatus(orderId, status);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
    @GetMapping("/orders/{customerName}")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable String customerName) {
        List<Order> orders = adminService.getOrdersByCustomer(customerName);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
