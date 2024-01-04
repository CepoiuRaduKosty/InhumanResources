package com.ihr.ihr.common.dtos.EmployeeDtos;

import com.ihr.ihr.common.enums.GenderEnum;

import java.time.LocalDate;

public class EmployeeDto extends CreateEmployeeDto{
    private Long id;

    public EmployeeDto(Long id, String name, String surname, GenderEnum gender, LocalDate dateOfBirth, String address, String religion, Integer hoursPerWeek) {
        super(name, surname, gender, dateOfBirth, address, religion, hoursPerWeek);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
