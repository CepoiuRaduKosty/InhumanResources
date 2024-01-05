package com.ihr.ihr.common.excep;

public class InvalidBonusException extends Exception{
    public InvalidBonusException() {
    }

    public InvalidBonusException(String message) {
        super(message);
    }
}
