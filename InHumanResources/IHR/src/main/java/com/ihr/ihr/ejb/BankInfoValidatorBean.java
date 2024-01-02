package com.ihr.ihr.ejb;


import com.ihr.ihr.common.dtos.BankInfoDtos.BankInfoDto;
import com.ihr.ihr.common.dtos.BankInfoDtos.UpdateBankInfoDto;
import com.ihr.ihr.common.interf.BankInfoValidation;
import jakarta.ejb.Stateless;
import org.apache.commons.validator.routines.IBANValidator;

import java.util.logging.Logger;

@Stateless
public class BankInfoValidatorBean implements BankInfoValidation {
    private static final Logger LOG = Logger.getLogger(BankInfoValidatorBean.class.getName());

    public boolean isBankNameValid(String name) {
        LOG.info("BankNameValidator");

        return !name.isEmpty();
    }

    public boolean isIBANValid(String IBAN) {
        LOG.info("IBANValidator");

        if (IBAN.isEmpty())
            return false;

        IBANValidator validator = IBANValidator.getInstance();

        return validator.isValid(IBAN);
    }

    public boolean isBankInfoDtoValid(UpdateBankInfoDto updateBankInfoDto) {
        LOG.info("ValidateBankInfo");

        return isIBANValid(updateBankInfoDto.getIBAN()) && isBankNameValid(updateBankInfoDto.getBankName());
    }
}
