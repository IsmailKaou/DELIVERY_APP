package com.ensa.deliveryapp.model;

import lombok.Data;

@Data
public class PaymentRequest {
    private String cardNumber;
    private String cardHolder;
    private String email;
    private String expirationDate;
    private String cvv;
    private String billingAddress;
    private String deliveryMode;
}
