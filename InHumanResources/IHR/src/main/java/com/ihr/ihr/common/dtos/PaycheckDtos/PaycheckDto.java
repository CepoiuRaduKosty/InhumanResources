package com.ihr.ihr.common.dtos.PaycheckDtos;

import java.time.LocalDate;

public class PaycheckDto {
    private Long id;
    private Long paymentId;
    private LocalDate date;
    private Integer basicSalary;
    private Integer bonusForSuccess;
    private Integer numberOfShares;
    private Integer cumulatedShares;
    private Integer salaryBeforeTaxes;
    private Integer tax;
    private Integer socialCharge;
    private Integer finalSalary;

    public PaycheckDto(Long id, Long paymentId, LocalDate date, Integer basicSalary, Integer bonusForSuccess,
                       Integer numberOfShares, Integer cumulatedShares, Integer salaryBeforeTaxes, Integer tax,
                       Integer socialCharge, Integer finalSalary) {
        this.id = id;
        this.paymentId = paymentId;
        this.date = date;
        this.basicSalary = basicSalary;
        this.bonusForSuccess = bonusForSuccess;
        this.numberOfShares = numberOfShares;
        this.cumulatedShares = cumulatedShares;
        this.salaryBeforeTaxes = salaryBeforeTaxes;
        this.tax = tax;
        this.socialCharge = socialCharge;
        this.finalSalary = finalSalary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
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
}

