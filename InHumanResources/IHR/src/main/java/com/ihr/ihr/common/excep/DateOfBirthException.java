package com.ihr.ihr.common.excep;

import jakarta.validation.ValidationException;

public class DateOfBirthException extends ValidationException {
    public DateOfBirthException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
