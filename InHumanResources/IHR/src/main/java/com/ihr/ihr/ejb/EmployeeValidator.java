package com.ihr.ihr.ejb;


import com.ihr.ihr.common.dtos.EmployeeDto;

import java.time.LocalDate;

public class EmployeeValidator {
    private boolean NameValidator(EmployeeDto employeeDto) {
        return !employeeDto.getName().isEmpty();
    }

    private boolean SurnameValidator(EmployeeDto employeeDto) {
        return !employeeDto.getSurname().isEmpty();
    }

    private boolean GenderValidator(EmployeeDto employeeDto) {
        return employeeDto.getGender() != null;
    }

    private boolean DateOfBirthValidator(EmployeeDto employeeDto) {
        if (employeeDto.getDateOfBirth() == null)
            return false;
        return !employeeDto.getDateOfBirth().isAfter(LocalDate.now());
    }

    private boolean AddressValidator(EmployeeDto employeeDto) {
        return !employeeDto.getAddress().isEmpty();
    }

    private boolean ReligionValidator(EmployeeDto employeeDto) {
        return !employeeDto.getReligion().isEmpty();
    }

    private boolean HoursPerWeekValidator(EmployeeDto employeeDto) {
        return employeeDto.getHoursPerWeek() >= 10 && employeeDto.getHoursPerWeek() <= 40;
    }

    private boolean EmployeeDataValidator(EmployeeDto employeeDto)
    {
        return NameValidator(employeeDto) && SurnameValidator(employeeDto) && GenderValidator(employeeDto)
                && DateOfBirthValidator(employeeDto) && AddressValidator(employeeDto) && DateOfBirthValidator(employeeDto)
                && ReligionValidator(employeeDto) && HoursPerWeekValidator(employeeDto);
    }
}
