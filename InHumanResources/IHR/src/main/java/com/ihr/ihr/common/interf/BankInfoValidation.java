package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BankInfoDto;

public interface BankInfoValidation {
    boolean BankNameValidator(String bankName);
    public boolean IBANValidator(String IBAN);
    public boolean ValidateBankInfo(BankInfoDto bankInfoDto);
}
