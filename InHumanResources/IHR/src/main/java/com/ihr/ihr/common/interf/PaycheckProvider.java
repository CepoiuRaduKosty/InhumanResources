package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.PaycheckDtos.CreatePaycheckDto;
import com.ihr.ihr.common.dtos.PaycheckDtos.PaycheckDto;
import com.ihr.ihr.common.excep.UnknownPaycheckException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;

import java.util.List;

public interface PaycheckProvider {
    PaycheckDto getPaycheckById(Long paycheckId) throws UnknownPaycheckException;

    void updatePaycheckById(Long paycheckId, CreatePaycheckDto updatePaycheckDTO) throws UnknownPaymentInfoException;

    void createPaycheck(CreatePaycheckDto createPaycheckDto) throws UnknownPaymentInfoException;

    void deletePaycheckById(Long paycheckId) throws UnknownPaycheckException;

    List<PaycheckDto> getAllPaychecks();

    List<PaycheckDto> getAllPaychecksByPaymentInfoId(Long paymentInfoId);

    List<PaycheckDto> getAllPaychecksByMonth(Integer month);

    EmployeeDto getEmployeeByPaycheck(Long paycheckId) throws UnknownPaycheckException;
}
