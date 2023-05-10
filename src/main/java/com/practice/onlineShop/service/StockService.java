package com.practice.onlineShop.service;

import com.practice.onlineShop.entities.OrderItem;
import com.practice.onlineShop.entities.Orders;
import com.practice.onlineShop.entities.Product;
import com.practice.onlineShop.exceptions.InvalidProductException;
import com.practice.onlineShop.exceptions.InvalidStockException;
import com.practice.onlineShop.repositories.ProductRepository;
import com.practice.onlineShop.vos.OrderVO;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class StockService {
    private final ProductRepository productRepository;


    public StockService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }
    void validateStock(OrderVO orderVO) throws InvalidProductException, InvalidStockException {
        Map<Integer,Integer> ProductToQuantityMap=orderVO.getIdTOQuantity();
        for(Integer productID:ProductToQuantityMap.keySet()) {
            Optional<Product> productOptional = productRepository.findById(productID);
            if (productOptional.isEmpty()) {
                throw new InvalidProductException();
            }
            int stock=productOptional.get().getStock();
            Integer productquanity=ProductToQuantityMap.get(productID);
            if(productquanity>stock){
                throw new InvalidStockException();
            }
        }

    }

    public void updateStock(Orders order) {

        for(OrderItem orderItem:order.getOrderItems()) {
           Product product=orderItem.getProduct();
            product.setStock(product.getStock()- orderItem.getQuantity());
            productRepository.save(product);
        }
    }
}
