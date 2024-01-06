package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.BonusDtos.CreateBonusDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.interf.BonusProvider;
import com.ihr.ihr.common.interf.BonusValidation;
import com.ihr.ihr.common.interf.EmployeeProvider;
import com.ihr.ihr.common.interf.EmployeeValidation;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CreateBonusFindEmployeeServlet", value = "/CreateBonusFindEmployee")
public class CreateBonusFindEmployeeServlet extends HttpServlet {

    @Inject
    EmployeeProvider employeeProvider;

    @Inject
    EmployeeValidation employeeValidation;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/createBonusFindEmployee.jsp").forward(request, response);

    }

    private void handleEmployeeSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeName = request.getParameter("employeeName");

        if (!employeeValidation.isNameValid(employeeName)) {
            request.setAttribute("error", "Invalid employee name. Please try again.");
            request.setAttribute("matchingEmployees", null);
        } else {
            List<EmployeeDto> employeesResult = employeeProvider.findAllEmployeesByName(employeeName);
            if(employeesResult.isEmpty()) {
                request.setAttribute("error", "No employee found with that name. Please try again.");
                request.setAttribute("matchingEmployees", null);
            } else {
                request.setAttribute("matchingEmployees", employeesResult);
            }
        }

        request.getRequestDispatcher("/WEB-INF/pages/createBonusFindEmployee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedEmployee = request.getParameter("selectedEmployee");

        if(selectedEmployee == null || selectedEmployee.isEmpty()) {
            handleEmployeeSearch(request, response);
        }

        //else sent to CreateBonusForEmployee as specified in jsp
    }
}