package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.CreatePaycheckDto;
import com.ihr.ihr.common.dtos.CreatePaymentInfoDto;
import com.ihr.ihr.common.dtos.PaycheckDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.excep.NonPositiveIncomeException;
import com.ihr.ihr.common.interf.PaycheckProvider;
import com.ihr.ihr.common.interf.PaymentInfoProvider;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.xml.bind.ValidationException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "TestServlet", value = "/test")
public class TestServlet extends HttpServlet {

    @Inject
    PaymentInfoProvider paymentInfoProvider;

    @Inject
    PaycheckProvider paycheckProvider;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        ArrayList<String> debug = new ArrayList<>();

        // test code
        try {
            for (PaycheckDto paycheckDto : paycheckProvider.getAllPaychecksByMonth(8)) {
                debug.add("month 8 " + paycheckDto.getId());
            }
            for (PaycheckDto paycheckDto : paycheckProvider.getAllPaychecksByMonth(1)) {
                debug.add("month 1 " + paycheckDto.getId());
            }
            for (PaycheckDto paycheckDto : paycheckProvider.getAllPaychecksByMonth(6)) {
                debug.add("month 6 " + paycheckDto.getId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // end test

        request.setAttribute("debugtxt", debug);
        request.getRequestDispatcher("/WEB-INF/pages/test.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}