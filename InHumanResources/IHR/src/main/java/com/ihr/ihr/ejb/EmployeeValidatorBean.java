package com.ihr.ihr.ejb;


import com.ihr.ihr.common.dtos.EmployeeDto;
import com.ihr.ihr.common.enums.GenderEnum;
import com.ihr.ihr.common.interf.EmployeeValidation;
import jakarta.ejb.Stateless;

import java.util.logging.Logger;
import java.time.LocalDate;

@Stateless
public class EmployeeValidatorBean implements EmployeeValidation {
    private static final Logger LOG = Logger.getLogger(EmployeeValidatorBean.class.getName());

    public boolean isNameValid(String name) {
        LOG.info("NameValidator");
        return !name.isEmpty();
    }

    public boolean isSurnameValid(String surname) {
        LOG.info(" SurnameValidator");
        return !surname.isEmpty();
    }

    public boolean isGenderValid(GenderEnum gender) {
        LOG.info("GenderValidator");
        return gender != null;
    }

    public boolean isDateOfBirthValid(LocalDate dateOfBirth) {
        LOG.info("DateOfBirthValidator");
        if (dateOfBirth == null)
            return false;
        return !dateOfBirth.isAfter(LocalDate.now());
    }

    public boolean isAddressValid(String address) {
        LOG.info("AddressValidator");
        return !address.isEmpty();
    }

   public boolean isReligionValid(String religion) {
        LOG.info("ReligionValidator");
        return !religion.isEmpty();
    }

    public boolean isHoursPerWeekValid(Integer hoursPerWeek) {
        LOG.info("HoursPerWeekValidator");
        return hoursPerWeek >= 10 && hoursPerWeek <= 40;
    }

    public boolean isEmployeeDataValid(EmployeeDto employeeDto) {
        LOG.info("EmployeeDataValidator");
        return isNameValid(employeeDto.getName())
                && isSurnameValid(employeeDto.getSurname())
                && isGenderValid(employeeDto.getGender())
                && isDateOfBirthValid(employeeDto.getDateOfBirth())
                && isAddressValid(employeeDto.getAddress())
                && isReligionValid(employeeDto.getReligion())
                && isHoursPerWeekValid(employeeDto.getHoursPerWeek());
    }
}
