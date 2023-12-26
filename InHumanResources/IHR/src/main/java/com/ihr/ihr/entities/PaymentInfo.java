package com.ihr.ihr.entities;

import com.ihr.ihr.common.enums.SalaryLevelEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class PaymentInfo {
    @Id
    @GeneratedValue
    private Long Id;

    private Integer monthlyBasicSalary;

    private SalaryLevelEnum salaryLevel;

    private Integer bonusForSuccess;

    private Integer numberOfShares;
    @OneToOne
    private Employee employee;

    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
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

    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
