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

    private Double basicSalary;

    private Double bonusForSuccess;

    private Integer numberOfShares;

    private Integer cumulatedShares;

    private Double salaryBeforeTaxes;

    private Double tax;

    private Double socialCharge;

    private Double finalSalary;

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

    public Double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary;
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

    public Integer getCumulatedShares() {
        return cumulatedShares;
    }

    public void setCumulatedShares(Integer cumulatedShares) {
        this.cumulatedShares = cumulatedShares;
    }

    public Double getSalaryBeforeTaxes() {
        return salaryBeforeTaxes;
    }

    public void setSalaryBeforeTaxes(Double salaryBeforeTaxes) {
        this.salaryBeforeTaxes = salaryBeforeTaxes;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getSocialCharge() {
        return socialCharge;
    }

    public void setSocialCharge(Double socialCharge) {
        this.socialCharge = socialCharge;
    }

    public Double getFinalSalary() {
        return finalSalary;
    }

    public void setFinalSalary(Double finalSalary) {
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
