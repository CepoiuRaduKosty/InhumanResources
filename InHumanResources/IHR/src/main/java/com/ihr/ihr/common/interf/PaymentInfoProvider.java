package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.PaymentInfoDto;
import com.ihr.ihr.common.excep.NonPositiveIncomeException;

public interface PaymentInfoProvider {
    PaymentInfoDto get(int id_paymentinfo);   // returns PaymentInfoDto of payment info with given id or null if none found
    void add(PaymentInfoDto paymentinfoDto) throws NonPositiveIncomeException; // tries to create new payment info based on PaymentInfoDto ; automatically assigns id (id given in dto is ignored)
    // throws NonPositiveIncomeException if monthlyBasicSalary or bonusForSuccess or numberOfShares <= 0
    void set(int id_paymentinfo, PaymentInfoDto paymentInfoDto) throws NonPositiveIncomeException; // tries to (fully) replace payment info data at id_paymentinfo with data given in DTO
    // throws NonPositiveIncomeException if monthlyBasicSalary or bonusForSuccess or numberOfShares <= 0
    void remove(int id_paymentinfo); // tries to remove payment info with given id

}
