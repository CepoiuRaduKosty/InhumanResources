package com.ihr.ihr.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ExceptionServlet", value = "/Exception")
public class ExceptionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
        request.setAttribute("errorMessage", exception.getMessage());

        request.getRequestDispatcher("/errorPages/errorPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}