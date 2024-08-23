package com.ubots.domain.exception;

public abstract class BusinessServiceException extends RuntimeException {

    public BusinessServiceException(String message) {
        super(message);
    }
}
