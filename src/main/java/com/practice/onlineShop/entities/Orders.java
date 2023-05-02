package com.practice.onlineShop.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.data.util.Lazy;

import java.io.Serial;
import java.util.Collection;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public
 class Orders {

    @Id
    @SequenceGenerator(name="ordersequence",allocationSize = 1)
    @GeneratedValue(generator = "ordersequence",strategy = GenerationType.SEQUENCE)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
     @JoinColumn(name="order_id")
    private Collection<OrderItem>orderItems;

    @OneToOne
    @JoinColumn(name="user_toId")
    private User user;


}
