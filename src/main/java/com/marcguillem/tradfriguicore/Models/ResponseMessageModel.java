package com.marcguillem.tradfriguicore.Models;

public class ResponseMessageModel {

    private String message;

    public ResponseMessageModel(String message) {
        this.message = message;
    }

    public ResponseMessageModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
