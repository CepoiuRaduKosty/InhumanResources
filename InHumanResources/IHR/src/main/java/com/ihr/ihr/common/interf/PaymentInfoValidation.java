package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;

public interface PaymentInfoValidation {
    boolean isMonthlyBasicSalaryValid(int monthlyBasicSalary);

    boolean isSalaryLevelValid(SalaryLevelEnum salaryLevel);

    boolean isBonusForSuccessValid(int bonusForSuccess);

    boolean isNumberOfSharesValid(int numberOfShares);

    boolean isPaymentInfoDtoValid(PaymentInfoDto paymentInfoDto);

    boolean isCumulatedSharesValid(Integer cumulatedShares);

}
