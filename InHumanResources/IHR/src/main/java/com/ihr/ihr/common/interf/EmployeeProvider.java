package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.EmployeeDto;
import com.ihr.ihr.common.excep.DateOfBirthException;
import com.ihr.ihr.common.excep.WorkingHoursException;

import java.util.Collection;

public interface EmployeeProvider {
    EmployeeDto findById(Integer employeeId);
    Collection<EmployeeDto> findAllEmployeesByName(String employeeName);
    void createEmployee(EmployeeDto employeeDto) throws WorkingHoursException, DateOfBirthException;
    void updateEmployeeById(Integer employeeId, EmployeeDto employeeDto) throws DateOfBirthException, WorkingHoursException;
    void deleteEmployeeById(Integer employeeId);
}

