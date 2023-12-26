package com.ihr.ihr.entities;

import com.ihr.ihr.common.enums.GenderEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;


import java.time.LocalDate;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long Id;
    private String name;
    private String surname;
    @OneToOne
    private BankInfo bankInfo;
    @OneToOne
    private PaymentInfo paymentInfo;
    private GenderEnum gender;
    private LocalDate dateOfBirth;
    private String address;
    private String religion;
    private Integer hoursPerWeek; // must be within 40-10

    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
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

    public BankInfo getBankInfo() {
        return bankInfo;
    }
    public void setBankInfo(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }
    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
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
