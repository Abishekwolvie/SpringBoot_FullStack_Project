package com.abishek.ecommercewebsiteapiproject.orders.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message) {
        super(message);
    }
}
