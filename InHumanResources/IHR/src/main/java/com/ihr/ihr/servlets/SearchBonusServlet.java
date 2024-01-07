package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.BonusDtos.BonusEntryDto;
import com.ihr.ihr.common.excep.UnknownBonusException;
import com.ihr.ihr.common.interf.BonusProvider;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchBonusServlet", value = "/SearchBonus")
public class SearchBonusServlet extends HttpServlet {

    @Inject
    BonusProvider bonusProvider;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/SearchBonus.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeName = request.getParameter("employeeName");

        if (request.getParameter("action") != null && request.getParameter("action").equals("delete")) {
            handleDeleteRequest(request, response);
            return;
        }

        try {
            List<BonusEntryDto> bonusEntries = bonusProvider.searchBonusesByEmployeeName(employeeName);

            request.setAttribute("bonusEntries", bonusEntries);
            request.getRequestDispatcher("/WEB-INF/pages/SearchBonus.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred during the search. Please try again.");
            request.getRequestDispatcher("/WEB-INF/pages/SearchBonus.jsp").forward(request, response);
        }
    }

    private void handleDeleteRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long bonusId = Long.parseLong(request.getParameter("bonusId"));

        try {
            bonusProvider.removeBonusById(bonusId);
            response.sendRedirect(request.getContextPath() + "/SearchBonus");
            return;  // Add this line to exit the method after redirecting
        } catch (UnknownBonusException e) {
            request.setAttribute("error", "Error deleting bonus: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/pages/SearchBonus.jsp").forward(request, response);
        }
    }

}
