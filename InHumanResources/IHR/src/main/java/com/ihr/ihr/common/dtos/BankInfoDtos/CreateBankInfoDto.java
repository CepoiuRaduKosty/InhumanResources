package com.ihr.ihr.common.dtos.BankInfoDtos;

import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;

public class CreateBankInfoDto extends UpdateBankInfoDto{
    private EmployeeDto employeeDto;

    public CreateBankInfoDto(String IBAN, String bankName) {
        super(IBAN, bankName);
    }

    public CreateBankInfoDto(String IBAN, String bankName, EmployeeDto employeeDto) {
        super(IBAN, bankName);
        this.employeeDto = employeeDto;
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }
}
