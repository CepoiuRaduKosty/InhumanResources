package com.ihr.ihr.common.excep;

import jakarta.xml.bind.ValidationException;

public class UnknownPaycheckException extends ValidationException {

    public UnknownPaycheckException(String message) {
        super(message);
    }
}
