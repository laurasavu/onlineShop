package com.practice.onlineShop.handlers;

import com.practice.onlineShop.exceptions.InvalidCustomerIdException;
import com.practice.onlineShop.exceptions.InvalidProductException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.ResponseEntity.status;


@org.springframework.web.bind.annotation.ControllerAdvice

public class OrderHandler {
    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<String>hadleInvalidCustomerIdException(){
        return status(BAD_REQUEST).body("id ul produsului trimis nu este valid");
    }

}
