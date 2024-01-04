package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;

public interface PaymentInfoValidation {
    boolean isMonthlyBasicSalaryValid(Double monthlyBasicSalary);

    boolean isSalaryLevelValid(SalaryLevelEnum salaryLevel);

    boolean isBonusForSuccessValid(Double bonusForSuccess);

    boolean isNumberOfSharesValid(int numberOfShares);

    boolean isPaymentInfoDtoValid(PaymentInfoDto paymentInfoDto);

    boolean isCumulatedSharesValid(Integer cumulatedShares);

}
