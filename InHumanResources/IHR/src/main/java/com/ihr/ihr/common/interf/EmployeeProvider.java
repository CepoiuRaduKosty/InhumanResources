package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.EmployeeDtos.CreateEmployeeDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.UpdateEmployeeDto;
import com.ihr.ihr.common.excep.*;

import java.util.List;

public interface EmployeeProvider {

    void createEmployee(CreateEmployeeDto createEmployeeDto) throws WorkingHoursException, DateOfBirthException, UnknownBankInfoException, UnknownPaymentInfoException, InvalidEmployeeDto;

    void updateEmployeeById(Long employeeId, UpdateEmployeeDto updateEmployeeDto) throws DateOfBirthException, WorkingHoursException, InvalidEmployeeDto;

    void deleteEmployeeById(Long employeeId);

    EmployeeDto findById(Long employeeId);

    List<EmployeeDto> findAllEmployeesByName(String employeeName);

    List<EmployeeDto> findAllEmployees();
}

