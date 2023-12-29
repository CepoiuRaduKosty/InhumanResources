package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.CreatePaymentInfoDto;
import com.ihr.ihr.common.dtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.interf.PaymentInfoProvider;
import com.ihr.ihr.common.interf.PaymentInfoValidation;
import com.ihr.ihr.entities.PaymentInfo;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.logging.Logger;

import static com.ihr.ihr.common.enums.SalaryLevelEnum.*;

@Stateless
public class PaymentInfoValidatorBean implements PaymentInfoValidation {
    private static final Logger LOG = Logger.getLogger(PaymentInfoValidatorBean.class.getName());

    @Override
    public boolean isMonthlyBasicSalaryValid(int monthlyBasicSalary) {
        LOG.info("isMonthlyBasicSalaryValid");
        return monthlyBasicSalary > 0;
    }

    @Override
    public boolean isSalaryLevelValid(SalaryLevelEnum salaryLevel) {
        LOG.info("salaryLevelNotNull");
        return salaryLevel != null;
    }

    @Override
    public boolean isBonusForSuccessValid(int bonusForSuccess) {
        LOG.info("bonusForSuccessZeroOrAbove");
        return bonusForSuccess >= 0;
    }

    @Override
    public boolean isNumberOfSharesValid(int numberOfShares) {
        LOG.info("numberOfSharesZeroOrAbove");
        return numberOfShares >= 0;
    }

    private boolean salaryLevelIsAssociateOrExecutive(SalaryLevelEnum salaryLevelEnum, Integer bonusForSuccess) {
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

        return isMonthlyBasicSalaryValid(paymentInfoDto.getMonthlyBasicSalary()) &&
                isSalaryLevelValid(paymentInfoDto.getSalaryLevel()) &&
                isBonusForSuccessValid(paymentInfoDto.getBonusForSuccess()) &&
                isNumberOfSharesValid(paymentInfoDto.getNumberOfShares()) &&
                salaryLevelIsAssociateOrExecutive(paymentInfoDto.getSalaryLevel(), paymentInfoDto.getBonusForSuccess()) &&
                salaryLevelIsExecutive(paymentInfoDto.getSalaryLevel(), paymentInfoDto.getNumberOfShares()) &&
                isCumulatedSharesValid(paymentInfoDto.getCumulatedShares()) &&
                isNumberOfSharesZeroForLecturerAssociate(paymentInfoDto.getSalaryLevel(), paymentInfoDto.getNumberOfShares());
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
