package com.abac.planetapp.exception;

public class CrewNotFoundException extends RuntimeException {
    public CrewNotFoundException(String message) {
        super(message);
    }
}
