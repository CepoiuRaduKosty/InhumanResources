package com.ihr.ihr.entities;

import com.ihr.ihr.common.enums.SalaryLevelEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class PaymentInfo {
    @Id
    @GeneratedValue
    private Integer id;

    public Integer monthlyBasicSalary;

    public SalaryLevelEnum salaryLevel;

    public Integer bonusForSuccess;

    public Integer numberOfShares;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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
