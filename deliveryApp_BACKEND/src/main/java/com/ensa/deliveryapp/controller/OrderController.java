package com.ensa.deliveryapp.controller;

import com.ensa.deliveryapp.model.Order;
import com.ensa.deliveryapp.model.PaymentRequest;
import com.ensa.deliveryapp.service.BankAccountService;
import com.ensa.deliveryapp.service.OrderService;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Data
public class OrderController {
    private final OrderService orderService;
    private final BankAccountService bankAccountService;
    @PostConstruct
    public void initProducts(){
        bankAccountService.initProducts();
    }
    @PostMapping("/checkout")
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public Order placeOrder(@RequestBody PaymentRequest paymentRequest){
        return orderService.placeOrder(paymentRequest);
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public List<Order> getOrders(){
        return orderService.getOrders();
    }
    @GetMapping("/{orderId}")
    @PreAuthorize("hasAnyRole('ADMIN','CUSTOMER')")
    public Order getOrder(@PathVariable Long orderId){
        return orderService.getOrder(orderId);
    }
}
