package com.ihr.ihr.servlets;

import com.ihr.ihr.common.interf.PayDayProvider;
import com.ihr.ihr.ejb.PayDayBean;
import com.ihr.ihr.entities.PayDay;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PayDayServlet", value = "/PayDay")
public class PayDayServlet extends HttpServlet {

    @Inject
    PayDayProvider payDayProvider;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {


        Integer dayOfMonth = payDayProvider.getDayOfMonth();
        request.setAttribute("payDayOfMonth", dayOfMonth);


        if (dayOfMonth != null) {

            request.getRequestDispatcher("/WEB-INF/pages/PayDayIsSet.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/pages/PayDayNotSet.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}