package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BankInfoDtos.UpdateBankInfoDto;

public interface BankInfoValidation {
    boolean isBankNameValid(String bankName);
    boolean isIBANValid(String IBAN);
    boolean isBankInfoDtoValid(UpdateBankInfoDto updateBankInfoDto);
}
