package com.ensa.deliveryapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "cart")

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @OneToOne
    private Product product;

    private int quantity;
    

    public Cart(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public Cart() {

    }
}
