package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.CreatePaycheckDto;
import com.ihr.ihr.common.dtos.PaycheckDto;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;

import java.util.List;

public interface PaycheckProvider {
    PaycheckDto getPaycheckById(Long paycheckId);

    void updatePaycheckById(Long paycheckId, CreatePaycheckDto updatePaycheckDTO) throws UnknownPaymentInfoException;

    void createPaycheck(CreatePaycheckDto createPaycheckDto);

    void deletePaycheckById(Long paycheckId);

    List<PaycheckDto> getAllPaychecks();

    List<PaycheckDto> getAllPaychecksByPaymentInfoId(Long paymentInfoId);

    List<PaycheckDto> getAllPaychecksByMonth(Integer month);
}