package com.tsystems.javaschool.error;

public class BusinessLogicException extends RuntimeException {
    public BusinessLogicException(final String message) {
        super(message);
    }
    public BusinessLogicException() {
        super();
    }

}
