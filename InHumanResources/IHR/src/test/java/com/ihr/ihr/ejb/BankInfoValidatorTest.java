package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BankInfoDtos.BankInfoDto;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BankInfoValidatorTest {
    @InjectMocks
    BankInfoValidatorBean bankInfoValidatorBean;
    @Test
    void isBankNameValid_positive() {
        String name = "george";
        boolean isValid = bankInfoValidatorBean.isBankNameValid(name);
        assertTrue(isValid);
    }

    @Test
    void isBankNameValid_negative() {
        String name = "";
        boolean isValid = bankInfoValidatorBean.isBankNameValid(name);
        assertFalse(isValid);
    }

    @Test
    void isIBANValid_positive() {
        String IBAN ="RO49AAAA1B31007593840000";
        boolean isValid = bankInfoValidatorBean.isIBANValid(IBAN);
        assertTrue(isValid);

    }
    @Test
    void isIBANValid_negative() {
        String IBAN ="55";
        boolean isValid = bankInfoValidatorBean.isIBANValid(IBAN);
        assertFalse(isValid);

    }
    @Test
    void isBankInfoDtoValid_positive() {
        BankInfoDto validDto = new BankInfoDto(1L, "RO49AAAA1B31007593840000", "ValidBankName");
        assertTrue(bankInfoValidatorBean.isBankInfoDtoValid(validDto));
    }
    @Test
    void isBankInfoDtoValid_negative() {
        BankInfoDto invalidDto = new BankInfoDto(1L, "RA1B31007593840000", "");
        assertFalse(bankInfoValidatorBean.isBankInfoDtoValid(invalidDto));
    }
}