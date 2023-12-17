package com.ihr.ihr.common.dtos;

import com.ihr.ihr.common.enums.GenderEnum;

import java.time.LocalDate;

public class EmployeeDto {
    private Integer id;
    private String name;
    private String surname;
    private Integer bankInfoId;
    private Integer paymentInfoId;
    private GenderEnum gender;
    private LocalDate dateOfBirth;
    private String address;
    private String religion;
    private Integer hoursPerWeek;

    public EmployeeDto(Integer id, String name, String surname, Integer bankInfoId, Integer paymentInfoId, GenderEnum gender, LocalDate dateOfBirth, String address, String religion, Integer hoursPerWeek) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.bankInfoId = bankInfoId;
        this.paymentInfoId = paymentInfoId;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.religion = religion;
        this.hoursPerWeek = hoursPerWeek;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getBankInfoId() {
        return this.bankInfoId;
    }

    public void setBankInfoId(Integer bankInfoId) {
        this.bankInfoId = bankInfoId;
    }

    public Integer getPaymentInfoId() {
        return this.paymentInfoId;
    }

    public void setPaymentInfoId(Integer paymentInfoId) {
        this.paymentInfoId = paymentInfoId;
    }

    public GenderEnum getGender() {
        return this.gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReligion() {
        return this.religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public Integer getHoursPerWeek() {
        return this.hoursPerWeek;
    }

    public void setHoursPerWeek(Integer hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }
}
