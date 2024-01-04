package com.ihr.ihr.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Paycheck {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;

    private Integer basicSalary;

    private Integer bonusForSuccess;

    private Integer numberOfShares;

    private Integer cumulatedShares;

    private Integer salaryBeforeTaxes;

    private Integer tax;

    private Integer socialCharge;

    private Integer finalSalary;

    @ManyToOne
    private PaymentInfo paymentInfo;

    @OneToMany(mappedBy = "paycheck", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaycheckBonus> paycheckBonuses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Integer basicSalary) {
        this.basicSalary = basicSalary;
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

    public Integer getCumulatedShares() {
        return cumulatedShares;
    }

    public void setCumulatedShares(Integer cumulatedShares) {
        this.cumulatedShares = cumulatedShares;
    }

    public Integer getSalaryBeforeTaxes() {
        return salaryBeforeTaxes;
    }

    public void setSalaryBeforeTaxes(Integer salaryBeforeTaxes) {
        this.salaryBeforeTaxes = salaryBeforeTaxes;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getSocialCharge() {
        return socialCharge;
    }

    public void setSocialCharge(Integer socialCharge) {
        this.socialCharge = socialCharge;
    }

    public Integer getFinalSalary() {
        return finalSalary;
    }

    public void setFinalSalary(Integer finalSalary) {
        this.finalSalary = finalSalary;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public List<PaycheckBonus> getPaycheckBonuses() {
        return paycheckBonuses;
    }

    public void setPaycheckBonuses(List<PaycheckBonus> paycheckBonuses) {
        this.paycheckBonuses = paycheckBonuses;
    }
}
