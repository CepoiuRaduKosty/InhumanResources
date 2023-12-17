package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.EmployeeDto;
import com.ihr.ihr.common.excep.DateOfBirthException;
import com.ihr.ihr.common.excep.WorkingHoursException;

import java.util.Collection;

public interface EmployeeProvider {
    EmployeeDto findById(int id_employee);
    Collection<EmployeeDto> findAllEmployeesByName(String employeeName);
    void createEmployee(EmployeeDto employeeDto) throws WorkingHoursException, DateOfBirthException;
    void updateEmployeeById(int id_employee, EmployeeDto employeeDto) throws DateOfBirthException, WorkingHoursException;
    void deleteEmployeeById(int id_employee);
}

