package com.ihr.ihr.common.interf;
import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;

import com.ihr.ihr.common.dtos.EmployeeDtos.UpdateEmployeeDto;
import com.ihr.ihr.common.enums.GenderEnum;

import java.time.LocalDate;

public interface EmployeeValidation {
   boolean isNameValid(String name);

   boolean isSurnameValid(String surname);

   boolean isGenderValid(GenderEnum gender);

   boolean isDateOfBirthValid(LocalDate date);

   boolean isAddressValid(String address);

   boolean isReligionValid(String religion);

   boolean isHoursPerWeekValid(Integer hoursPerWeek);

   boolean isEmployeeDataValid(UpdateEmployeeDto updateEmployeeDto);

}
