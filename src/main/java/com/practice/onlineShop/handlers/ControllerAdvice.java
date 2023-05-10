package com.practice.onlineShop.handlers;

import com.practice.onlineShop.exceptions.InvalidCodeException;
import com.practice.onlineShop.exceptions.InvalidCustomerIdException;
import com.practice.onlineShop.exceptions.InvalidOperationEXP;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.ResponseEntity.status;


@org.springframework.web.bind.annotation.ControllerAdvice

public class ControllerAdvice {
   /* @ExceptionHandler(InvalidCustomerIdException.class)
    public ResponseEntity<String> hadleinvalidcustumeridexception() {
        return status(BAD_REQUEST).body("Invalid Customer ID");
    }*/

    @ExceptionHandler(InvalidOperationEXP.class)
    public ResponseEntity<String> hadleInvalidOperationEXP(InvalidOperationEXP e) {
        return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(InvalidCodeException.class)
    public ResponseEntity<String> hadleInvalidCodeException() {
        return status(BAD_REQUEST).body("Invalid Product Code ");
    }
}