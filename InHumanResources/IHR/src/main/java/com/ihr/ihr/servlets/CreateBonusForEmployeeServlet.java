package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.BonusDtos.CreateBonusDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.excep.InvalidBonusException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;
import com.ihr.ihr.common.interf.BonusProvider;
import com.ihr.ihr.common.interf.BonusValidation;
import com.ihr.ihr.common.interf.EmployeeProvider;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@DeclareRoles({"EMPLOYEE", "ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"ADMIN"})})
@WebServlet(name = "CreateBonusForEmployeeServlet", value = "/CreateBonusForEmployee")
public class CreateBonusForEmployeeServlet extends HttpServlet {

    @Inject
    EmployeeProvider employeeProvider;

    @Inject
    BonusValidation bonusValidator;

    @Inject
    BonusProvider bonusProvider;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedEmployeeIdParam = request.getParameter("selectedEmployee");
        if(selectedEmployeeIdParam == null) {
            request.setAttribute("error", "Must first select an employee to be able to add custom bonuses to that employee");
            request.getRequestDispatcher("/WEB-INF/pages/createBonusFindEmployee.jsp").forward(request, response);
        } else {
            Long selectedEmployeeId = Long.parseLong(selectedEmployeeIdParam);
            EmployeeDto employeeDto = employeeProvider.findById(selectedEmployeeId);

            if(employeeDto == null) {
                request.setAttribute("error", "Employee not found. Try again.");
                request.getRequestDispatcher("/WEB-INF/pages/createBonusFindEmployee.jsp").forward(request, response);
            } else {
                request.setAttribute("employeeDto", employeeDto);
                request.getRequestDispatcher("/WEB-INF/pages/createBonusForEmployee.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Double bonusValue;
        String bonusDescription;
        String selectedEmployeeIdStr;

        try {
            bonusValue = Double.parseDouble(request.getParameter("value"));
            bonusDescription = request.getParameter("description");
            selectedEmployeeIdStr = request.getParameter("employeeId");
        } catch (NumberFormatException | NullPointerException e) {
            request.setAttribute("error", "Invalid input. Please provide valid bonus value and description.");
            request.getRequestDispatcher("/WEB-INF/pages/createBonusForEmployee.jsp").forward(request, response);
            return;
        }

        Long selectedEmployeeId = Long.parseLong(selectedEmployeeIdStr);
        boolean isValueValid = bonusValidator.isValueValid(bonusValue);
        boolean isDescriptionValid = bonusValidator.isDenumireBonusValid(bonusDescription);

        if (!isValueValid && !isDescriptionValid) {
            request.setAttribute("error", "Invalid bonus value and description. Value must be greater than 0 and description cannot be empty");
        } else if (!isValueValid) {
            request.setAttribute("error", "Invalid bonus value. Value must be greater than 0.");
        } else if (!isDescriptionValid) {
            request.setAttribute("error", "Invalid bonus description. Description cannot be empty.");
        }

        if (!isValueValid || !isDescriptionValid) {
            request.setAttribute("employeeDto", employeeProvider.findById(selectedEmployeeId));
            request.getRequestDispatcher("/WEB-INF/pages/createBonusForEmployee.jsp").forward(request, response);
        } else {
            EmployeeDto employeeDto = employeeProvider.findById(selectedEmployeeId);
            if(employeeDto == null) {
                request.setAttribute("error", "Employee not found. Try again.");
                request.getRequestDispatcher("/WEB-INF/pages/createBonusFindEmployee.jsp").forward(request, response);
            } else {
                try {
                    bonusProvider.createBonusByDto(new CreateBonusDto(
                            bonusValue,
                            bonusDescription,
                            employeeDto.getPaymentInfoDto().getId()
                    ));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }
}