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
    @ExceptionHandler(InvalidCustomerIdException.class)
    public ResponseEntity<String> hadleinvalidcustumeridexception() {
        return status(BAD_REQUEST).body("id-ul trimis este invalid");
    }

    @ExceptionHandler(InvalidOperationEXP.class)
    public ResponseEntity<String> hadleInvalidOperationEXP() {
        return status(BAD_REQUEST).body("Userul nu are permisiunea de a modifica acest produs");
    }

    @ExceptionHandler(InvalidCodeException.class)
    public ResponseEntity<String> hadleInvalidCodeException() {
        return status(BAD_REQUEST).body("codul trimis este invalid");
    }
}