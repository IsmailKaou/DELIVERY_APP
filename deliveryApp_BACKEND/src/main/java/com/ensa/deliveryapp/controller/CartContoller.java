package com.ensa.deliveryapp.controller;

import com.ensa.deliveryapp.model.Cart;
import com.ensa.deliveryapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CartContoller {
    @Autowired
    private CartService cartService;
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    @GetMapping("/addToCart/{productId}")
    public Cart addToCart(@PathVariable(name = "productId") Long productId ){
        return cartService.addToCart(productId);
    }
}
