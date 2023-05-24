package com.ensa.deliveryapp.controller;

import com.ensa.deliveryapp.dto.ProductDto;
import com.ensa.deliveryapp.model.Category;
import com.ensa.deliveryapp.model.Product;
import com.ensa.deliveryapp.service.ProductService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@Data
public class ProductController {
    public final ProductService productService;
    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<Product> products = productService.listAllProducts();
        List<ProductDto> body = products.stream()
                .map(productService::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    @GetMapping("/{category}")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable String category) {
        List<ProductDto> body = productService.listProductsByCategory(category).stream()
                .map(productService::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
