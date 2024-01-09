package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.BankInfoDtos.BankInfoDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.enums.GenderEnum;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.excep.*;
import com.ihr.ihr.common.interf.*;
import com.ihr.ihr.common.interf.mappers.UserCreationDtoMapping;
import com.ihr.ihr.common.interf.validators.UserValidation;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.xml.bind.ValidationException;

import java.io.IOException;
import java.time.LocalDate;

@DeclareRoles({"EMPLOYEE", "ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN", "EMPLOYEE"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"ADMIN", "EMPLOYEE"})})
@WebServlet(name = "EditEmployeeServlet", value = "/EditEmployee")
public class EditEmployeeServlet extends HttpServlet {
    @Inject
    EmployeeProvider employeeProvider;
    @Inject
    BankInfoProvider bankInfoProvider;
    @Inject
    PaymentInfoProvider paymentInfoProvider;

    @Inject
    UserCreationDtoMapping userCreationDtoMapping;

    @Inject
    EmployeeValidation employeeValidation;

    @Inject
    BankInfoValidation bankInfoValidation;

    @Inject
    PaymentInfoValidation paymentInfoValidation;

    @Inject
    UserValidation userValidation;

    @Inject
    UserProvider userProvider;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        String id_link = request.getParameter("id_link");

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

        if(request.isUserInRole("ADMIN")){
            request.setAttribute("isAdmin", true);
        } else {
            request.setAttribute("isAdmin", false);
        }

        request.getRequestDispatcher("/WEB-INF/pages/EditEmployee.jsp").forward(request, response);

    }

    private void checkPasswordFieldValidation(UserCreationDto userCreationDto) throws ServletException {
        String passwordFieldTxt = userCreationDto.getPassword();
        if((passwordFieldTxt.isEmpty() || passwordFieldTxt.isBlank()) && !userValidation.isUserCreationDtoValidNoPasswordCheck(userCreationDto))
            throw new ServletException("invalid user format"); // todo change this to show user-friendly errors
        if(!passwordFieldTxt.isEmpty() && !passwordFieldTxt.isBlank() && !userValidation.isUserCreationDtoValid(userCreationDto))
            throw new ServletException("invalid user format"); // todo change this to show user-friendly errors
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        Long employee_id = Long.parseLong(request.getParameter("employee_id"));

        if ("save".equals(action) && request.isUserInRole("EMPLOYEE")) {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            GenderEnum genderEnum = GenderEnum.valueOf(request.getParameter("gender"));
            LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
            String address = request.getParameter("address");
            String religion = request.getParameter("religion");
            Integer hoursPerWeek = Integer.parseInt(request.getParameter("hoursPerWeek"));

            String bankName = request.getParameter("bankName");
            String iBan = request.getParameter("iBan");

            PaymentInfoDto paymentInfoDto = null;
            if(request.isUserInRole("ADMIN")) {
                Double monthlyBasicSalary = Double.parseDouble(request.getParameter("monthlyBasicSalary"));
                SalaryLevelEnum salaryLevel = SalaryLevelEnum.valueOf(request.getParameter("salaryLevel"));
                Double bonusForSuccess = Double.parseDouble(request.getParameter("bonusForSuccess"));
                Integer numberOfShares = Integer.parseInt(request.getParameter("numberOfShares"));
                Integer cumulatedShares = employeeProvider.findById(employee_id).getPaymentInfoDto().getCumulatedShares();
                paymentInfoDto = new PaymentInfoDto(employeeProvider.findById(employee_id).getPaymentInfoDto().getId(), monthlyBasicSalary, salaryLevel, bonusForSuccess, numberOfShares, cumulatedShares);

                if(!paymentInfoValidation.isPaymentInfoDtoValid(paymentInfoDto)) {
                    throw new ServletException("invalid information"); // todo change this to show user-friendly errors
                }
            }



            EmployeeDto employeeDto = new EmployeeDto(employee_id, name, surname, genderEnum, dateOfBirth, address, religion, hoursPerWeek);
            BankInfoDto bankInfoDto = new BankInfoDto(employeeProvider.findById(employee_id).getBankInfoDto().getId(), iBan, bankName);


            UserCreationDto userCreationDto = userCreationDtoMapping.fromRequest(request);

            if(!employeeValidation.isEmployeeDataValid(employeeDto) || !bankInfoValidation.isBankInfoDtoValid(bankInfoDto))
                throw new ServletException("invalid information"); // todo change this to show user-friendly errors
            checkPasswordFieldValidation(userCreationDto);

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

            if(request.isUserInRole("ADMIN")) {
                try {
                    paymentInfoProvider.updatePaymentInfo(paymentInfoDto.getId(), paymentInfoDto);
                } catch (NonPositiveIncomeException | ValidationException | InvalidPaymentInfoException |
                         UnknownPaymentInfoException e) {
                    throw new RuntimeException(e);
                }
            }


            try {
                userProvider.updateUserById(employeeProvider.findById(employee_id).getUserDto().getId(), userCreationDto);
            } catch (InvalidUserException | UnknownUserException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect(request.getContextPath() + "/EmployeeDetails?id_link=" + employee_id);

        }
        else if ("delete".equals(action) && request.isUserInRole("ADMIN"))
        {
            employeeProvider.deleteEmployeeById(employee_id);
            response.sendRedirect(request.getContextPath() + "/FindEmployee");


        } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }


    }
}