package com.springboot2.rest.api.constant;

public enum Status {
    success("Success"),
    failed("Failed");

    private String status;

    Status(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }


}
