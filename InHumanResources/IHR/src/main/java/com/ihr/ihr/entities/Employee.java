package com.ihr.ihr.entities;

import com.ihr.ihr.common.enums.GenderEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Integer Id;
    private String name;
    private String surname;
    private Integer bankInfoId;
    private Integer paymentInfoId;
    private GenderEnum gender;
    private LocalDate dateOfBirth;
    private String address;
    private String religion;
    private Integer hoursPerWeek; // must be within 40-10

    public Integer getId() {
        return this.Id;
    }

    public void setId(Integer id) {
        Id = id;
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

    public void setBankInfoId(Integer id_bankinfo) {
        this.bankInfoId = id_bankinfo;
    }

    public Integer getPaymentInfoId() {
        return this.paymentInfoId;
    }

    public void setPaymentInfoId(Integer id_paymentinfo) {
        this.paymentInfoId = id_paymentinfo;
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
