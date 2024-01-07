package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.PaycheckDtos.PaycheckDto;
import com.ihr.ihr.common.dtos.PaycheckDtos.PaycheckWithNamesDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.interf.EmployeeProvider;
import com.ihr.ihr.common.interf.PaycheckProvider;
import com.ihr.ihr.common.interf.PaymentInfoProvider;
import com.ihr.ihr.entities.Employee;
import com.ihr.ihr.entities.PaymentInfo;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.tags.shaded.org.apache.xalan.templates.ElemApplyImport;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "AllPaychecksServlet", value = "/AllPaychecks")
public class AllPaychecksServlet extends HttpServlet {

    @Inject
    PaycheckProvider paycheckProvider;

    @Inject
    PaymentInfoProvider paymentInfoProvider;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PaycheckDto> allPaychecks = paycheckProvider.getAllPaychecks();
        List<PaycheckWithNamesDto> paycheckWithNamesDtos = new ArrayList<>();

        for (PaycheckDto p : allPaychecks)
        {
            PaymentInfoDto paymentInfoDto = paymentInfoProvider.findPaymentInfoById(p.getPaymentId());
            String employeeName = paymentInfoDto.getEmployeeDto().getName();
            paycheckWithNamesDtos.add(new PaycheckWithNamesDto(p, employeeName));
        }

        paycheckWithNamesDtos.sort(Comparator.<PaycheckWithNamesDto, LocalDate>comparing(dto -> dto.getPaycheckDto().getDate())
                .reversed()
                .thenComparing(PaycheckWithNamesDto::getName));


        request.setAttribute("paycheckWithNames", paycheckWithNamesDtos);

        request.getRequestDispatcher("/WEB-INF/pages/AllPaychecks.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}

