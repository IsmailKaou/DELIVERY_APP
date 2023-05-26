package com.ensa.deliveryapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Product product;

    public Cart(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public Cart() {

    }
}
