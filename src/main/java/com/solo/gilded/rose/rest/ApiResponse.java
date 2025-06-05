package com.solo.gilded.rose.rest;


import java.util.ArrayList;
import java.util.List;

public class ApiResponse<T extends ProductResult> {
    private boolean success;
    private T data;
    private String message;

    public ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseContainer generateResponse() {
        ResponseContainer responseContainer;
       if(success){
           List<ProductResult> productResults = new ArrayList<>();
           productResults.add(data);
           responseContainer = new ResponseContainer("success",productResults, message);
       }else {
           responseContainer = new ResponseContainer("fail", null, message);
       }

       return responseContainer;
    }
}