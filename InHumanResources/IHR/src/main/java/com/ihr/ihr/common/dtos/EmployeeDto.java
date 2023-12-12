package com.ihr.ihr.common.dtos;

import com.ihr.ihr.common.enums.GenderEnum;
import java.time.LocalDate;

public class EmployeeDto {
    public int id;
    public String name;
    public String surname;
    public int id_bankinfo;
    public int id_paymentinfo;
    public GenderEnum gender;
    public LocalDate dateOfBirth;
    public String address;
    public String religion;
    public int hoursPerWeek; // must be within 40-10

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
}
