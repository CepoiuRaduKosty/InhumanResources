package com.ihr.ihr.common.dtos;

public class BankInfoDto {
    private int id;
    private String IBAN;
    private String bankName;

    public BankInfoDto(int id, String IBAN, String bankName) {
        this.id = id;
        this.IBAN = IBAN;
        this.bankName = bankName;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIBAN() {
        return this.IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getBankName() {
        return this.bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}