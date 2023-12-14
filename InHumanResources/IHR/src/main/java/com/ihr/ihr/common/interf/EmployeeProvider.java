package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.EmployeeDto;
import com.ihr.ihr.common.excep.DateOfBirthException;
import com.ihr.ihr.common.excep.WorkingHoursException;

import java.util.Collection;

public interface EmployeeProvider {
    EmployeeDto findById(int id_employee);   // returns EmployeeDto of employee with given id or null if none found
    Collection<EmployeeDto> findAllEmployeesByName(String employeeName);  // returns all EmployeeDtos for all employees which contain employeeName in their names
    void createEmployee(EmployeeDto employeeDto) throws WorkingHoursException, DateOfBirthException; // tries to create new employee based on EmployeeDto ; automatically assigns id (id given in dto is ignored)
    // throws DateOfBirthException if date of birth is current time or in future
    // throws WorkingHoursException if working hours outside 40-10 range

    void updateEmployee(int id_employee, EmployeeDto employeeDto) throws DateOfBirthException, WorkingHoursException; // tries to (fully) replace employee data at id_employee with data given in DTO
    // throws DateOfBirthException if date of birth is current time or in future
    // throws WorkingHoursException if working hours outside 40-10 range

    void deleteEmployeeById(int id_employee); // tries to remove employee with given id
}

