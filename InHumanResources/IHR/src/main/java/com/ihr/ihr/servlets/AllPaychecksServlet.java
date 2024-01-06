package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.PaycheckDtos.PaycheckDto;
import com.ihr.ihr.common.interf.PaycheckProvider;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AllPaychecksServlet", value = "/AllPaychecks")
public class AllPaychecksServlet extends HttpServlet {

    @Inject
    PaycheckProvider paycheckProvider;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PaycheckDto> allPaychecks = paycheckProvider.getAllPaychecks();

        request.setAttribute("allPaychecks", allPaychecks);

        request.getRequestDispatcher("/WEB-INF/pages/AllPaychecks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}

