package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.BonusDtos.BonusDto;
import com.ihr.ihr.common.dtos.BonusDtos.UpdateBonusDto;
import com.ihr.ihr.common.excep.InvalidBonusException;
import com.ihr.ihr.common.excep.UnknownBonusException;
import com.ihr.ihr.common.interf.BonusProvider;
import com.ihr.ihr.common.interf.BonusValidation;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@DeclareRoles({"EMPLOYEE", "ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"ADMIN"})})
@WebServlet(name = "EditBonus", value = "/EditBonus")
public class EditBonus extends HttpServlet {

    @Inject
    BonusProvider bonusProvider;

    @Inject
    BonusValidation bonusValidator;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bonusId = request.getParameter("bonusId");
        if(bonusId == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }


        BonusDto bonusDto = null;
        try {
            bonusDto = bonusProvider.getBonusDtoById(Long.parseLong(bonusId));
        } catch (UnknownBonusException e) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        request.setAttribute("bonusDto", bonusDto);
        request.getRequestDispatcher("/WEB-INF/pages/editBonus.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Double bonusValue;
        String bonusDescription;
        String bonusIdStr = request.getParameter("bonusId");

        try {
            bonusValue = Double.parseDouble(request.getParameter("value"));
            bonusDescription = request.getParameter("description");
        } catch (NumberFormatException | NullPointerException e) {
            request.setAttribute("error", "Invalid input. Please provide valid bonus value and description.");
            try {
                request.setAttribute("bonusDto", bonusProvider.getBonusDtoById(Long.parseLong(bonusIdStr)));
            } catch (UnknownBonusException ex) {
                throw new RuntimeException(ex);
            }
            request.getRequestDispatcher("/WEB-INF/pages/editBonus.jsp").forward(request, response);
            return;
        }

        boolean isValueValid = bonusValidator.isValueValid(bonusValue);
        boolean isDescriptionValid = bonusValidator.isDenumireBonusValid(bonusDescription);

        if (!isValueValid && !isDescriptionValid) {
            request.setAttribute("error", "Invalid bonus value and description. Value must be greater than 0 and description cannot be empty");
        } else if (!isValueValid) {
            request.setAttribute("error", "Invalid bonus value. Value must be greater than 0.");
        } else if (!isDescriptionValid) {
            request.setAttribute("error", "Invalid bonus description. Description cannot be empty.");
        }

        try {
            if (!isValueValid || !isDescriptionValid) {
                request.setAttribute("bonusDto", bonusProvider.getBonusDtoById(Long.parseLong(bonusIdStr)));
                request.getRequestDispatcher("/WEB-INF/pages/editBonus.jsp").forward(request, response);
            } else {
                UpdateBonusDto bonusDto = new UpdateBonusDto(
                        bonusValue,
                        bonusDescription
                );
                bonusProvider.updateBonusById(Long.parseLong(bonusIdStr), bonusDto);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

}