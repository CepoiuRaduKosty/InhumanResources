package com.ihr.ihr.common.excep;

public class PayDayDoesNotExistException extends Exception {
    public PayDayDoesNotExistException(String message) {
        super(message);
    }
}