package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.CreatePaymentInfoDto;
import com.ihr.ihr.common.dtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.excep.NonPositiveIncomeException;

public interface PaymentInfoValidator {
    boolean monthlyBasicSalaryAboveZero(int monthlyBasicSalary);

    boolean salaryLevelNotNull(SalaryLevelEnum salaryLevel);

    boolean bonusForSuccessZeroOrAbove(int bonusForSuccess);

    boolean numberOfSharesZeroOrAbove(int numberOfShares);

    boolean PaymentInfoDtoRules(PaymentInfoDto paymentInfoDto);

}
