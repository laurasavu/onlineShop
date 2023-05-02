package com.practice.onlineShop.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    @Id
    @Column(name = "id", nullable = false)

    @SequenceGenerator(name="orderitem",allocationSize = 1)
    @GeneratedValue(generator = "orderitem",strategy = GenerationType.SEQUENCE)
    private Integer Id;
    @OneToOne
    private Product product;
    private int quantity;



}
