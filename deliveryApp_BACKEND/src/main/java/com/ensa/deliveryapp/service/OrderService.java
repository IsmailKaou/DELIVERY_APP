package com.ensa.deliveryapp.service;

import com.ensa.deliveryapp.config.JwtAuthenticationFilter;
import com.ensa.deliveryapp.model.*;
import com.ensa.deliveryapp.repository.CustomerRepository;
import com.ensa.deliveryapp.repository.OrderRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    public Order getOrder(Long id){
        return orderRepository.findById(id).get();
    }
    @Transactional
    public Order placeOrder(PaymentRequest paymentRequest){
        // get cart list
    List<Cart> cartList = cartService.getCartDetails();

    if(cartList.isEmpty())
        return null;
    // get current user
    User user = customerRepository.findByEmail(JwtAuthenticationFilter.CURRENT_USER).get();
        System.out.printf(user.getName());

    // build an order
Order order = Order.builder().user(user).
            products(getCartProducts(cartList))
            .orderStatus(Status.PENDING_PAYMENT.name())
            .dateCreated(LocalDate.now())
            .totalAmount(calculateTotalAmount(cartList))
        .email(paymentRequest.getEmail())
        .cardHolder(paymentRequest.getCardHolder())
            .build();
    // save order to database
        String message = processPayment(paymentRequest);
        orderRepository.save(order);
       cartService.clearCart();
        return order;
    }
    private double calculateTotalAmount(List<Cart> cartList){
        double totalAmount=0;
        for(Cart cart:cartList) {
            totalAmount+=cart.getQuantity()*cart.getProduct().getPrice();
        }
        return totalAmount;
    }
    private String processPayment(PaymentRequest paymentRequest){
        return "Your order has been shipped to "+paymentRequest.getBillingAddress();
    }
    private List<Product> getCartProducts(List<Cart> cartList){
        List<Product> products = new ArrayList<>();
        for(Cart cart:cartList) {
            products.add(cart.getProduct());
        }
        return products;
    }
}
