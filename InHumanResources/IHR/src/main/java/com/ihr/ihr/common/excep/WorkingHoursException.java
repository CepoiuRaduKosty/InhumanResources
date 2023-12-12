package com.ihr.ihr.common.excep;

import jakarta.validation.ValidationException;

public class WorkingHoursException extends ValidationException {
    public WorkingHoursException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
