package com.ihr.ihr.common.excep;

import jakarta.validation.ValidationException;

public class WorkingHoursException extends Exception {
    public WorkingHoursException(String errorMessage) {
        super(errorMessage);
    }
}
