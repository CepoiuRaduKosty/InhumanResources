package com.ihr.ihr.ejb;

import com.ihr.ihr.common.interf.BankInfoValidation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BankInfoValidatorBeanTest {

    @Inject
    BankInfoValidation bankInfoValidation;
    @Test
    void isBankNameValid_positive() {
        String name = "george";
        boolean isValid = bankInfoValidation.isBankNameValid(name);

        assertTrue(isValid);
    }

    @Test
    void isBankNameValid_negative() {
        String name = "";
        boolean isValid = bankInfoValidation.isBankNameValid(name);

        assertFalse(isValid);
    }

    @Test
    void isIBANValid() {
    }

    @Test
    void isBankInfoDtoValid() {
    }
}