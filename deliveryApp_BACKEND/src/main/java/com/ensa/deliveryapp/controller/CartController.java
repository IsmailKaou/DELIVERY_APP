package com.ensa.deliveryapp.controller;

import com.ensa.deliveryapp.model.Cart;
import com.ensa.deliveryapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {
    @Autowired
    private CartService cartService;
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    @GetMapping("/addToCart/{productId}")
    public Cart addToCart(@PathVariable(name = "productId") Long productId ){
        return cartService.addToCart(productId);
    }

    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    @GetMapping("/removeFromCart/{productId}")
    public Cart removeFromCart(@PathVariable(name = "productId") Long productId ){
        return cartService.removeFromCart(productId);
    }
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    @DeleteMapping("/deleteCartItem/{productId}")
    public Cart deleteCartItem(@PathVariable(name = "productId") Long productId ){
        return cartService.deleteProductFromCart(productId);
    }

    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    @GetMapping("/getCartDetails")
    public List<Cart> getCartDetails(){
        return cartService.getCartDetails();
    }
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    @DeleteMapping("/clearCart")
    public ResponseEntity<String> clearCart(){
        return ResponseEntity.ok("Cart was cleared out");
    }
}
