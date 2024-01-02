package com.ihr.ihr.common.excep;

import jakarta.xml.bind.ValidationException;

public class UnknownBankInfoException extends Exception {
    public UnknownBankInfoException(String message) {
        super(message);
    }
}
