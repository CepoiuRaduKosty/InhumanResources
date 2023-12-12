package com.ihr.ihr.common.excep;

import jakarta.validation.ValidationException;

public class NonPositiveIncomeException extends ValidationException {

    public NonPositiveIncomeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
