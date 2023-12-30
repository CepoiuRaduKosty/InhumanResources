package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.interf.PaymentInfoValidation;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentInfoValidatorTest {
    private final PaymentInfoValidation tempPaymentInfoValidation = new PaymentInfoValidatorBean();
    @Inject
    PaymentInfoValidation paymentInfoValidation;

    @Test
    void isMonthlyBasicSalaryValid_positive() {
        int monthlyBasicSalary = 1000;
        boolean isValid = tempPaymentInfoValidation.isMonthlyBasicSalaryValid(monthlyBasicSalary);
        assertTrue(isValid);
    }

    @Test
    void isMonthlyBasicSalaryValid_negative() {
        int monthlyBasicSalary = 0;
        boolean isValid = tempPaymentInfoValidation.isMonthlyBasicSalaryValid(monthlyBasicSalary);
        assertFalse(isValid);
    }

    @Test
    void isSalaryLevelValid_positive() {
        SalaryLevelEnum person = SalaryLevelEnum.LECTURER;
        boolean isValid = tempPaymentInfoValidation.isSalaryLevelValid(person);
        assertTrue(isValid);
    }

    @Test
    void isSalaryLevelValid_negative() {
        SalaryLevelEnum person = null;
        boolean isValid = tempPaymentInfoValidation.isSalaryLevelValid(person);
        assertFalse(isValid);
    }

    @Test
    void isBonusForSuccessValid_positive() {
        int bonusForSuccess = 1000;
        boolean isValid = tempPaymentInfoValidation.isBonusForSuccessValid(bonusForSuccess);
        assertTrue(isValid);
    }

    @Test
    void isBonusForSuccessValid_negative() {
        int bonusForSuccess = -1000;
        boolean isValid = tempPaymentInfoValidation.isBonusForSuccessValid(bonusForSuccess);
        assertFalse(isValid);
    }

    @Test
    void isNumberOfSharesValid_positive() {
        int numberOfShares = 1000;
        boolean isValid = tempPaymentInfoValidation.isNumberOfSharesValid(numberOfShares);
        assertTrue(isValid);
    }

    @Test
    void isNumberOfSharesValid_negative() {
        int numberOfShares = -1000;
        boolean isValid = tempPaymentInfoValidation.isNumberOfSharesValid(numberOfShares);
        assertFalse(isValid);
    }

    @Test
    void isPaymentInfoDtoValid_positive() {
        PaymentInfoDto validDto = new PaymentInfoDto(1L, 1000, SalaryLevelEnum.EXECUTIVE, 1000, 1000);
        assertTrue(tempPaymentInfoValidation.isPaymentInfoDtoValid(validDto));
    }

    @Test
    void isPaymentInfoDtoValid_negative() {
        PaymentInfoDto invalidDto = new PaymentInfoDto(1L, 1000, SalaryLevelEnum.EXECUTIVE, -1000, 1000);
        assertFalse(tempPaymentInfoValidation.isPaymentInfoDtoValid(invalidDto));
    }
}


