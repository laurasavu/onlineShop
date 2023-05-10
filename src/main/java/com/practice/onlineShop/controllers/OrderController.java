package com.practice.onlineShop.controllers;

import com.practice.onlineShop.exceptions.InvalidCustomerIdException;
import com.practice.onlineShop.exceptions.InvalidOperationEXP;
import com.practice.onlineShop.exceptions.InvalidProductException;
import com.practice.onlineShop.exceptions.InvalidStockException;
import com.practice.onlineShop.service.OrderService;
import com.practice.onlineShop.vos.OrderVO;
import com.practice.onlineShop.vos.ProductVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/{customerId}")
    public void addOrder(@RequestBody OrderVO orderVO, @PathVariable Long customerId) throws InvalidCustomerIdException, InvalidProductException, InvalidOperationEXP, InvalidStockException {

        orderService.addOrder(orderVO);
    }


}


