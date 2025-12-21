package com.abishek.ecommercewebsiteapiproject.cart.exceptions;

public class CartItemNotFoundException extends RuntimeException{
    public  CartItemNotFoundException(String message){
        super(message);
    }
}
