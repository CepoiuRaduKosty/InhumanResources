package com.ihr.ihr.common.excep;

public class UnknownUserException extends Exception{
    public UnknownUserException() {
    }

    public UnknownUserException(String message) {
        super(message);
    }
}
