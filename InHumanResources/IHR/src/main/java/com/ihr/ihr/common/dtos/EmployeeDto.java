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
}
