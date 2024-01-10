package com.ihr.ihr.common.interf;

import java.util.List;

import com.ihr.ihr.common.dtos.EmployeeDtos.CreateEmployeeDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.UpdateEmployeeDto;
import com.ihr.ihr.common.excep.DateOfBirthException;
import com.ihr.ihr.common.excep.InvalidEmployeeDto;
import com.ihr.ihr.common.excep.UnknownBankInfoException;
import com.ihr.ihr.common.excep.UnknownEmployeeException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;
import com.ihr.ihr.common.excep.UnknownUserException;
import com.ihr.ihr.common.excep.WorkingHoursException;

public interface EmployeeProvider {

    Long createEmployee(CreateEmployeeDto createEmployeeDto) throws WorkingHoursException, DateOfBirthException, UnknownBankInfoException, UnknownPaymentInfoException, InvalidEmployeeDto;

    void updateEmployeeById(Long employeeId, UpdateEmployeeDto updateEmployeeDto) throws DateOfBirthException, WorkingHoursException, InvalidEmployeeDto;

    void deleteEmployeeById(Long employeeId);

    EmployeeDto findById(Long employeeId);

    List<EmployeeDto> findAllEmployeesByName(String employeeName);

    List<EmployeeDto> findAllEmployees();

    void setUserToEmployee(Long userID, Long employeeID) throws UnknownEmployeeException, UnknownUserException;
}

