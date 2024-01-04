package com.ihr.ihr.common.dtos;

import com.ihr.ihr.common.enums.SalaryLevelEnum;

public class CreatePaymentInfoDto {
    private Double monthlyBasicSalary;
    private SalaryLevelEnum salaryLevel;
    private Double bonusForSuccess;
    private Integer numberOfShares;
    private Integer cumulatedShares;

    public CreatePaymentInfoDto(Double monthlyBasicSalary, SalaryLevelEnum salaryLevel,
                                Double bonusForSuccess, Integer numberOfShares, Integer cumulatedShares) {
        this.monthlyBasicSalary = monthlyBasicSalary;
        this.salaryLevel = salaryLevel;
        this.bonusForSuccess = bonusForSuccess;
        this.numberOfShares = numberOfShares;
        this.cumulatedShares = cumulatedShares;
    }

    public CreatePaymentInfoDto(PaymentInfoDto paymentInfoDto) {
        this.monthlyBasicSalary = paymentInfoDto.getMonthlyBasicSalary();
        this.salaryLevel = paymentInfoDto.getSalaryLevel();
        this.bonusForSuccess = paymentInfoDto.getBonusForSuccess();
        this.numberOfShares = paymentInfoDto.getNumberOfShares();
        this.cumulatedShares = paymentInfoDto.getCumulatedShares();
    }


    public Double getMonthlyBasicSalary() {
        return this.monthlyBasicSalary;
    }

    public void setMonthlyBasicSalary(Double monthlyBasicSalary) {
        this.monthlyBasicSalary = monthlyBasicSalary;
    }

    public SalaryLevelEnum getSalaryLevel() {
        return this.salaryLevel;
    }

    public void setSalaryLevel(SalaryLevelEnum salaryLevel) {
        this.salaryLevel = salaryLevel;
    }

    public Double getBonusForSuccess() {
        return this.bonusForSuccess;
    }

    public void setBonusForSuccess(Double bonusForSuccess) {
        this.bonusForSuccess = bonusForSuccess;
    }

    public Integer getNumberOfShares() {
        return this.numberOfShares;
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
}
