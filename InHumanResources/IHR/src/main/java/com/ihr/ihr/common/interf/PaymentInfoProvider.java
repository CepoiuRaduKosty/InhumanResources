package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.PaymentInfoDtos.CreatePaymentInfoDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.excep.InvalidPaymentInfoException;
import com.ihr.ihr.common.excep.NonPositiveIncomeException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;
import jakarta.ejb.Local;
import jakarta.xml.bind.ValidationException;

@SuppressWarnings("UnusedReturnValue")
@Local
public interface PaymentInfoProvider {
    PaymentInfoDto findPaymentInfoById(Long paymentInfoId);
    Long addPaymentInfo(CreatePaymentInfoDto createPaymentInfoDto) throws NonPositiveIncomeException, ValidationException, InvalidPaymentInfoException;
    void updatePaymentInfo(Long paymentInfoId, PaymentInfoDto paymentInfoDto) throws NonPositiveIncomeException, ValidationException, InvalidPaymentInfoException, UnknownPaymentInfoException;
    void deletePaymentInfo(Long paymentInfoId) throws UnknownPaymentInfoException;
    void incrementCumulatedSharesByNumberOfShares(Long paymentInfoId) throws UnknownPaymentInfoException;
    void resetCumulatedShares(Long paymentInfoId) throws UnknownPaymentInfoException;
}
