package com.ihr.ihr.common.dtos.EmployeeDtos;

import com.ihr.ihr.common.dtos.BankInfoDtos.BankInfoDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.GenderEnum;

import java.time.LocalDate;

public class CreateEmployeeDto extends UpdateEmployeeDto{
    private PaymentInfoDto paymentInfoDto;
    private BankInfoDto bankInfoDto;
    private Long userID;

    public CreateEmployeeDto(String name, String surname, GenderEnum gender, LocalDate dateOfBirth, String address, String religion, Integer hoursPerWeek) {
        super(name, surname, gender, dateOfBirth, address, religion, hoursPerWeek);
    }

    public CreateEmployeeDto(String name, String surname, GenderEnum gender, LocalDate dateOfBirth, String address, String religion, Integer hoursPerWeek, PaymentInfoDto paymentInfoDto, BankInfoDto bankInfoDto) {
        super(name, surname, gender, dateOfBirth, address, religion, hoursPerWeek);
        this.paymentInfoDto = paymentInfoDto;
        this.bankInfoDto = bankInfoDto;
    }

    public PaymentInfoDto getPaymentInfoDto() {
        return paymentInfoDto;
    }

    public void setPaymentInfoDto(PaymentInfoDto paymentInfoDto) {
        this.paymentInfoDto = paymentInfoDto;
    }

    public BankInfoDto getBankInfoDto() {
        return bankInfoDto;
    }

    public void setBankInfoDto(BankInfoDto bankInfoDto) {
        this.bankInfoDto = bankInfoDto;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
