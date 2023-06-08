package com.ensa.deliveryapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private @NotNull User user;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private @NotNull LocalDate dateCreated;
    private @NotNull LocalDate expectedArrivedDate;
    private String orderStatus;
    private @NotNull double totalAmount;
    @JsonIgnore
    private String cardNumber;
    @JsonIgnore
    private String cardHolder;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String expirationDate;
    @JsonIgnore
    private String cvv;
    private String billingAddress;
    private String deliveryMode;
    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

}
