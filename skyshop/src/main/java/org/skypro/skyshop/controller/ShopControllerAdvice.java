package org.skypro.skyshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.error.ShopError;

@ControllerAdvice

public class ShopControllerAdvice {

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError>handleNoSuchProductException(NoSuchProductException ex){
        ShopError shopError=new ShopError("PRODUCT_NOT_FOUND", ex.getMessage());
        return new ResponseEntity<>(shopError,HttpStatus.NOT_FOUND);
    }
}
