package com.practice.onlineShop.vos;

import com.practice.onlineShop.enums.Currency;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductVO {
    private Long id;

    private String code;

    private String description;
    private double price;
    private int stock;
    private boolean valid;
    private Currency currency;
}
