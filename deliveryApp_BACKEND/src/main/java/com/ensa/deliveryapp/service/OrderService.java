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
    private final BankAccountService bankAccountService;
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
double totalAmount = calculateTotalAmount(cartList);
    // build an order
Order order = Order.builder().user(user).
            products(getCartProducts(cartList))
            .orderStatus(Status.PROCESSING.name())
            .dateCreated(LocalDate.now())
        .expectedArrivedDate(LocalDate.now().plusDays(30))
            .totalAmount(totalAmount)
        .email(paymentRequest.getEmail())
        .deliveryMode(paymentRequest.getDeliveryMode())
        .cardHolder(paymentRequest.getCardHolder())
            .build();
    // save order to database
       if(bankAccountService.processPayment(paymentRequest.getCardNumber(),totalAmount)!=null) {
           order.setOrderStatus(Status.PLACED.name());
           orderRepository.save(order);
           cartService.clearCart();
       }

        return order;
    }
    private double calculateTotalAmount(List<Cart> cartList){
        double totalAmount=0;
        for(Cart cart:cartList) {
            totalAmount+=cart.getQuantity()*cart.getProduct().getPrice();
        }
        return totalAmount;
    }

    private List<Product> getCartProducts(List<Cart> cartList){
        List<Product> products = new ArrayList<>();
        for(Cart cart:cartList) {
            products.add(cart.getProduct());
        }
        return products;
    }

    public List<Order> getOrders() {
        User user = customerRepository.findByEmail(JwtAuthenticationFilter.CURRENT_USER).get();
        return orderRepository.findByUser(user).get();
    }
}
