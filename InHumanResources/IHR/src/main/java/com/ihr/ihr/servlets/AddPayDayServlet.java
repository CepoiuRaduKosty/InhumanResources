package com.ihr.ihr.servlets;

import com.ihr.ihr.common.excep.InvalidPayDayException;
import com.ihr.ihr.common.excep.PayDayAlreadyExistsException;
import com.ihr.ihr.common.excep.PayDayDoesNotExistException;
import com.ihr.ihr.common.interf.PayDayProvider;
import com.ihr.ihr.common.interf.PayDayValidation;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AddPayDayServlet", value = "/AddPayDay")
public class AddPayDayServlet extends HttpServlet {
    @Inject
    PayDayProvider payDayProvider;
    @Inject
    PayDayValidation payDayValidation;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        if (payDayProvider.isPayDateSet())
            response.sendRedirect(request.getContextPath() + "/PayDay");
        else {
            Integer dayOfMonth = null;
            try {
                dayOfMonth = payDayProvider.getDayOfMonth();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            request.setAttribute("payDayOfMonth", dayOfMonth);
            request.getRequestDispatcher("/WEB-INF/pages/AddPayDay.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        Integer dayOfMonth = Integer.parseInt(request.getParameter("payDayOfMonth"));
        if (payDayValidation.isPayDayValid(dayOfMonth)) {
            try {
                payDayProvider.addPayDayOfMonth(dayOfMonth);
            } catch (InvalidPayDayException | PayDayAlreadyExistsException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect(request.getContextPath() + "/PayDay");
        }
    }
}