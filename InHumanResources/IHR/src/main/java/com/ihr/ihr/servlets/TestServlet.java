package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.*;
import com.ihr.ihr.common.enums.GenderEnum;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.interf.BankInfoProvider;
import com.ihr.ihr.common.interf.EmployeeProvider;

import com.ihr.ihr.common.interf.PaymentInfoProvider;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "TestServlet", value = "/test")
public class TestServlet extends HttpServlet {

    @Inject
    BankInfoProvider bankInfoProvider;

    @Inject
    EmployeeProvider employeeProvider;

    @Inject
    PaymentInfoProvider paymentInfoProvider;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        ArrayList<String> debug = new ArrayList<>();

        // test code

        debug.add("croissant");
//        CreatePaymentInfoDto createPaymentInfoDto = new CreatePaymentInfoDto(56, SalaryLevelEnum.ASSOCIATE, 82, 29);
//        CreateBankInfoDto bankInfoDto = new CreateBankInfoDto("SBt2138t", "BT");
//
//        Long paymentInfoId = paymentInfoProvider.addPaymentInfo(createPaymentInfoDto);
//        Long bankInfoId = bankInfoProvider.addBankInfo(bankInfoDto);
//        EmployeeDto employeeDto = new EmployeeDto(-1L, "nume1", "prenume1", GenderEnum.MALE, LocalDate.now(), "address1", "evreu", 35);
//
//        BankInfoDto bankInfoDto1 = new BankInfoDto(bankInfoId, bankInfoDto.getIBAN(), bankInfoDto.getBankName());
//        PaymentInfoDto paymentInfoDto = new PaymentInfoDto(paymentInfoId, createPaymentInfoDto.getMonthlyBasicSalary(),
//                createPaymentInfoDto.getSalaryLevel(), createPaymentInfoDto.getBonusForSuccess(),
//                createPaymentInfoDto.getNumberOfShares());
//
//        employeeDto.setPaymentInfoDto(paymentInfoDto);
//        employeeDto.setBankInfoDto(bankInfoDto1);



//        employeeProvider.createEmployee(employeeDto);
        employeeProvider.deleteEmployeeById(3L);
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