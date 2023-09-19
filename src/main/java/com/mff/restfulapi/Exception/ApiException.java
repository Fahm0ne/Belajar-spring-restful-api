package com.mff.restfulapi.Exception;

public class ApiException extends RuntimeException{

    public ApiException(String message) {
        super(message);
    }
}
