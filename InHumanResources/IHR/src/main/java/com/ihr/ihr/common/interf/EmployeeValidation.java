package com.ihr.ihr.common.interf;
import com.ihr.ihr.common.dtos.EmployeeDto;

import com.ihr.ihr.common.enums.GenderEnum;
import com.ihr.ihr.common.excep.DateOfBirthException;
import com.ihr.ihr.common.excep.WorkingHoursException;
import com.ihr.ihr.ejb.EmployeeBean;
import com.ihr.ihr.ejb.EmployeeValidatorBean;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Bool;

import java.time.LocalDate;

public interface EmployeeValidation {
   boolean nameValidator(String name);

   boolean surnameValidator(String surname);

   boolean genderValidator(GenderEnum gender);

   boolean dateOfBirthValidator(LocalDate date);

   boolean addressValidator(String address);

   boolean religionValidator(String religion);

   boolean hoursPerWeekValidator(Integer hoursPerWeek);

   boolean employeeDataValidator(EmployeeDto employeeDto);

}
