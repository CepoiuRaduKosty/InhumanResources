package com.ihr.ihr.common.excep;

import jakarta.xml.bind.ValidationException;

public class UnknownPaymentInfoException extends ValidationException {


    public UnknownPaymentInfoException(String message) {
        super(message);
    }
}
