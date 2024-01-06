package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.interf.PaymentInfoValidation;
import jakarta.ejb.Stateless;

import java.util.logging.Logger;

import static com.ihr.ihr.common.enums.SalaryLevelEnum.*;

@Stateless
public class PaymentInfoValidatorBean implements PaymentInfoValidation {
    private static final Logger LOG = Logger.getLogger(PaymentInfoValidatorBean.class.getName());

    @Override
    public boolean isMonthlyBasicSalaryValid(Double monthlyBasicSalary) {
        LOG.info("isMonthlyBasicSalaryValid");
        return monthlyBasicSalary > 0;
    }

    @Override
    public boolean isSalaryLevelValid(SalaryLevelEnum salaryLevel) {
        LOG.info("salaryLevelNotNull");
        return salaryLevel != null;
    }

    @Override
    public boolean isBonusForSuccessValid(Double bonusForSuccess) {
        LOG.info("bonusForSuccessZeroOrAbove");
        return bonusForSuccess >= 0;
    }

    @Override
    public boolean isNumberOfSharesValid(int numberOfShares) {
        LOG.info("numberOfSharesZeroOrAbove");
        return numberOfShares >= 0;
    }

    private boolean salaryLevelIsAssociateOrExecutive(SalaryLevelEnum salaryLevelEnum, Double bonusForSuccess) {
        if (salaryLevelEnum.equals(ASSOCIATE) || salaryLevelEnum.equals(EXECUTIVE)) {
            return bonusForSuccess > 0;
        } else return bonusForSuccess == 0;
    }

    private boolean salaryLevelIsExecutive(SalaryLevelEnum salaryLevelEnum, Integer numberOfShares) {
        if (salaryLevelEnum.equals(EXECUTIVE)) {
            return numberOfShares > 0;
        } else return numberOfShares == 0;
    }

    @Override
    public boolean isPaymentInfoDtoValid(PaymentInfoDto paymentInfoDto) {
        LOG.info("PaymentInfoDtoRules");
        if(paymentInfoDto == null)
            return false;

        if(!isCumulatedSharesValid(paymentInfoDto.getCumulatedShares()))
            return false;

        boolean isMonthlyBasicSalaryValid = isMonthlyBasicSalaryValid(paymentInfoDto.getMonthlyBasicSalary());

        boolean isSalaryLevelValid = isSalaryLevelValid(paymentInfoDto.getSalaryLevel());

        boolean isBonusForSuccessValid = isBonusForSuccessValid(paymentInfoDto.getBonusForSuccess());

        boolean isNumberOfSharesValid = isNumberOfSharesValid(paymentInfoDto.getNumberOfShares());

        boolean salaryLevelIsAssociateOrExecutive = salaryLevelIsAssociateOrExecutive(paymentInfoDto.getSalaryLevel(),
                paymentInfoDto.getBonusForSuccess());

        boolean salaryLevelIsExecutive = salaryLevelIsExecutive(paymentInfoDto.getSalaryLevel(),
                paymentInfoDto.getNumberOfShares());

        boolean isNumberOfSharesZeroForLecturerAssociate = isNumberOfSharesZeroForLecturerAssociate(paymentInfoDto.getSalaryLevel(), paymentInfoDto.getNumberOfShares());

        return isMonthlyBasicSalaryValid && isSalaryLevelValid && isBonusForSuccessValid &&
                isNumberOfSharesValid && salaryLevelIsAssociateOrExecutive && salaryLevelIsExecutive && isNumberOfSharesZeroForLecturerAssociate;
    }

    @Override
    public boolean isCumulatedSharesValid(Integer cumulatedShares) {
        return cumulatedShares >= 0;
    }

    private boolean isNumberOfSharesZeroForLecturerAssociate(SalaryLevelEnum salaryLevelEnum, Integer numberOfShares) {
        return (salaryLevelEnum.equals(LECTURER) && numberOfShares == 0) ||
                (salaryLevelEnum.equals(ASSOCIATE) && numberOfShares == 0) ||
                (salaryLevelEnum.equals(PROFESSOR) && numberOfShares > 0) ||
                (salaryLevelEnum.equals(EXECUTIVE) && numberOfShares > 0);
    }
}
