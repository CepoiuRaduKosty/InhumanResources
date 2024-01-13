package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.PaycheckDtos.PaycheckDto;
import com.ihr.ihr.common.excep.UnknownPaycheckException;
import com.ihr.ihr.common.interf.EmployeeProvider;
import com.ihr.ihr.common.interf.PayDayProvider;
import com.ihr.ihr.common.interf.PaycheckProvider;
import com.ihr.ihr.common.interf.PaymentInfoProvider;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@DeclareRoles({"ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"ADMIN"})})
@WebServlet(name = "PaycheckManagement", value = "/PaycheckManagement")
public class PaycheckManagement extends HttpServlet {

    @Inject
    EmployeeProvider employeeProvider;

    @Inject
    PaycheckProvider paycheckProvider;

    @Inject
    PayDayProvider payDayProvider;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        request.getRequestDispatcher("/WEB-INF/pages/paycheckManagement.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}
