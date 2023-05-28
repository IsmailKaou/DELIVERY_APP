package com.ensa.deliveryapp.service;

import com.ensa.deliveryapp.config.JwtAuthenticationFilter;
import com.ensa.deliveryapp.model.Cart;
import com.ensa.deliveryapp.model.Product;
import com.ensa.deliveryapp.model.User;
import com.ensa.deliveryapp.repository.CartRepository;
import com.ensa.deliveryapp.repository.ProductRepository;
import com.ensa.deliveryapp.repository.UserRepository;
import com.sun.source.doctree.SeeTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            Cart cart = cartRepository.findByUserAndProduct(currentUser,product);
            if(cart !=null){
                cart.setQuantity(cart.getQuantity()+1);
                return cartRepository.save(cart);
            }else {
                Cart newCart = new Cart(currentUser,product);
                newCart.setQuantity(1);
                return cartRepository.save(newCart);
            }
        }
        return null;
    }
    public Cart removeFromCart(Long productId) {
        Product product = productRepository.findById(productId).get();
        String emailCurrentUser = JwtAuthenticationFilter.CURRENT_USER;
        User currentUser = null;
        if(emailCurrentUser != null){
            currentUser = userRepository.findByEmail(emailCurrentUser).get();
        }
        if (currentUser !=null && product!=null ){
            Cart cart = cartRepository.findByUserAndProduct(currentUser,product);
            if(cart !=null){
                if(cart.getQuantity()>1){

                    cart.setQuantity(cart.getQuantity()-1);
                    return cartRepository.save(cart);
                }else{
                    return  null;
                }
            }else {
                return null;
            }
        }
        return null;
    }
    public Cart deleteProductFromCart(Long productId) {
        Product product = productRepository.findById(productId).get();
        String emailCurrentUser = JwtAuthenticationFilter.CURRENT_USER;
        User currentUser = null;
        if(emailCurrentUser != null){
            currentUser = userRepository.findByEmail(emailCurrentUser).get();
        }
        if (currentUser !=null && product!=null ){
            Cart cart = cartRepository.findByUserAndProduct(currentUser,product);
            if(cart !=null){
                cartRepository.delete(cart);
            }else {
                return null;
            }
        }
        return null;
    }

    public List<Cart> getCartDetails() {
        String emailCurrentUser = JwtAuthenticationFilter.CURRENT_USER;
        User currentUser = null;
        currentUser = userRepository.findByEmail(emailCurrentUser).get();
        return cartRepository.findByUser(currentUser);
    }

}
