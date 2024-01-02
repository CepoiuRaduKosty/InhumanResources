package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.EmployeeDtos.CreateEmployeeDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.UpdateEmployeeDto;
import com.ihr.ihr.common.excep.DateOfBirthException;
import com.ihr.ihr.common.excep.UnknownBankInfoException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;
import com.ihr.ihr.common.excep.WorkingHoursException;

import java.util.List;

public interface EmployeeProvider {

    void createEmployee(CreateEmployeeDto createEmployeeDto) throws WorkingHoursException, DateOfBirthException, UnknownBankInfoException, UnknownPaymentInfoException;

    void updateEmployeeById(Long employeeId, UpdateEmployeeDto updateEmployeeDto) throws DateOfBirthException, WorkingHoursException;

    void deleteEmployeeById(Long employeeId);

    EmployeeDto findById(Long employeeId);

    List<EmployeeDto> findAllEmployeesByName(String employeeName);

    List<EmployeeDto> findAllEmployees();



}

