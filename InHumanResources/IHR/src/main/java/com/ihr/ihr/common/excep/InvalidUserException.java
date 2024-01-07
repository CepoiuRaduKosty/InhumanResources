package com.ihr.ihr.common.excep;

public class InvalidUserException extends Exception{
    public InvalidUserException() {
    }

    public InvalidUserException(String message) {
        super(message);
    }
}
