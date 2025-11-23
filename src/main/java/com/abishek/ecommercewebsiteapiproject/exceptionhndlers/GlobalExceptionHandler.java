package com.abishek.ecommercewebsiteapiproject.exceptionhndlers;

import com.abishek.ecommercewebsiteapiproject.products.exceptions.ProductErrorResponse;
import com.abishek.ecommercewebsiteapiproject.products.exceptions.ProductNotFoundException;
import com.abishek.ecommercewebsiteapiproject.users.exceptions.ExistingUserException;
import com.abishek.ecommercewebsiteapiproject.users.exceptions.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

        exception.printStackTrace();

        return new ResponseEntity<>(productErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleinvalidcredentials(BadCredentialsException exception){

        ProductErrorResponse productErrorResponse = new ProductErrorResponse("Invalid Credentials", LocalDateTime.now());

        exception.printStackTrace();

        return new ResponseEntity<>(productErrorResponse, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(ExistingUserException.class)
    public ResponseEntity<?> handleuseralreadyexistexception(ExistingUserException exception){

        UserErrorResponse userErrorResponse = new UserErrorResponse(exception.getMessage(),LocalDateTime.now());

        return new ResponseEntity<>(userErrorResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleusernamenotfoundexception(UsernameNotFoundException exception){

        UserErrorResponse userErrorResponse = new UserErrorResponse(exception.getMessage(),LocalDateTime.now());

        return new ResponseEntity<>(userErrorResponse,HttpStatus.NOT_FOUND);
    }
}
