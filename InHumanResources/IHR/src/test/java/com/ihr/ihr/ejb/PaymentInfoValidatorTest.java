package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.interf.PaymentInfoValidation;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PaymentInfoValidatorTest {
    @InjectMocks
    PaymentInfoValidatorBean paymentInfoValidatorBean;

    @Test
    void isMonthlyBasicSalaryValid_positive() {
        Double monthlyBasicSalary = 1000.0;
        boolean isValid = paymentInfoValidatorBean.isMonthlyBasicSalaryValid(monthlyBasicSalary);
        assertTrue(isValid);
    }

    @Test
    void isMonthlyBasicSalaryValid_negative() {
        Double monthlyBasicSalary = 0.0;
        boolean isValid = paymentInfoValidatorBean.isMonthlyBasicSalaryValid(monthlyBasicSalary);
        assertFalse(isValid);
    }

    @Test
    void isSalaryLevelValid_positive() {
        SalaryLevelEnum person = SalaryLevelEnum.LECTURER;
        boolean isValid = paymentInfoValidatorBean.isSalaryLevelValid(person);
        assertTrue(isValid);
    }

    @Test
    void isSalaryLevelValid_negative() {
        SalaryLevelEnum person = null;
        boolean isValid = paymentInfoValidatorBean.isSalaryLevelValid(person);
        assertFalse(isValid);
    }

    @Test
    void isBonusForSuccessValid_positive() {
        Double bonusForSuccess = 1000.0;
        boolean isValid = paymentInfoValidatorBean.isBonusForSuccessValid(bonusForSuccess);
        assertTrue(isValid);
    }

    @Test
    void isBonusForSuccessValid_negative() {
        Double bonusForSuccess = -1000.0;
        boolean isValid = paymentInfoValidatorBean.isBonusForSuccessValid(bonusForSuccess);
        assertFalse(isValid);
    }

    @Test
    void isNumberOfSharesValid_positive() {
        int numberOfShares = 1000;
        boolean isValid = paymentInfoValidatorBean.isNumberOfSharesValid(numberOfShares);
        assertTrue(isValid);
    }

    @Test
    void isNumberOfSharesValid_negative() {
        int numberOfShares = -1000;
        boolean isValid = paymentInfoValidatorBean.isNumberOfSharesValid(numberOfShares);
        assertFalse(isValid);
    }

    @Test
    void isPaymentInfoDtoValid_positive() {
        PaymentInfoDto validDto = new PaymentInfoDto(1L, 1000.0, SalaryLevelEnum.EXECUTIVE, 1000.0, 1000,0);
        assertTrue(paymentInfoValidatorBean.isPaymentInfoDtoValid(validDto));
    }

    @Test
    void isPaymentInfoDtoValid_negative() {
        PaymentInfoDto invalidDto = new PaymentInfoDto(1L, 1000.0, SalaryLevelEnum.EXECUTIVE, -1000.0, 1000, 0);
        assertFalse(paymentInfoValidatorBean.isPaymentInfoDtoValid(invalidDto));
    }
}


