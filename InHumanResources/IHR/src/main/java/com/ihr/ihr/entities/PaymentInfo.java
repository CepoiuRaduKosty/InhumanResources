package com.ihr.ihr.entities;

import com.ihr.ihr.common.enums.SalaryLevelEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import java.util.Collection;

@Entity
public class PaymentInfo {
    @Id
    @GeneratedValue
    private Long id;

    private Double monthlyBasicSalary;

    private SalaryLevelEnum salaryLevel;

    private Double bonusForSuccess;

    private Integer numberOfShares;

    @Basic
    private Integer cumulatedShares;

    @OneToOne
    private Employee employee;

    @OneToMany(mappedBy = "paymentInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BonusInfo> bonuses = new ArrayList<>();

    @OneToMany(mappedBy = "paymentInfo")
    private Collection<Paycheck> paychecks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMonthlyBasicSalary() {
        return monthlyBasicSalary;
    }

    public void setMonthlyBasicSalary(Double monthlyBasicSalary) {
        this.monthlyBasicSalary = monthlyBasicSalary;
    }

    public SalaryLevelEnum getSalaryLevel() {
        return salaryLevel;
    }

    public void setSalaryLevel(SalaryLevelEnum salaryLevel) {
        this.salaryLevel = salaryLevel;
    }

    public Double getBonusForSuccess() {
        return bonusForSuccess;
    }

    public void setBonusForSuccess(Double bonusForSuccess) {
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

    public Integer getCumulatedShares() {
        return this.cumulatedShares;
    }

    public void setCumulatedShares(Integer cumulatedShares) {
        this.cumulatedShares = cumulatedShares;
    }


    public List<BonusInfo> getBonuses() {
        return bonuses;
    }

    public void setBonuses(List<BonusInfo> bonuses) {
        this.bonuses = bonuses;
    }

    public Collection<Paycheck> getPaychecks() {
        return paychecks;
    }

    public void setPaychecks(Collection<Paycheck> paychecks) {
        this.paychecks = paychecks;
    }
}
