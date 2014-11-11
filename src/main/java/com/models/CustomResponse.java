package com.models;

public class CustomResponse {

    public CustomResponse() {
    }

    public CustomResponse(String message) {
        this.message = message;
    }

    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
