package com.ihr.ihr.servlets;

import com.ihr.ihr.common.interf.*;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@DeclareRoles({"ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"ADMIN"})})
@WebServlet(name = "PaycheckManagement", value = "/PaycheckManagement")
public class PaycheckManagement extends HttpServlet {
    @Inject
    PaycheckManagementServiceProvider paycheckManagementServiceProvider;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        paycheckManagementServiceProvider.populatePaycheckManagementDetails(request);
        request.getRequestDispatcher("/WEB-INF/pages/paycheckManagement.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}
