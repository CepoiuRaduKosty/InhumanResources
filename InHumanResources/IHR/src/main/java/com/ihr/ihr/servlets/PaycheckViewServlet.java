package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.PaycheckDtos.PaycheckDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.interf.PaycheckProvider;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "PaycheckView", value = "/PaycheckView")
public class PaycheckViewServlet extends HttpServlet {
    @Inject
    PaycheckProvider paycheckProvider;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long paycheckId = Long.parseLong(request.getParameter("paycheckId"));

        PaycheckDto paycheckDto = paycheckProvider.getPaycheckById(paycheckId);

        request.setAttribute("paycheckDto", paycheckDto);

        request.setAttribute("salaryLevels", SalaryLevelEnum.values());

        request.getRequestDispatcher("/WEB-INF/pages/PaycheckView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}
