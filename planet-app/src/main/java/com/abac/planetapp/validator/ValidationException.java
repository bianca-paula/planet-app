package com.abac.planetapp.validator;

public class ValidationException extends RuntimeException{
    public ValidationException(String exception) {
        super(exception);
    }
}