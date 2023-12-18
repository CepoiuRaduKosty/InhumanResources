package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BankInfoDto;

public interface IBankInfoValidator {
    boolean BankNameValidator(BankInfoDto bankInfoDto);
    public boolean IBANValidator(BankInfoDto bankInfoDto);
    public boolean ValidateBankInfo(BankInfoDto bankInfoDto);
}
