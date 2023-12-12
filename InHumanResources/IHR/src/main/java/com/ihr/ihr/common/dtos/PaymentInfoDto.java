package com.ihr.ihr.common.dtos;

import com.ihr.ihr.common.enums.SalaryLevelEnum;

public class PaymentInfoDto {
    public int id;
    public int monthlyBasicSalary;
    public SalaryLevelEnum salaryLevel;
    public int bonusForSuccess;
    public int numberOfShares;

    public PaymentInfoDto(int id, int monthlyBasicSalary, SalaryLevelEnum salaryLevel, int bonusForSuccess, int numberOfShares) {
        this.id = id;
        this.monthlyBasicSalary = monthlyBasicSalary;
        this.salaryLevel = salaryLevel;
        this.bonusForSuccess = bonusForSuccess;
        this.numberOfShares = numberOfShares;
    }
}
