package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.PaycheckDtos.CreatePaycheckDto;
import com.ihr.ihr.common.dtos.PaycheckDtos.PaycheckDto;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;

import java.util.List;

public interface PaycheckProvider {
    PaycheckDto getPaycheckById(Long paycheckId);

    void updatePaycheckById(Long paycheckId, CreatePaycheckDto updatePaycheckDTO) throws UnknownPaymentInfoException;

    void createPaycheck(CreatePaycheckDto createPaycheckDto);

    void deletePaycheckById(Long paycheckId);

    List<PaycheckDto> getAllPaychecks();

    List<PaycheckDto> getAllPaychecksByPaymentInfoId(Long paymentInfoId);

    List<PaycheckDto> getAllPaychecksByEmployeeId(Long employeeId);

    List<PaycheckDto> getAllPaychecksByMonth(Integer month);
}
