package com.ihr.ihr.ejb;


import com.ihr.ihr.common.dtos.EmployeeDto;
import com.ihr.ihr.common.enums.GenderEnum;
import com.ihr.ihr.common.interf.EmployeeValidation;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import java.util.logging.Logger;
import java.time.LocalDate;

@Stateless
public class EmployeeValidatorBean implements EmployeeValidation {
    private static final Logger LOG = Logger.getLogger(EmployeeValidatorBean.class.getName());

    public boolean nameValidator(String name) {
        LOG.info("NameValidator");
        return !name.isEmpty();
    }

    public boolean surnameValidator(String surname) {
        LOG.info(" SurnameValidator");
        return !surname.isEmpty();
    }

    public boolean genderValidator(GenderEnum gender) {
        LOG.info("GenderValidator");
        return gender != null;
    }

    public boolean dateOfBirthValidator(LocalDate dateOfBirth) {
        LOG.info("DateOfBirthValidator");
        if (dateOfBirth == null)
            return false;
        return !dateOfBirth.isAfter(LocalDate.now());
    }

    public boolean addressValidator(String address) {
        LOG.info("AddressValidator");
        return !address.isEmpty();
    }

   public boolean religionValidator(String religion) {
        LOG.info("ReligionValidator");
        return !religion.isEmpty();
    }

    public boolean hoursPerWeekValidator(Integer hoursPerWeek) {
        LOG.info("HoursPerWeekValidator");
        return hoursPerWeek >= 10 && hoursPerWeek <= 40;
    }

    public boolean employeeDataValidator(EmployeeDto employeeDto) {
        LOG.info("EmployeeDataValidator");
        return nameValidator(employeeDto.getName())
                && surnameValidator(employeeDto.getSurname())
                && genderValidator(employeeDto.getGender())
                && dateOfBirthValidator(employeeDto.getDateOfBirth())
                && addressValidator(employeeDto.getAddress())
                && religionValidator(employeeDto.getReligion())
                && hoursPerWeekValidator(employeeDto.getHoursPerWeek());
    }
}
