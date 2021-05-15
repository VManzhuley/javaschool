package com.tsystems.javaschool.error;

public class WrongParameterException extends RuntimeException{
    public WrongParameterException(final String message) {
        super(message);
    }
    public WrongParameterException() {
        super();
    }

}
