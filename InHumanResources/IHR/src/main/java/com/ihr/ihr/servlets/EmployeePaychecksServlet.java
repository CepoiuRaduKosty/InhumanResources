package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.PaycheckDtos.PaycheckDto;
import com.ihr.ihr.common.interf.HTTPSessionManagement;
import com.ihr.ihr.common.interf.PaycheckProvider;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@DeclareRoles({"EMPLOYEE", "ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN", "EMPLOYEE"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"ADMIN", "EMPLOYEE"})})
@WebServlet(name = "EmployeePaychecksServlet", value = "/EmployeePaychecks")
public class EmployeePaychecksServlet extends HttpServlet {

    @Inject
    PaycheckProvider paycheckProvider;

    @Inject
    HTTPSessionManagement httpSessionManagement;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeIdStr = request.getParameter("employeeId");

        if (employeeIdStr == null ||
                (!request.isUserInRole("ADMIN") && Long.parseLong(employeeIdStr) != httpSessionManagement.getEmployeeIdLoggedIn(request))) {
            request.getRequestDispatcher("/WEB-INF/pages/accessDenied.jsp").forward(request, response);
            return;
        }

        Long employeeId = Long.parseLong(employeeIdStr);

        List<PaycheckDto> employeePaychecks = paycheckProvider.getAllPaychecksByEmployeeId(employeeId);

        employeePaychecks.sort(Comparator.comparing(PaycheckDto::getDate).reversed());

        request.setAttribute("employeePaychecks", employeePaychecks);

        request.getRequestDispatcher("/WEB-INF/pages/EmployeePaychecks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}
