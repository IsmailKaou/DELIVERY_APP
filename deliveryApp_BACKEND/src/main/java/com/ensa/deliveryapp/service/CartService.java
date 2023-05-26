package com.ensa.deliveryapp.service;

import com.ensa.deliveryapp.config.JwtAuthenticationFilter;
import com.ensa.deliveryapp.model.Cart;
import com.ensa.deliveryapp.model.Product;
import com.ensa.deliveryapp.model.User;
import com.ensa.deliveryapp.repository.CartRepository;
import com.ensa.deliveryapp.repository.ProductRepository;
import com.ensa.deliveryapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Cart addToCart(Long productId){
        Product product = productRepository.findById(productId).get();
        String emailCurrentUser = JwtAuthenticationFilter.CURRENT_USER;
        User currentUser = null;
        if(emailCurrentUser != null){
            currentUser = userRepository.findByEmail(emailCurrentUser).get();
        }
        if (currentUser !=null && product!=null){
            Cart cart = new Cart(currentUser,product);
            return cartRepository.save(cart);
        }
        return null;
    }
}
