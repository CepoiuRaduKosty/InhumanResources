package com.ihr.ihr.common.dtos.BankInfoDtos;

import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;

public class BankInfoDto extends CreateBankInfoDto{
    private Long id;

    public BankInfoDto(Long id, String IBAN, String bankName, EmployeeDto employeeDto) {
        super(IBAN, bankName, employeeDto);
        this.id = id;
    }

    public BankInfoDto(Long id, String IBAN, String bankName) {
        super(IBAN, bankName);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
