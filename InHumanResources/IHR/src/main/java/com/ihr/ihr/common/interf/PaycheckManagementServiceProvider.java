package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import jakarta.servlet.http.HttpServletRequest;

public interface PaycheckManagementServiceProvider {
    void populatePaycheckManagementDetails(HttpServletRequest request);
}
