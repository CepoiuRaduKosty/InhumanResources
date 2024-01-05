package com.ihr.ihr.common.excep;

public class InvalidPaymentInfoException extends Exception {
    public InvalidPaymentInfoException(String errorMessage) {
        super(errorMessage);
    }
}
