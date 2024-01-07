package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.BankInfoDtos.BankInfoDto;
import com.ihr.ihr.common.dtos.BankInfoDtos.UpdateBankInfoDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.CreateEmployeeDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.UpdateEmployeeDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.CreatePaymentInfoDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.GenderEnum;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.excep.*;
import com.ihr.ihr.common.interf.BankInfoProvider;
import com.ihr.ihr.common.interf.EmployeeProvider;
import com.ihr.ihr.common.interf.PaymentInfoProvider;
import com.ihr.ihr.entities.Employee;
import com.ihr.ihr.entities.PaymentInfo;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.xml.bind.ValidationException;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "EditEmployeeServlet", value = "/EditEmployee")
public class EditEmployeeServlet extends HttpServlet {
    @Inject
    EmployeeProvider employeeProvider;
    @Inject
    BankInfoProvider bankInfoProvider;
    @Inject
    PaymentInfoProvider paymentInfoProvider;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        String id_link = null;
        id_link = request.getParameter("id_link");

        if (id_link == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        EmployeeDto employeeDto = employeeProvider.findById(Long.parseLong(id_link));

        if (employeeDto == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        PaymentInfoDto paymentInfoDto = paymentInfoProvider.findPaymentInfoById(employeeDto.getPaymentInfoDto().getId());
        BankInfoDto bankInfoDto = bankInfoProvider.getById(employeeDto.getBankInfoDto().getId());
        request.setAttribute("employee", employeeDto);
        request.setAttribute("paymentInfo", paymentInfoDto);
        request.setAttribute("bankInfo", bankInfoDto);

        request.setAttribute("employee_id", id_link);
        request.getRequestDispatcher("/WEB-INF/pages/EditEmployee.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        Long employee_id = Long.parseLong(request.getParameter("employee_id"));

        if ("save".equals(action)) {
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
            Integer cumulatedShares = employeeProvider.findById(employee_id).getPaymentInfoDto().getCumulatedShares();

            EmployeeDto employeeDto = new EmployeeDto(employee_id, name, surname, genderEnum, dateOfBirth, address, religion, hoursPerWeek);
            BankInfoDto bankInfoDto = new BankInfoDto(employeeProvider.findById(employee_id).getBankInfoDto().getId(), iBan, bankName);
            PaymentInfoDto paymentInfoDto = new PaymentInfoDto(employeeProvider.findById(employee_id).getPaymentInfoDto().getId(), monthlyBasicSalary, salaryLevel, bonusForSuccess, numberOfShares, cumulatedShares);

            try {
                employeeProvider.updateEmployeeById(employee_id, employeeDto);
            } catch (DateOfBirthException | WorkingHoursException | InvalidEmployeeDto e) {
                throw new RuntimeException(e);
            }

            try {
                bankInfoProvider.updateBankInfo(bankInfoDto.getId(), bankInfoDto);
            } catch (InvalidBankInfoException e) {
                throw new RuntimeException(e);
            }

            try {
                paymentInfoProvider.updatePaymentInfo(paymentInfoDto.getId(), paymentInfoDto);
            } catch (NonPositiveIncomeException | ValidationException | InvalidPaymentInfoException |
                     UnknownPaymentInfoException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect(request.getContextPath() + "/EmployeeDetails?id_link=" + employee_id);

        }
        else if ("delete".equals(action))
        {
            employeeProvider.deleteEmployeeById(employee_id);
            response.sendRedirect(request.getContextPath() + "/FindEmployee");


        } else {
            // Handle other cases or display an error
            response.getWriter().write("Unknown action");
        }


    }
}