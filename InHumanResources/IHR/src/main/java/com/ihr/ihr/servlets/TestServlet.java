package com.ihr.ihr.servlets;


import com.ihr.ihr.common.interf.PaycheckProvider;
import com.ihr.ihr.common.interf.PaymentInfoProvider;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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
        debug.add("asa se face debug");
        // end test

        request.setAttribute("debugtxt", debug);
        request.getRequestDispatcher("/WEB-INF/pages/test.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}