package com.ihr.ihr.common.dtos;

public class BankInfoDto {
    public int id;
    public String IBAN;
    public String bankName;

    public BankInfoDto(int id, String IBAN, String bankName) {
        this.id = id;
        this.IBAN = IBAN;
        this.bankName = bankName;
    }
}
