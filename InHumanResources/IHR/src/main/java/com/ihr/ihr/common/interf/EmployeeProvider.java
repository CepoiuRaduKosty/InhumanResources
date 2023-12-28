package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.EmployeeDto;
import com.ihr.ihr.common.excep.DateOfBirthException;
import com.ihr.ihr.common.excep.UnknownBankInfoException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;
import com.ihr.ihr.common.excep.WorkingHoursException;

import java.util.Collection;

public interface EmployeeProvider {

    void createEmployee(EmployeeDto employeeDto) throws WorkingHoursException, DateOfBirthException, UnknownBankInfoException, UnknownPaymentInfoException;

    void updateEmployeeById(Long employeeId, EmployeeDto employeeDto) throws DateOfBirthException, WorkingHoursException;

    void deleteEmployeeById(Long employeeId);

    EmployeeDto findById(Long employeeId);

    Collection<EmployeeDto> findAllEmployeesByName(String employeeName);

    Collection<EmployeeDto> findAllEmployees();



}

