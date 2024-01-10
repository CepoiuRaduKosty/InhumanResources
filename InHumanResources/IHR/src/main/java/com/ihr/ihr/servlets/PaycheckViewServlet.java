package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.PaycheckBonusDtos.PaycheckBonusDto;
import com.ihr.ihr.common.dtos.PaycheckDtos.PaycheckDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.excep.UnknownPaycheckException;
import com.ihr.ihr.common.interf.*;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


@DeclareRoles({"EMPLOYEE", "ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN", "EMPLOYEE"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"ADMIN", "EMPLOYEE"})})
@WebServlet(name = "PaycheckView", value = "/PaycheckView")
public class PaycheckViewServlet extends HttpServlet {
    @Inject
    PaycheckProvider paycheckProvider;

    @Inject
    PaycheckBonusProvider paycheckBonusProvider;

    @Inject
    PaymentInfoProvider paymentInfoProvider;

    @Inject
    HTTPSessionManagement httpSessionManagement;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paycheckIdStr = request.getParameter("paycheckId");


        if(paycheckIdStr == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        Long paycheckId = Long.parseLong((paycheckIdStr));

        PaycheckDto paycheckDto = null;
        try {
            paycheckDto = paycheckProvider.getPaycheckById(paycheckId);
        } catch (UnknownPaycheckException e) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        Long paymentID = paycheckDto.getPaymentId();
        if (!request.isUserInRole("ADMIN") && !Objects.equals(paymentID, httpSessionManagement.getPaymentIdLoggedIn(request))) {
            request.getRequestDispatcher("/WEB-INF/pages/accessDenied.jsp").forward(request, response);
            return;
        }

        List<PaycheckBonusDto> paycheckBonusDtos = paycheckBonusProvider.getAllPaycheckBonusesByPaycheckId(paycheckId);

        PaymentInfoDto paymentInfoDto = paymentInfoProvider.findPaymentInfoById(paycheckDto.getPaymentId());

        request.setAttribute("paycheck", paycheckDto);

        request.setAttribute("paycheckBonus", paycheckBonusDtos);

        request.setAttribute("salaryLevel", paymentInfoDto.getSalaryLevel().toString());

        request.getRequestDispatcher("/WEB-INF/pages/PaycheckView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}
