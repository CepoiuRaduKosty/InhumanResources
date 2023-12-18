package com.ihr.ihr.ejb;


import com.ihr.ihr.common.dtos.BankInfoDto;
import com.ihr.ihr.common.interf.IBankInfoValidator;
import org.apache.commons.validator.routines.IBANValidator;

import java.util.logging.Logger;

public class BankInfoValidator implements IBankInfoValidator {

    private static final Logger LOG = Logger.getLogger(BankInfoValidator.class.getName());

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
