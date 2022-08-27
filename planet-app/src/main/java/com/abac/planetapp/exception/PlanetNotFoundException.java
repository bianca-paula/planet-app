package com.abac.planetapp.exception;

public class PlanetNotFoundException extends RuntimeException {
    public PlanetNotFoundException(String message) {
        super(message);
    }
}
