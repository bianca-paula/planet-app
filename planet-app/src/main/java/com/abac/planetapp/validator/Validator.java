package com.abac.planetapp.validator;

public interface Validator<E> {
    void validate(E entity) throws ValidationException;
}
