package com.ihr.ihr.common.excep;

import jakarta.validation.ValidationException;

public class DateOfBirthException extends Exception {
    public DateOfBirthException(String errorMessage) {
        super(errorMessage);
    }
}
