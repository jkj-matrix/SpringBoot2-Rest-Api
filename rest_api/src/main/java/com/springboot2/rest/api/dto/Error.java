package com.springboot2.rest.api.dto;

public class Error {

    private String message;

    public Error(String message){
        this.message = message;
    }

    public Error(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
