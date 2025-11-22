package com.abishek.ecommercewebsiteapiproject.users.exceptions;

public class ExistingUserException extends RuntimeException{

    public ExistingUserException(String message){
        super(message);
    }
}
