package com.ihr.ihr.common.dtos;

public class CreateBankInfoDto {
    private String IBAN;
    private String bankName;
    private EmployeeDto employeeDto;

    public CreateBankInfoDto(String IBAN, String bankName) {
        this.IBAN = IBAN;
        this.bankName = bankName;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }
}
