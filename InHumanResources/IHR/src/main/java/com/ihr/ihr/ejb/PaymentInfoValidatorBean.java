package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.CreatePaymentInfoDto;
import com.ihr.ihr.common.dtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.interf.PaymentInfoProvider;
import com.ihr.ihr.common.interf.PaymentInfoValidator;
import com.ihr.ihr.entities.PaymentInfo;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.logging.Logger;

import static com.ihr.ihr.common.enums.SalaryLevelEnum.ASSOCIATE;
import static com.ihr.ihr.common.enums.SalaryLevelEnum.EXECUTIVE;

@Stateless
public class PaymentInfoValidatorBean implements PaymentInfoValidator {
    private static final Logger LOG = Logger.getLogger(PaymentInfoValidatorBean.class.getName());

    @Override
    public boolean monthlyBasicSalaryAboveZero(int monthlyBasicSalary) {
        LOG.info("monthlyBasicSalaryAboveZero");
        return monthlyBasicSalary > 0;
    }

    @Override
    public boolean salaryLevelNotNull(SalaryLevelEnum salaryLevel) {
        LOG.info("salaryLevelNotNull");
        return salaryLevel != null;
    }

    @Override
    public boolean bonusForSuccessZeroOrAbove(int bonusForSuccess) {
        LOG.info("bonusForSuccessZeroOrAbove");
        return bonusForSuccess >= 0;
    }

    @Override
    public boolean numberOfSharesZeroOrAbove(int numberOfShares) {
        LOG.info("numberOfSharesZeroOrAbove");
        return numberOfShares >= 0;
    }

    public boolean salaryLevelIsAssociateOrExecutive(SalaryLevelEnum salaryLevelEnum, Integer bonusForSuccess) {
        if (salaryLevelEnum.equals(ASSOCIATE) || salaryLevelEnum.equals(EXECUTIVE)) {
            return bonusForSuccess > 0;
        }
        return true;
    }

    public boolean salaryLevelIsExecutive(SalaryLevelEnum salaryLevelEnum, Integer numberOfShares) {
        if (salaryLevelEnum.equals(EXECUTIVE)) {
            return numberOfShares <= 0;
        }
        return true;
    }

    @Override
    public boolean PaymentInfoDtoRules(PaymentInfoDto paymentInfoDto) {

        LOG.info("PaymentInfoDtoRules");

        return monthlyBasicSalaryAboveZero(paymentInfoDto.getMonthlyBasicSalary()) &&
                salaryLevelNotNull(paymentInfoDto.getSalaryLevel()) &&
                bonusForSuccessZeroOrAbove(paymentInfoDto.getBonusForSuccess()) &&
                numberOfSharesZeroOrAbove(paymentInfoDto.getNumberOfShares()) &&
                salaryLevelIsAssociateOrExecutive(paymentInfoDto.getSalaryLevel(), paymentInfoDto.getBonusForSuccess()) &&
                salaryLevelIsExecutive(paymentInfoDto.getSalaryLevel(), paymentInfoDto.getNumberOfShares());
    }

}
