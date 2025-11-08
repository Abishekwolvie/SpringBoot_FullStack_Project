package com.abishek.ecommercewebsiteapiproject.products.exceptions;

import java.time.LocalDateTime;

public class ProductErrorResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    private LocalDateTime dateTime;

    public ProductErrorResponse(String message,LocalDateTime dateTime) {
        this.message = message;
        this.dateTime = dateTime;


    }
}
