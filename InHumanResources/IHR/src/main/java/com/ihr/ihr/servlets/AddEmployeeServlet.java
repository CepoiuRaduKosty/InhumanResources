package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.BankInfoDtos.BankInfoDto;
import com.ihr.ihr.common.dtos.BankInfoDtos.CreateBankInfoDto;
import com.ihr.ihr.common.dtos.BonusDtos.BonusDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.CreateEmployeeDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.CreatePaymentInfoDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.GenderEnum;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.excep.*;
import com.ihr.ihr.common.interf.*;
import com.ihr.ihr.entities.Employee;
import com.ihr.ihr.entities.PaymentInfo;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.xml.bind.ValidationException;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "AddEmployeeServlet", value = "/AddEmployee")
public class AddEmployeeServlet extends HttpServlet {
    @Inject
    EmployeeProvider employeeProvider;
    @Inject
    BankInfoProvider bankInfoProvider;
    @Inject
    PaymentInfoProvider paymentInfoProvider;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/AddEmployee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        GenderEnum genderEnum = GenderEnum.valueOf(request.getParameter("gender"));
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        String address = request.getParameter("address");
        String religion = request.getParameter("religion");
        Integer hoursPerWeek = Integer.parseInt(request.getParameter("hoursPerWeek"));

        String bankName = request.getParameter("bankName");
        String iBan = request.getParameter("iBan");

        Double monthlyBasicSalary = Double.parseDouble(request.getParameter("monthlyBasicSalary"));
        SalaryLevelEnum salaryLevel = SalaryLevelEnum.valueOf(request.getParameter("salaryLevel"));
        Double bonusForSuccess = Double.parseDouble(request.getParameter("bonusForSuccess"));
        Integer numberOfShares = Integer.parseInt(request.getParameter("numberOfShares"));

        Long id = 0L;
        BankInfoDto bankInfoDto = new BankInfoDto(1L, iBan, bankName);
        PaymentInfoDto paymentInfoDto = new PaymentInfoDto(1L, monthlyBasicSalary, salaryLevel, bonusForSuccess, numberOfShares, 0);
        EmployeeDto employeeDto = new EmployeeDto(1L, name, surname, genderEnum, dateOfBirth, address, religion, hoursPerWeek);
        employeeDto.setBankInfoDto(bankInfoDto);
        employeeDto.setPaymentInfoDto(paymentInfoDto);

        try {
            id = bankInfoProvider.addBankInfo(bankInfoDto);
        } catch (InvalidBankInfoException e) {
            throw new RuntimeException(e);
        }
        bankInfoDto.setId(id);

        try {
            id = paymentInfoProvider.addPaymentInfo(new CreatePaymentInfoDto(paymentInfoDto));
        } catch (NonPositiveIncomeException | ValidationException | InvalidPaymentInfoException e) {
            throw new RuntimeException(e);
        }
        paymentInfoDto.setId(id);

        try {
            id = employeeProvider.createEmployee(employeeDto);
        } catch (WorkingHoursException | DateOfBirthException | UnknownBankInfoException | UnknownPaymentInfoException |
                 InvalidEmployeeDto e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(request.getContextPath() + "/EmployeeDetails?id_link=" + id);
    }
}