package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.EmployeeDto;
import com.ihr.ihr.common.enums.GenderEnum;
import com.ihr.ihr.common.interf.EmployeeValidation;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeValidationTest {
    private final EmployeeValidation tempEmployeeValidation = new EmployeeValidatorBean();
    @Inject
    EmployeeValidation employeeValidation;

    @Test
    void isNameValid_positive() {
        String name = "george";
        boolean isValid = tempEmployeeValidation.isNameValid(name);
        assertTrue(isValid);
    }

    @Test
    void isNameValid_negative() {
        String name = "";
        boolean isValid = tempEmployeeValidation.isNameValid(name);
        assertFalse(isValid);
    }

    @Test
    void isSurnameValid_positive() {
        String surname = "george";
        boolean isValid = tempEmployeeValidation.isSurnameValid(surname);
        assertTrue(isValid);
    }

    @Test
    void isSurnameValid_negative() {
        String surname = "";
        boolean isValid = tempEmployeeValidation.isSurnameValid(surname);
        assertFalse(isValid);
    }

    @Test
    void isGenderValid_positive() {
        GenderEnum genderEnum = GenderEnum.MALE;
        boolean isValid = tempEmployeeValidation.isGenderValid(genderEnum);
        assertTrue(isValid);
    }

    @Test
    void isGenderValid_negative() {
        GenderEnum genderEnum = null;
        boolean isValid = tempEmployeeValidation.isGenderValid(genderEnum);
        assertFalse(isValid);
    }

    @Test
    void isDateOfBirthValid_positive() {
        LocalDate dateOfBirth = LocalDate.ofYearDay(2001, 12);
        boolean isValid = tempEmployeeValidation.isDateOfBirthValid(dateOfBirth);
        assertTrue(isValid);
    }

    @Test
    void isDateOfBirthValid_negative() {
        LocalDate dateOfBirth = LocalDate.ofYearDay(2031, 12);
        boolean isValid = tempEmployeeValidation.isDateOfBirthValid(dateOfBirth);
        assertFalse(isValid);
    }

    @Test
    void isAddressValid_positive() {
        String adress = "aici";
        boolean isValid = tempEmployeeValidation.isAddressValid(adress);
        assertTrue(isValid);
    }

    @Test
    void isAddressValid_negative() {
        String adress = "";
        boolean isValid = tempEmployeeValidation.isAddressValid(adress);
        assertFalse(isValid);
    }

    @Test
    void isReligionValid_positive() {
        String religion = "Musulman";
        boolean isValid = tempEmployeeValidation.isReligionValid(religion);
        assertTrue(isValid);
    }

    @Test
    void isReligionValid_negative() {
        String religion = "";
        boolean isValid = tempEmployeeValidation.isReligionValid(religion);
        assertFalse(isValid);
    }

    @Test
    void isHoursPerWeekValid_positive() {
        int hoursPerWeek = 25;
        boolean isValid = tempEmployeeValidation.isHoursPerWeekValid(hoursPerWeek);
        assertTrue(isValid);
    }

    @Test
    void isHoursPerWeekValid_negative() {
        int hoursPerWeek = 3;
        boolean isValid = tempEmployeeValidation.isHoursPerWeekValid(hoursPerWeek);
        assertFalse(isValid);
    }

    @Test
    void isEmployeeDataValid_positive() {
        EmployeeDto validDto = new EmployeeDto(1L, "Radu", "Radu", GenderEnum.MALE, LocalDate.ofYearDay(2001, 12), "Aici", "Musulman", 25);
        assertTrue(tempEmployeeValidation.isEmployeeDataValid(validDto));
    }

    @Test
    void isEmployeeDataValid_negative() {
        EmployeeDto invalidDto = new EmployeeDto(1L, "", "", GenderEnum.MALE, LocalDate.ofYearDay(2001, 12), "Aici", "Musulman", 25);
        assertFalse(tempEmployeeValidation.isEmployeeDataValid(invalidDto));
    }

}
