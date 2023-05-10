package com.practice.onlineShop.handlers;

import com.practice.onlineShop.exceptions.InvalidCustomerIdException;
import com.practice.onlineShop.exceptions.InvalidStockException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.ResponseEntity.status;

@org.springframework.web.bind.annotation.ControllerAdvice
public class StockHandler {
    @ExceptionHandler(InvalidStockException.class)
    public ResponseEntity<String> hadleInvalidStckIdException() {
        return status(BAD_REQUEST).body("Invalid stock for this product");
    }
}
