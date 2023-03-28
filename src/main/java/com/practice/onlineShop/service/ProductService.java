package com.practice.onlineShop.service;

import com.practice.onlineShop.entities.Product;
import com.practice.onlineShop.exceptions.productNotFoundException;
import com.practice.onlineShop.mappers.ProductMapper;
import com.practice.onlineShop.repositories.ProductRepository;
import com.practice.onlineShop.vos.ProductVO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final  ProductMapper productMapper;
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }


    public void addProduct(ProductVO productVO,Long customerId){
        System.out.println("customer id in service"+customerId);
    Product product=productMapper.toEntity(productVO);
    productRepository.save(product);
    }

    public ProductVO getPrduct(String productCode) throws productNotFoundException {
      Optional<Product>productOptional=productRepository.findByCode(productCode);
      if(productOptional.isEmpty()){
          throw new productNotFoundException();
      }
        Product product=productOptional.get();
              return  productMapper.toVO(product);
    }


}
