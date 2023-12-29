package com.ihr.ihr.common.excep;

import jakarta.validation.ValidationException;

public class NonPositiveIncomeException extends Exception {

    public NonPositiveIncomeException(String errorMessage) {
        super(errorMessage);
    }
}
