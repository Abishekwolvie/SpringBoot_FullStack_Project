package com.abishek.ecommercewebsiteapiproject.exceptionhndlers;

import com.abishek.ecommercewebsiteapiproject.cart.exceptions.CartItemNotFoundException;
import com.abishek.ecommercewebsiteapiproject.products.exceptions.ProductErrorResponse;
import com.abishek.ecommercewebsiteapiproject.products.exceptions.ProductNotFoundException;
import com.abishek.ecommercewebsiteapiproject.users.exceptions.ExistingUserException;
import com.abishek.ecommercewebsiteapiproject.users.exceptions.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlemethodargumentnotvalidexception(MethodArgumentNotValidException exception){

        Map<String,String> errormap = new HashMap<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach((error)->{
            errormap.put(error.getField(),error.getDefaultMessage());
        });

        return new ResponseEntity<>(errormap,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<?> handlecartitemnotfoundexception(CartItemNotFoundException exception){

        ProductErrorResponse productErrorResponse = new ProductErrorResponse(exception.getMessage(), LocalDateTime.now());

        return new ResponseEntity<>(productErrorResponse, HttpStatus.NOT_FOUND);


    }
}
