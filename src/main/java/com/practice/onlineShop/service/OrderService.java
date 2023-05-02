package com.practice.onlineShop.service;

import com.practice.onlineShop.entities.Orders;
import com.practice.onlineShop.exceptions.InvalidCustomerIdException;
import com.practice.onlineShop.exceptions.InvalidOperationEXP;
import com.practice.onlineShop.exceptions.InvalidProductException;
import com.practice.onlineShop.mappers.OrderMapper;
import com.practice.onlineShop.repositories.OrderRepository;
import com.practice.onlineShop.vos.OrderVO;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
private final OrderRepository orderRepository;
private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;

    }

    public void addOrder(OrderVO orderVO) throws InvalidCustomerIdException, InvalidProductException, InvalidOperationEXP {
        Orders orders=orderMapper.toEntity(orderVO);
        Orders order=new Orders();
        orderRepository.save(order);
        orderRepository.save(orders);
    }




}
