package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.PaymentInfoDtos.CreatePaymentInfoDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.excep.NonPositiveIncomeException;
import jakarta.ejb.Local;
import jakarta.xml.bind.ValidationException;

@Local
public interface PaymentInfoProvider {
    PaymentInfoDto findPaymentInfoById(Long paymentInfoId);
    Long addPaymentInfo(CreatePaymentInfoDto createPaymentInfoDto) throws NonPositiveIncomeException, ValidationException;
    void updatePaymentInfo(Long paymentInfoId, PaymentInfoDto paymentInfoDto) throws NonPositiveIncomeException, ValidationException;
    void deletePaymentInfo(Long paymentInfoId);
    void incrementCumulatedSharesByNumberOfShares(Long paymentInfoId);
    void resetCumulatedShares(Long paymentInfoId);
}
