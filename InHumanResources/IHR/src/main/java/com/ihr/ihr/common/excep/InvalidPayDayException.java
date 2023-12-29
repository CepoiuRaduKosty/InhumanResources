package com.ihr.ihr.common.excep;

public class InvalidPayDayException extends Exception {
    public InvalidPayDayException(String errorMessage) {
        super(errorMessage);
    }
}
