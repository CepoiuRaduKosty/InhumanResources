package com.ihr.ihr.common.excep;

import jakarta.xml.bind.ValidationException;

public class UnknownPaymentInfoException extends Exception {


    public UnknownPaymentInfoException(String message) {
        super(message);
    }
}
