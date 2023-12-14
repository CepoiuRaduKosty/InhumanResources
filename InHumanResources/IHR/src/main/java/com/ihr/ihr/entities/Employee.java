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
    private int Id;

    private String name;
    private String surname;
    private int id_bankinfo;
    private int id_paymentinfo;
    private GenderEnum gender;
    private LocalDate dateOfBirth;
    private String address;
    private String religion;
    private int hoursPerWeek; // must be within 40-10

    public int getId() {
        return Id;
    }

    public void setId(int id) {
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

    public int getId_bankinfo() {
        return id_bankinfo;
    }

    public void setId_bankinfo(int id_bankinfo) {
        this.id_bankinfo = id_bankinfo;
    }

    public int getId_paymentinfo() {
        return id_paymentinfo;
    }

    public void setId_paymentinfo(int id_paymentinfo) {
        this.id_paymentinfo = id_paymentinfo;
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

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }
}
