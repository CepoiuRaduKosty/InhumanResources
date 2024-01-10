package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.BankInfoDtos.BankInfoDto;
import com.ihr.ihr.common.dtos.BonusDtos.BonusDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.interf.*;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

@DeclareRoles({"EMPLOYEE", "ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN", "EMPLOYEE"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"ADMIN", "EMPLOYEE"})})
@WebServlet(name = "EmployeeDetailsServlet", value = "/EmployeeDetails")
public class EmployeeDetailsServlet extends HttpServlet {
    @Inject
    EmployeeProvider employeeProvider;
    @Inject
    BankInfoProvider bankInfoProvider;
    @Inject
    PaymentInfoProvider paymentInfoProvider;
    @Inject
    BonusProvider bonusProvider;
    @Inject
    HTTPSessionManagement httpSessionManagement;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        String id_link = null;
        boolean isCumulatedSharesHidden = true;
        boolean isBonusForSuccessHidden = true;
        boolean isnumberOfSharesHidden = true;
        id_link = request.getParameter("id_link");

        if (id_link == null ||
                (!request.isUserInRole("ADMIN") && Long.parseLong(id_link) != httpSessionManagement.getEmployeeIdLoggedIn(request))) {
            request.getRequestDispatcher("/WEB-INF/pages/accessDenied.jsp").forward(request, response);
            return;
        }

        EmployeeDto employeeDto = employeeProvider.findById(Long.parseLong(id_link));

        if (employeeDto == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        List<BonusDto> bonuses = bonusProvider.getAllBonusesByPaymentId(employeeDto.getPaymentInfoDto().getId());

        PaymentInfoDto paymentInfoDto = paymentInfoProvider.findPaymentInfoById(employeeDto.getPaymentInfoDto().getId());
        BankInfoDto bankInfoDto = bankInfoProvider.getById(employeeDto.getBankInfoDto().getId());
        if (paymentInfoDto.getSalaryLevel() == SalaryLevelEnum.PROFESSOR)
            isCumulatedSharesHidden = false;
        if (paymentInfoDto.getSalaryLevel() == SalaryLevelEnum.EXECUTIVE || paymentInfoDto.getSalaryLevel() == SalaryLevelEnum.ASSOCIATE)
            isBonusForSuccessHidden = false;
        if (paymentInfoDto.getSalaryLevel() == SalaryLevelEnum.EXECUTIVE || paymentInfoDto.getSalaryLevel() == SalaryLevelEnum.PROFESSOR)
            isnumberOfSharesHidden = false;

        request.setAttribute("employee", employeeDto);
        request.setAttribute("paymentInfo", paymentInfoDto);
        request.setAttribute("bankInfo", bankInfoDto);
        request.setAttribute("bonuses", bonuses);

        request.setAttribute("employee_id", id_link);
        request.setAttribute("isCumulatedSharesHidden", String.valueOf(isCumulatedSharesHidden));
        request.setAttribute("isBonusForSuccessHidden", String.valueOf(isBonusForSuccessHidden));
        request.setAttribute("isnumberOfSharesHidden", String.valueOf(isnumberOfSharesHidden));

        request.getRequestDispatcher("/WEB-INF/pages/EmployeeDetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}