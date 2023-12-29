package com.ihr.ihr.common.dtos;

import com.ihr.ihr.common.enums.GenderEnum;
import com.ihr.ihr.entities.BankInfo;

import java.time.LocalDate;

public class EmployeeDto {
    private Long id;
    private String name;
    private String surname;
    private PaymentInfoDto paymentInfoDto;
    private BankInfoDto bankInfoDto;
    private GenderEnum gender;
    private LocalDate dateOfBirth;
    private String address;
    private String religion;
    private Integer hoursPerWeek;

    public EmployeeDto(Long id, String name, String surname, GenderEnum gender, LocalDate dateOfBirth, String address, String religion, Integer hoursPerWeek) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.religion = religion;
        this.hoursPerWeek = hoursPerWeek;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public Integer getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(Integer hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }
}
