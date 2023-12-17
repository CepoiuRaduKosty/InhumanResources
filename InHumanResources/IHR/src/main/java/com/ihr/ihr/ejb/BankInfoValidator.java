package com.ihr.ihr.ejb;


import com.ihr.ihr.common.dtos.BankInfoDto;
import org.apache.commons.validator.routines.IBANValidator;

public class BankInfoValidator {

    private boolean BankNameValidator(BankInfoDto bankInfoDto) {
        return !bankInfoDto.getBankName().isEmpty();
    }

    private boolean IBANValidator(BankInfoDto bankInfoDto) {

        if (bankInfoDto.getIBAN().isEmpty())
            return false;

        IBANValidator validator = IBANValidator.getInstance();

        return validator.isValid(bankInfoDto.getIBAN());
    }

    private boolean ValidateBankInfo(BankInfoDto bankInfoDto) {
        return IBANValidator(bankInfoDto) && BankNameValidator(bankInfoDto);
    }
}
