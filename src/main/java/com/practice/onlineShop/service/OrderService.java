package com.practice.onlineShop.service;

import com.practice.onlineShop.entities.Orders;
import com.practice.onlineShop.entities.Product;
import com.practice.onlineShop.exceptions.InvalidCustomerIdException;
import com.practice.onlineShop.exceptions.InvalidOperationEXP;
import com.practice.onlineShop.exceptions.InvalidProductException;
import com.practice.onlineShop.exceptions.InvalidStockException;
import com.practice.onlineShop.mappers.OrderMapper;
import com.practice.onlineShop.repositories.OrderRepository;
import com.practice.onlineShop.repositories.ProductRepository;
import com.practice.onlineShop.vos.OrderVO;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {
private final OrderRepository orderRepository;
private final OrderMapper orderMapper;
private final ProductRepository productRepository;
private final StockService stockService;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ProductRepository productRepository, StockService stockService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;

        this.productRepository = productRepository;
        this.stockService = stockService;
    }

    public void addOrder(OrderVO orderVO) throws InvalidCustomerIdException, InvalidProductException, InvalidOperationEXP, InvalidStockException {
       stockService.validateStock(orderVO);
        Orders order=orderMapper.toEntity(orderVO);

        orderRepository.save(order);
        stockService.updateStock(order);
    }




}
