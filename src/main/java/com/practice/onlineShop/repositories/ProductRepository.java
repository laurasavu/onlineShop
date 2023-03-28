package com.practice.onlineShop.repositories;

import com.practice.onlineShop.entities.Product;
import com.practice.onlineShop.vos.ProductVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Double> {

    Optional<Product>findByCode(String productCode);
}
