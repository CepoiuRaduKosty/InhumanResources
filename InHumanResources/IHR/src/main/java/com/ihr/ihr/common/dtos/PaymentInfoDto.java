package com.ihr.ihr.common.dtos;

import com.ihr.ihr.common.enums.SalaryLevelEnum;

public class PaymentInfoDto {
    private Long id;
    private Integer monthlyBasicSalary;
    private SalaryLevelEnum salaryLevel;
    private Integer bonusForSuccess;
    private Integer numberOfShares;

    public PaymentInfoDto(Long id, Integer monthlyBasicSalary, SalaryLevelEnum salaryLevel,
                          Integer bonusForSuccess, Integer numberOfShares) {
        this.id = id;
        this.monthlyBasicSalary = monthlyBasicSalary;
        this.salaryLevel = salaryLevel;
        this.bonusForSuccess = bonusForSuccess;
        this.numberOfShares = numberOfShares;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMonthlyBasicSalary() {
        return this.monthlyBasicSalary;
    }

    public void setMonthlyBasicSalary(Integer monthlyBasicSalary) {
        this.monthlyBasicSalary = monthlyBasicSalary;
    }

    public SalaryLevelEnum getSalaryLevel() {
        return this.salaryLevel;
    }

    public void setSalaryLevel(SalaryLevelEnum salaryLevel) {
        this.salaryLevel = salaryLevel;
    }

    public Integer getBonusForSuccess() {
        return this.bonusForSuccess;
    }

    public void setBonusForSuccess(Integer bonusForSuccess) {
        this.bonusForSuccess = bonusForSuccess;
    }

    public Integer getNumberOfShares() {
        return this.numberOfShares;
    }

    public void setNumberOfShares(Integer numberOfShares) {
        this.numberOfShares = numberOfShares;
    }
}
