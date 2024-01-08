package com.ihr.ihr.common.excep;

public class UnknownEmployeeException extends Exception {
    public UnknownEmployeeException(String message) {
        super(message);
    }

    public UnknownEmployeeException() {
        super();
    }
}
