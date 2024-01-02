package com.ihr.ihr.common.dtos.BankInfoDtos;

public class UpdateBankInfoDto {
    private String IBAN;
    private String bankName;

    public UpdateBankInfoDto(String IBAN, String bankName) {
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
}
