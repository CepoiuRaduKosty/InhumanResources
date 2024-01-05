package com.ihr.ihr.common.dtos.PaymentInfoDtos;

import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.CreatePaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.entities.PaymentInfo;

import java.util.List;

public class PaymentInfoDto {

    private Long id;
    private Double monthlyBasicSalary;
    private SalaryLevelEnum salaryLevel;
    private Double bonusForSuccess;
    private Integer numberOfShares;
    private EmployeeDto employeeDto;
    private Integer cumulatedShares;
    private List<Long> bonuses;

    public PaymentInfoDto(Long id, Double monthlyBasicSalary, SalaryLevelEnum salaryLevel, Double bonusForSuccess, Integer numberOfShares, Integer cumulatedShares) {
        this.id = id;
        this.monthlyBasicSalary = monthlyBasicSalary;
        this.salaryLevel = salaryLevel;
        this.bonusForSuccess = bonusForSuccess;
        this.numberOfShares = numberOfShares;
        this.cumulatedShares = cumulatedShares;
    }

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

    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    public void setEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
    }

    public Integer getCumulatedShares() {
        return this.cumulatedShares;
    }

    public void setCumulatedShares(Integer cumulatedShares) {
        this.cumulatedShares = cumulatedShares;
    }

    public PaymentInfoDto(CreatePaymentInfoDto createPaymentInfoDto) {
        this.id = 0L;
        this.monthlyBasicSalary = createPaymentInfoDto.getMonthlyBasicSalary();
        this.salaryLevel = createPaymentInfoDto.getSalaryLevel();
        this.bonusForSuccess = createPaymentInfoDto.getBonusForSuccess();
        this.numberOfShares = createPaymentInfoDto.getNumberOfShares();
    }

    public List<Long> getBonuses() {
        return bonuses;
    }

    public void setBonuses(List<Long> bonuses) {
        this.bonuses = bonuses;
    }
}
