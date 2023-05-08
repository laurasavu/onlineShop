package com.practice.onlineShop.handlers;

import com.practice.onlineShop.exceptions.InvalidCustomerIdException;
import com.practice.onlineShop.exceptions.productNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.ResponseEntity.status;


@org.springframework.web.bind.annotation.ControllerAdvice

public class ProductHandler {
    @ExceptionHandler(InvalidCustomerIdException.class)
    public ResponseEntity<String> hadleInvalidCustomerIdException() {
        return status(BAD_REQUEST).body("Invalid Customer ID");
    }


        @ExceptionHandler(productNotFoundException.class)
        public ResponseEntity<String> hadleproductNotFoundException() {
            return status(BAD_REQUEST).body("Product not Found");
        }


}