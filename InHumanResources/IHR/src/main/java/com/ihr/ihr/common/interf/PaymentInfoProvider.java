package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.CreatePaymentInfoDto;
import com.ihr.ihr.common.dtos.PaymentInfoDto;
import com.ihr.ihr.common.excep.NonPositiveIncomeException;

public interface PaymentInfoProvider {
    PaymentInfoDto findPaymentInfoById(Long paymentInfoId);   // returns PaymentInfoDto of payment info with given id or null if none found
    void addPaymentInfo(CreatePaymentInfoDto createPaymentInfoDto) throws NonPositiveIncomeException; // tries to create new payment info based on PaymentInfoDto ; automatically assigns id (id given in dto is ignored)
    // throws NonPositiveIncomeException if monthlyBasicSalary or bonusForSuccess or numberOfShares <= 0
    void updatePaymentInfo(Long paymentInfoId, PaymentInfoDto paymentInfoDto) throws NonPositiveIncomeException; // tries to (fully) replace payment info data at id_paymentinfo with data given in DTO
    // throws NonPositiveIncomeException if monthlyBasicSalary or bonusForSuccess or numberOfShares <= 0
    void deletePaymentInfo(Long paymentInfoId); // tries to remove payment info with given id

}
