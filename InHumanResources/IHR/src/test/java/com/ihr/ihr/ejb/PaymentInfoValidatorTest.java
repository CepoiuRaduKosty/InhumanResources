package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        boolean isValid = paymentInfoValidatorBean.isSalaryLevelValid(null);
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

    @Test
    void isPaymentInfoDtoValid_negative_lecturer() {
        PaymentInfoDto invalidDto = new PaymentInfoDto(1L, 1000.0, SalaryLevelEnum.LECTURER, -1000.0, 1000, 0);
        assertFalse(paymentInfoValidatorBean.isPaymentInfoDtoValid(invalidDto));
    }

    @Test
    void isPaymentInfoDtoValid_negative_null() {
        assertFalse(paymentInfoValidatorBean.isPaymentInfoDtoValid(null));
    }

    @Test
    void isCumulatedSharesValid_positive() {
        assertTrue(paymentInfoValidatorBean.isCumulatedSharesValid(10));
    }

    @Test
    void isCumulatedSharesValid_negative() {
        assertFalse(paymentInfoValidatorBean.isCumulatedSharesValid(-110));
    }
}


