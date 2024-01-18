package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.PaycheckDtos.PaycheckDto;
import com.ihr.ihr.common.excep.UnknownPaycheckException;
import com.ihr.ihr.common.interf.EmployeeProvider;
import com.ihr.ihr.common.interf.PayDayProvider;
import com.ihr.ihr.common.interf.PaycheckManagementServiceProvider;
import com.ihr.ihr.common.interf.PaycheckProvider;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Stateless
public class PaycheckManagementServiceBean implements PaycheckManagementServiceProvider {
    @Inject
    PayDayProvider payDayProvider;

    @Inject
    PaycheckProvider paycheckProvider;

    @Inject
    EmployeeProvider employeeProvider;


    @Override
    public void populatePaycheckManagementDetails(HttpServletRequest request) {
        if(!payDayProvider.isPayDateSet()){
            request.setAttribute("isPayDateSet", false);
        }

        List<PaycheckDto> paychecks = paycheckProvider.getAllPaychecksByMonth(LocalDate.now().getMonthValue());

        Set<Long> employeesWithPaychecksIds = paychecks.stream().map(paycheck -> {
            try {
                return paycheckProvider.getEmployeeByPaycheck(paycheck.getId()).getId();
            } catch (UnknownPaycheckException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toSet());

        List<EmployeeDto> employeesWithoutPaychecks = employeeProvider.findAllEmployees().stream().filter(employee ->
                !employeesWithPaychecksIds.contains(employee.getId())).collect(Collectors.toList());

        request.setAttribute("employees", employeesWithoutPaychecks);
    }
}
