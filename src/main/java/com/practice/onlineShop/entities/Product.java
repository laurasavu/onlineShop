package com.practice.onlineShop.entities;

import com.practice.onlineShop.enums.Currency;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
public class Product {
    @Id
    @Column(name = "id", nullable = false)

    @SequenceGenerator(name="productsequence",allocationSize = 1)
    @GeneratedValue(generator = "productsequence",strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true)
    private String code;

    private String description;
    private double price;
    private int stock;
    private boolean valid;
   /* @ElementCollection
    @CollectionTable(name="currency",joinColumns = @JoinColumn(name=" price"))
    @Column(name="currency")*/
    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Product() {
    }


}
