package com.ihr.ihr.ejb;


import com.ihr.ihr.common.dtos.BankInfoDto;
import com.ihr.ihr.common.interf.BankInfoValidation;
import jakarta.ejb.Stateless;
import org.apache.commons.validator.routines.IBANValidator;

import java.util.logging.Logger;

@Stateless
public class BankInfoValidatorBean implements BankInfoValidation {

    private static final Logger LOG = Logger.getLogger(BankInfoValidatorBean.class.getName());

    public boolean BankNameValidator(BankInfoDto bankInfoDto) {
        LOG.info("BankNameValidator");

        return !bankInfoDto.getBankName().isEmpty();
    }

    public boolean IBANValidator(BankInfoDto bankInfoDto) {
        LOG.info("IBANValidator");

        if (bankInfoDto.getIBAN().isEmpty())
            return false;

        IBANValidator validator = IBANValidator.getInstance();

        return validator.isValid(bankInfoDto.getIBAN());
    }

    public boolean ValidateBankInfo(BankInfoDto bankInfoDto) {
        LOG.info("ValidateBankInfo");

        return IBANValidator(bankInfoDto) && BankNameValidator(bankInfoDto);
    }
}
