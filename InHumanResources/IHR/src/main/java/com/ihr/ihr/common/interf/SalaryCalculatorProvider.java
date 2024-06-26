package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BonusDtos.UpdateBonusDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.UpdateEmployeeDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;

import java.util.List;

public interface SalaryCalculatorProvider {

    Double TaxRate(SalaryLevelEnum salaryLevelEnum);

    Double getSalaryBeforeTaxes(UpdateEmployeeDto updateEmployeeDto, PaymentInfoDto paymentInfoDto, List<UpdateBonusDto> updateBonusDtoList);

    Double getSalaryAfterTaxes(UpdateEmployeeDto updateEmployeeDto, PaymentInfoDto paymentInfoDto, List<UpdateBonusDto> updateBonusDtoList);
}
