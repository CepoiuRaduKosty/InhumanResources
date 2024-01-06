package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.PaycheckDtos.PaycheckDto;
import com.ihr.ihr.common.interf.PaycheckProvider;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "EmployeePaychecksServlet", value = "/EmployeePaychecks")
public class EmployeePaychecksServlet extends HttpServlet {

    @Inject
    PaycheckProvider paycheckProvider;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long employeeId = Long.parseLong(request.getParameter("employeeId"));

        List<PaycheckDto> employeePaychecks = paycheckProvider.getAllPaychecksByEmployeeId(employeeId);

        Collections.sort(employeePaychecks, Comparator.comparing(PaycheckDto::getDate).reversed());

        request.setAttribute("employeePaychecks", employeePaychecks);

        request.getRequestDispatcher("/WEB-INF/pages/EmployeePaychecks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}
