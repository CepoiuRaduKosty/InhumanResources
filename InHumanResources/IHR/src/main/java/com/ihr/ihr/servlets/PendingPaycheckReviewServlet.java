package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.BonusDtos.BonusDto;
import com.ihr.ihr.common.dtos.BonusDtos.CreateBonusDto;
import com.ihr.ihr.common.dtos.BonusDtos.UpdateBonusDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.PaycheckBonusDtos.CreatePaycheckBonusDto;
import com.ihr.ihr.common.dtos.PaycheckBonusDtos.PaycheckBonusDto;
import com.ihr.ihr.common.dtos.PaycheckDtos.CreatePaycheckDto;
import com.ihr.ihr.common.dtos.PaycheckDtos.PaycheckDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.excep.InvalidPaycheckBonusException;
import com.ihr.ihr.common.excep.UnknownBonusException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;
import com.ihr.ihr.common.interf.*;
import com.ihr.ihr.entities.Paycheck;
import com.ihr.ihr.entities.PaycheckBonus;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DeclareRoles({"EMPLOYEE", "ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN"}), httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"ADMIN"})})
@WebServlet(name = "PendingPaycheckReviewServlet", value = "/PendingPaycheckReview")
public class PendingPaycheckReviewServlet extends HttpServlet {
    @Inject
    EmployeeProvider employeeProvider;

    @Inject
    PendingPaycheckReviewServiceProvider pendingPaycheckReviewServiceProvider;

    @Inject
    PayDayProvider payDayProvider;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String employeeId = request.getParameter("employeeId");
            Long employeeIdLong = Long.parseLong(employeeId);
            EmployeeDto employee = employeeProvider.findById(employeeIdLong);

            if (employee == null || employeeId.isEmpty()) {
                request.setAttribute("error", "invalidId");
                request.getRequestDispatcher("/WEB-INF/pages/pendingPaycheckReview.jsp").forward(request, response);
            } else {
                pendingPaycheckReviewServiceProvider.populatePendingPaycheckReviewDetails(employee, request);
                request.getRequestDispatcher("/WEB-INF/pages/pendingPaycheckReview.jsp").forward(request, response);
            }
        } catch (UnknownPaymentInfoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long employeeId = Long.parseLong(request.getParameter("employeeId"));
            EmployeeDto employeeDto = employeeProvider.findById(employeeId);

            if (!payDayProvider.isPayDateSet() || (payDayProvider.isPayDateSet()) && payDayProvider.getDayOfMonth() != LocalDate.now().getDayOfMonth()) {
                response.sendRedirect(request.getContextPath() + "/AccessDenied");
            } else {
                pendingPaycheckReviewServiceProvider.processPendingPaycheckReview(employeeDto, request);
                response.sendRedirect(request.getContextPath() + "/PaycheckManagement");
            }
        } catch (UnknownPaymentInfoException | InvalidPaycheckBonusException e) {
            throw new RuntimeException(e);
        }
    }
}