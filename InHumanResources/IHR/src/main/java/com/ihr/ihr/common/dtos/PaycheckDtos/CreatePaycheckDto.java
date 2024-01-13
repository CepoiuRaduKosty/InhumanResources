package com.ihr.ihr.common.dtos.PaycheckDtos;

import java.time.LocalDate;

public class CreatePaycheckDto {
    private Long paymentId;
    private LocalDate date;
    private Double basicSalary;
    private Double bonusForSuccess;
    private Integer numberOfShares;
    private Integer cumulatedShares;
    private Double salaryBeforeTaxes;
    private Double tax;
    private Double socialCharge;
    private Double finalSalary;

    public CreatePaycheckDto(Long paymentId, LocalDate date, Double basicSalary, Double bonusForSuccess, Integer numberOfShares, Integer cumulatedShares, Double salaryBeforeTaxes, Double tax, Double socialCharge, Double finalSalary) {
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
}
