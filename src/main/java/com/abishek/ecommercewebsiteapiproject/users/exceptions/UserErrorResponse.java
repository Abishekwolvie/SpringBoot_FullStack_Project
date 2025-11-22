package com.abishek.ecommercewebsiteapiproject.users.exceptions;

import java.time.LocalDateTime;

public class UserErrorResponse {

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

    public UserErrorResponse(String message,LocalDateTime dateTime) {
        this.message = message;
        this.dateTime = dateTime;


    }
}
