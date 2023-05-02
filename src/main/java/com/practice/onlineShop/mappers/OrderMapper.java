package com.practice.onlineShop.mappers;

import com.practice.onlineShop.entities.OrderItem;
import com.practice.onlineShop.entities.Orders;
import com.practice.onlineShop.entities.Product;
import com.practice.onlineShop.entities.User;
import com.practice.onlineShop.exceptions.InvalidCustomerIdException;
import com.practice.onlineShop.exceptions.InvalidOperationEXP;
import com.practice.onlineShop.exceptions.InvalidProductException;
import com.practice.onlineShop.repositories.ProductRepository;
import com.practice.onlineShop.repositories.UserRepository;
import com.practice.onlineShop.vos.OrderVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class OrderMapper { private final UserRepository userRepository;
private final ProductRepository productRepository;
    public OrderMapper(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Orders toEntity(OrderVO orderVO) throws InvalidCustomerIdException, InvalidOperationEXP, InvalidProductException {

        Orders orders=new Orders();
        OrderItem orderItem=new OrderItem();
        if(orderVO==null){throw new InvalidProductException();}
        validateOrder(orderVO);
        Optional<User> userOptional=userRepository.findById(orderVO.getUserId());
        if(userOptional.isEmpty()){
        throw new InvalidCustomerIdException();
        }

        orders.setUser(userOptional.get());

        Map<Integer,Integer>ProductToQuantityMap=orderVO.getIdTOQuantity();
        List<OrderItem>orderItems=new ArrayList<>();
        for(Integer productID:ProductToQuantityMap.keySet()) {
           Optional<Product>productOptional= productRepository.findById( productID);
           if(productOptional.isEmpty()){
               throw new InvalidProductException();
           }
           orderItem.setProduct(productOptional.get());
            Integer productquanity=ProductToQuantityMap.get(productID);
            orderItem.setQuantity(productquanity);
           orderItems.add(orderItem);
        }
        orders.setOrderItems(orderItems);
        return orders;
    }

    private void validateOrder(OrderVO orderVO) throws InvalidCustomerIdException, InvalidOperationEXP {
        if(orderVO.getUserId()==null){
            throw new InvalidCustomerIdException();
        }

        if(orderVO.getIdTOQuantity().keySet().isEmpty()){
            throw new InvalidOperationEXP();
        }
    }


}
