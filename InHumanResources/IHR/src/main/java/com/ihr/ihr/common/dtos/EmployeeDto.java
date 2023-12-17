package com.ihr.ihr.common.dtos;

import com.ihr.ihr.common.enums.GenderEnum;

import java.time.LocalDate;

public class EmployeeDto {
    private int id;
    private String name;
    private String surname;
    private int id_bankinfo;
    private int id_paymentinfo;
    private GenderEnum gender;
    private LocalDate dateOfBirth;
    private String address;
    private String religion;
    private int hoursPerWeek;


    public EmployeeDto(int id, String name, String surname, int id_bankinfo, int id_paymentinfo, GenderEnum gender, LocalDate dateOfBirth, String address, String religion, int hoursPerWeek) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.id_bankinfo = id_bankinfo;
        this.id_paymentinfo = id_paymentinfo;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.religion = religion;
        this.hoursPerWeek = hoursPerWeek;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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

    public int getId_bankinfo() {
        return this.id_bankinfo;
    }

    public void setId_bankinfo(int id_bankinfo) {
        this.id_bankinfo = id_bankinfo;
    }

    public int getId_paymentinfo() {
        return this.id_paymentinfo;
    }

    public void setId_paymentinfo(int id_paymentinfo) {
        this.id_paymentinfo = id_paymentinfo;
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

    public int getHoursPerWeek() {
        return this.hoursPerWeek;
    }

    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }


}
