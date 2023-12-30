package com.ihr.ihr.common.excep;

import jakarta.xml.bind.ValidationException;

public class PayDayAlreadyExistsException extends Exception {
    public PayDayAlreadyExistsException(String message) {
        super(message);
    }
}