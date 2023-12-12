package com.bavon.app.rest.api;

import java.io.Serializable;

public class RestResponseWrapper implements Serializable {

    private boolean success;

    private String message;

    public RestResponseWrapper(){
        this.success = true;
        this.message = "OK";
    }

    public RestResponseWrapper(String message){
        this.success = true;
        this.message = message;
    }

    public RestResponseWrapper(boolean success, String message){
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
