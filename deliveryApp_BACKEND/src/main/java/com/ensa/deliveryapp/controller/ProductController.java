package com.ensa.deliveryapp.controller;

import com.ensa.deliveryapp.dto.ProductDto;
import com.ensa.deliveryapp.model.Category;
import com.ensa.deliveryapp.model.Product;
import com.ensa.deliveryapp.service.ProductService;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@Data
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
    public final ProductService productService;

    @PostConstruct
    public void initProducts(){
        productService.initProducts();
    }
    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.listAllProducts();
//        List<ProductDto> body = products.stream()
//                .map(productService::convertToDTO)
//                .collect(Collectors.toList());
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/getProducts/{category}")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable String category) {
        List<ProductDto> body = productService.listProductsByCategory(category).stream()
                .map(productService::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    @GetMapping("/getProducts/product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        System.out.printf(product.getName());
        ProductDto body = productService.convertToDTO(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
