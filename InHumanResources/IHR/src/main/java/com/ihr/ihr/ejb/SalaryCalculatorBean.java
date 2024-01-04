package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BonusDtos.UpdateBonusDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.UpdateEmployeeDto;
import com.ihr.ihr.common.dtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.interf.SalaryCalculatorProvider;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class SalaryCalculatorBean implements SalaryCalculatorProvider {
    private static final Logger LOG = Logger.getLogger(BankInfoBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public double TaxRate(SalaryLevelEnum salaryLevelEnum) {
        if(salaryLevelEnum.equals(SalaryLevelEnum.PROFESSOR))
            return 20d;
        if(salaryLevelEnum.equals(SalaryLevelEnum.LECTURER))
            return 25d;
        if(salaryLevelEnum.equals(SalaryLevelEnum.ASSOCIATE))
            return 30d;
        if(salaryLevelEnum.equals(SalaryLevelEnum.EXECUTIVE))
            return 0d;

        return 0;
    }

    @Override
    public double getSalaryBeforeTaxes(UpdateEmployeeDto updateEmployeeDto, PaymentInfoDto paymentInfoDto, List<UpdateBonusDto> updateBonusDtoList) {

        double additionalSalary = 0;

        for(var bonus : updateBonusDtoList)
            additionalSalary += bonus.getValue();

        if(paymentInfoDto.getSalaryLevel().equals(SalaryLevelEnum.LECTURER) &&
                ((LocalDate.now().getMonth() == Month.JUNE) || (LocalDate.now().getMonth() == Month.NOVEMBER)))
            additionalSalary += (double) (paymentInfoDto.getMonthlyBasicSalary() * updateEmployeeDto.getHoursPerWeek()) / 40;

        if((paymentInfoDto.getSalaryLevel().equals(SalaryLevelEnum.ASSOCIATE) || paymentInfoDto.getSalaryLevel().equals(SalaryLevelEnum.PROFESSOR))
                && LocalDate.now().getMonth() == Month.AUGUST)
            additionalSalary += paymentInfoDto.getBonusForSuccess();

        return (double) (paymentInfoDto.getMonthlyBasicSalary() * updateEmployeeDto.getHoursPerWeek()) / 40 + additionalSalary;
    }

    @Override
    public double getSalaryAfterTaxes(UpdateEmployeeDto updateEmployeeDto, PaymentInfoDto paymentInfoDto, List<UpdateBonusDto> updateBonusDtoList) {
        double salaryBeforeTaxes = getSalaryBeforeTaxes(updateEmployeeDto, paymentInfoDto, updateBonusDtoList);
        double taxRate = TaxRate(paymentInfoDto.getSalaryLevel()) / 100;
        final double socialCharge = 1/10d;

        return salaryBeforeTaxes - (salaryBeforeTaxes * taxRate) - (salaryBeforeTaxes * socialCharge);
    }
}
