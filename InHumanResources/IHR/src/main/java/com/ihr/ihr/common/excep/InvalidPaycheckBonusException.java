package com.ihr.ihr.common.excep;

public class InvalidPaycheckBonusException extends Exception{
    public InvalidPaycheckBonusException() {
    }

    public InvalidPaycheckBonusException(String message) {
        super(message);
    }
}
