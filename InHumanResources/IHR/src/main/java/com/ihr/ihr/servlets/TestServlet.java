package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.BankInfoDto;
import com.ihr.ihr.common.dtos.CreatePaymentInfoDto;
import com.ihr.ihr.common.dtos.EmployeeDto;
import com.ihr.ihr.common.dtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.GenderEnum;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.interf.BankInfoProvider;
import com.ihr.ihr.common.interf.EmployeeProvider;

import com.ihr.ihr.common.interf.PaymentInfoProvider;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "TestServlet", value = "/test")
public class TestServlet extends HttpServlet {

    @Inject
    BankInfoProvider bankInfoProvider;

    @Inject
    EmployeeProvider employeeProvider;

    @Inject
    PaymentInfoProvider paymentInfoProvider;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        ArrayList<String> debug = new ArrayList<>();

        // test code

        debug.add("croissant");
//        paymentInfoProvider.addPaymentInfo(new CreatePaymentInfoDto(56, SalaryLevelEnum.ASSOCIATE, 82, 29));
//        bankInfoProvider.addBankInfo(new BankInfoDto(-1L, "SBt2138t", "BT", null));
        employeeProvider.createEmployee(new EmployeeDto(-1L, "nume1", "prenume1", -1L, 51L, GenderEnum.MALE, LocalDate.now(), "address1", "evreu", 35));
        debug.add("lalele?");
        // end test

        request.setAttribute("debugtxt", debug);
        request.getRequestDispatcher("/WEB-INF/pages/test.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}