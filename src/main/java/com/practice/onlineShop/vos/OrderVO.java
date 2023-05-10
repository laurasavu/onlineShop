package com.practice.onlineShop.vos;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data

public class OrderVO {
    private Integer userId;
    //productidtoQuantity
    private Map<Integer,Integer> idTOQuantity;
}