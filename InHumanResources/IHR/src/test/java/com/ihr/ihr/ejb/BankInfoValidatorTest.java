package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BankInfoDto;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import com.ihr.ihr.common.interf.BankInfoValidation;


import static org.junit.jupiter.api.Assertions.*;
class BankInfoValidatorTest {
    private final BankInfoValidation tempBankInfoValidation=new BankInfoValidatorBean();
    @Inject
    BankInfoValidation bankInfoValidation;
    @Test
    void isBankNameValid_positive() {
        String name = "george";
        boolean isValid = tempBankInfoValidation.isBankNameValid(name);
        assertTrue(isValid);
    }

    @Test
    void isBankNameValid_negative() {
        String name = "";
        boolean isValid = tempBankInfoValidation.isBankNameValid(name);
        assertFalse(isValid);
    }

    @Test
    void isIBANValid_positive() {
        String IBAN ="RO49AAAA1B31007593840000";
        boolean isValid = tempBankInfoValidation.isIBANValid(IBAN);
        assertTrue(isValid);

    }
    @Test
    void isIBANValid_negative() {
        String IBAN ="55";
        boolean isValid = tempBankInfoValidation.isIBANValid(IBAN);
        assertFalse(isValid);

    }
    @Test
    void isBankInfoDtoValid_positive() {
        BankInfoDto validDto = new BankInfoDto(1L, "RO49AAAA1B31007593840000", "ValidBankName");
        assertTrue(tempBankInfoValidation.isBankInfoDtoValid(validDto));
    }
    @Test
    void isBankInfoDtoValid_negative() {
        BankInfoDto invalidDto = new BankInfoDto(1L, "RA1B31007593840000", "");
        assertFalse(tempBankInfoValidation.isBankInfoDtoValid(invalidDto));
    }
}