package com.ihr.ihr.common.dtos;

import com.ihr.ihr.common.enums.SalaryLevelEnum;

public class PaymentInfoDto {
    private Long id;
    private Integer monthlyBasicSalary;
    private SalaryLevelEnum salaryLevel;
    private Integer bonusForSuccess;
    private Integer numberOfShares;
    private Long employeeId;

    public PaymentInfoDto(Long id, Integer monthlyBasicSalary, SalaryLevelEnum salaryLevel,
                          Integer bonusForSuccess, Integer numberOfShares, Long employeeId) {
        this.id = id;
        this.monthlyBasicSalary = monthlyBasicSalary;
        this.salaryLevel = salaryLevel;
        this.bonusForSuccess = bonusForSuccess;
        this.numberOfShares = numberOfShares;
        this.employeeId = employeeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMonthlyBasicSalary() {
        return monthlyBasicSalary;
    }

    public void setMonthlyBasicSalary(Integer monthlyBasicSalary) {
        this.monthlyBasicSalary = monthlyBasicSalary;
    }

    public SalaryLevelEnum getSalaryLevel() {
        return salaryLevel;
    }

    public void setSalaryLevel(SalaryLevelEnum salaryLevel) {
        this.salaryLevel = salaryLevel;
    }

    public Integer getBonusForSuccess() {
        return bonusForSuccess;
    }

    public void setBonusForSuccess(Integer bonusForSuccess) {
        this.bonusForSuccess = bonusForSuccess;
    }

    public Integer getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(Integer numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
