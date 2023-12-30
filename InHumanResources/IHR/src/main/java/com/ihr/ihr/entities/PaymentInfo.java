package com.ihr.ihr.entities;

import com.ihr.ihr.common.enums.SalaryLevelEnum;
import jakarta.persistence.*;

@Entity
public class PaymentInfo {
    @Id
    @GeneratedValue
    private Long id;

    private Integer monthlyBasicSalary;

    private SalaryLevelEnum salaryLevel;

    private Integer bonusForSuccess;

    private Integer numberOfShares;

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

    @OneToOne
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    @Basic
    private Integer cummulatedShares;

    public Integer getCummulatedShares() {
        return this.cummulatedShares;
    }

    public void setCummulatedShares(Integer cummulatedShares) {
        this.cummulatedShares = cummulatedShares;
    }
}
