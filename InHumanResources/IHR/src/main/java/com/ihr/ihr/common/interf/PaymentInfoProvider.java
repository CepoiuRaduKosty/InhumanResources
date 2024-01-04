package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.CreatePaymentInfoDto;
import com.ihr.ihr.common.dtos.PaymentInfoDto;
import com.ihr.ihr.common.excep.InvalidPaymentInfoException;
import com.ihr.ihr.common.excep.NonPositiveIncomeException;
import jakarta.ejb.Local;
import jakarta.xml.bind.ValidationException;

@SuppressWarnings("UnusedReturnValue")
@Local
public interface PaymentInfoProvider {
    PaymentInfoDto findPaymentInfoById(Long paymentInfoId);
    Long addPaymentInfo(CreatePaymentInfoDto createPaymentInfoDto) throws NonPositiveIncomeException, ValidationException, InvalidPaymentInfoException;
    void updatePaymentInfo(Long paymentInfoId, PaymentInfoDto paymentInfoDto) throws NonPositiveIncomeException, ValidationException, InvalidPaymentInfoException;
    void deletePaymentInfo(Long paymentInfoId);
    void incrementCumulatedSharesByNumberOfShares(Long paymentInfoId);
    void resetCumulatedShares(Long paymentInfoId);
}
