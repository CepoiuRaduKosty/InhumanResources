package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.PaycheckBonusDtos.PaycheckBonusDto;
import com.ihr.ihr.common.dtos.PaycheckDtos.PaycheckDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.excep.UnknownPaycheckException;
import com.ihr.ihr.common.interf.PaycheckBonusProvider;
import com.ihr.ihr.common.interf.PaycheckProvider;
import com.ihr.ihr.common.interf.PaymentInfoProvider;
import com.ihr.ihr.common.interf.PaymentInfoValidation;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "PaycheckView", value = "/PaycheckView")
public class PaycheckViewServlet extends HttpServlet {
    @Inject
    PaycheckProvider paycheckProvider;

    @Inject
    PaycheckBonusProvider paycheckBonusProvider;

    @Inject
    PaymentInfoProvider paymentInfoProvider;

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
