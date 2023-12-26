package com.ihr.ihr.common.dtos;

public class BankInfoDto {
    private Long Id;
    private String IBAN;
    private String bankName;
    private Long employeeId;

    public BankInfoDto(Long id, String IBAN, String bankName, Long employeeId) {
        Id = id;
        this.IBAN = IBAN;
        this.bankName = bankName;
        this.employeeId = employeeId;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
