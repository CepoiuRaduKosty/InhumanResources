package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.EmployeeDto;
import com.ihr.ihr.common.dtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.interf.BankInfoProvider;
import com.ihr.ihr.common.interf.EmployeeProvider;

import com.ihr.ihr.common.interf.PaymentInfoProvider;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "TestServlet", value = "/test")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        ArrayList<String> debug = new ArrayList<>();

        // test code

        debug.add("croissant");

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