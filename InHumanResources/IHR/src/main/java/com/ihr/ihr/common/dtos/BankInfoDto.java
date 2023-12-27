package com.ihr.ihr.common.dtos;

import com.ihr.ihr.entities.Employee;

public class BankInfoDto {
    private Long id;
    private String IBAN;
    private String bankName;
    private EmployeeDto employeeDto;

    public BankInfoDto(Long id, String IBAN, String bankName) {
        this.id = id;
        this.IBAN = IBAN;
        this.bankName = bankName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
