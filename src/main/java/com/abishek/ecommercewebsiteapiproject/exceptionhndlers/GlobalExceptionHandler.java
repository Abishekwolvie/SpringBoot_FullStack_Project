package com.abishek.ecommercewebsiteapiproject.exceptionhndlers;

import com.abishek.ecommercewebsiteapiproject.products.exceptions.ProductErrorResponse;
import com.abishek.ecommercewebsiteapiproject.products.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleproductnotfoundexception(ProductNotFoundException exception){

        ProductErrorResponse productErrorResponse = new ProductErrorResponse(exception.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(productErrorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleinternalservererror(Exception exception){

        ProductErrorResponse productErrorResponse = new ProductErrorResponse("Server Error", LocalDateTime.now());

        return new ResponseEntity<>(productErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
