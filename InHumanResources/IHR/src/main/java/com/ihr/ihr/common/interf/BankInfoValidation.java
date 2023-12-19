package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BankInfoDto;

public interface BankInfoValidation {
    boolean isBankNameValid(String bankName);
    boolean isIBANValid(String IBAN);
    boolean isBankInfoDtoValid(BankInfoDto bankInfoDto);
}
