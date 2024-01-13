package com.ihr.ihr.servlets;

import com.ihr.ihr.common.dtos.BonusDtos.BonusDto;
import com.ihr.ihr.common.dtos.BonusDtos.CreateBonusDto;
import com.ihr.ihr.common.dtos.BonusDtos.UpdateBonusDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.PaycheckBonusDtos.CreatePaycheckBonusDto;
import com.ihr.ihr.common.dtos.PaycheckBonusDtos.PaycheckBonusDto;
import com.ihr.ihr.common.dtos.PaycheckDtos.CreatePaycheckDto;
import com.ihr.ihr.common.dtos.PaycheckDtos.PaycheckDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.excep.InvalidPaycheckBonusException;
import com.ihr.ihr.common.excep.UnknownBonusException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;
import com.ihr.ihr.common.interf.*;
import com.ihr.ihr.entities.Paycheck;
import com.ihr.ihr.entities.PaycheckBonus;
import jakarta.annotation.security.DeclareRoles;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DeclareRoles({"EMPLOYEE", "ADMIN"})
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN"}),
        httpMethodConstraints = {@HttpMethodConstraint(value = "POST", rolesAllowed = {"ADMIN"})})
@WebServlet(name = "PendingPaycheckReviewServlet", value = "/PendingPaycheckReview")
public class PendingPaycheckReviewServlet extends HttpServlet {

    @Inject
    EmployeeProvider employeeProvider;

    @Inject
    PaycheckProvider paycheckProvider;

    @Inject
    PaymentInfoProvider paymentInfoProvider;

    @Inject
    PayDayProvider payDayProvider;

    @Inject
    SalaryCalculatorProvider salaryCalculatorProvider;

    @Inject
    BonusProvider bonusProvider;

    @Inject
    PaycheckBonusProvider paycheckBonusProvider;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeId = request.getParameter("employeeId");
        Long employeeIdLong = Long.parseLong(employeeId);
        EmployeeDto employee = employeeProvider.findById(employeeIdLong);

        if(employee == null || employeeId.isEmpty()) {
            request.setAttribute("error", "invalidId");
            request.getRequestDispatcher("/WEB-INF/pages/pendingPaycheckReview.jsp").forward(request, response);
        }
        else{
            request.setAttribute("employee", employee);

            SalaryLevelEnum salaryLevel = employee.getPaymentInfoDto().getSalaryLevel();

            request.setAttribute("basicSalary", employee.getPaymentInfoDto().getMonthlyBasicSalary());
            if(salaryLevel == SalaryLevelEnum.ASSOCIATE || salaryLevel == SalaryLevelEnum.EXECUTIVE){
                Double bonus = employee.getPaymentInfoDto().getBonusForSuccess();
                request.setAttribute("successBonus", bonus);
            }
            else{
                request.setAttribute("successBonus", 0.0);
            }

            if(salaryLevel == SalaryLevelEnum.EXECUTIVE){
                Integer shares = employee.getPaymentInfoDto().getNumberOfShares();
                request.setAttribute("sharesNumber", shares);
            }
            else{
                request.setAttribute("sharesNumber", 0);
            }

            if(salaryLevel == SalaryLevelEnum.PROFESSOR || LocalDate.now().getMonthValue() == 8){
                Integer cumulatedShares = employee.getPaymentInfoDto().getCumulatedShares();
                request.setAttribute("cumulatedSharesNumber", cumulatedShares);
            }
            else{
                request.setAttribute("cumulatedSharesNumber", 0);
            }

            PaymentInfoDto paymentInfoDto = employee.getPaymentInfoDto();
            try {
                List<BonusDto> bonuses = bonusProvider.getBonusDtoByPaymentId(paymentInfoDto.getId());;
                request.setAttribute("extraBonuses", bonuses);

                List<UpdateBonusDto> updateBonuses = bonuses.stream().map(bonus -> new UpdateBonusDto(bonus.getValue(), bonus.getBonusDescription()))
                        .collect(Collectors.toList());

                Double salaryBeforeTaxes = salaryCalculatorProvider.getSalaryBeforeTaxes(employee, paymentInfoDto, updateBonuses);

                request.setAttribute("salaryBeforeTax", salaryBeforeTaxes);

                Double salaryAfterTaxes = salaryCalculatorProvider.getSalaryAfterTaxes(employee, paymentInfoDto, updateBonuses);

                request.setAttribute("salaryAfterTax", salaryAfterTaxes);

            } catch (UnknownPaymentInfoException e) {
                throw new RuntimeException(e);
            }

            if(!payDayProvider.isPayDateSet()){
                request.setAttribute("isPayDateSet", false);
            }

            request.getRequestDispatcher("/WEB-INF/pages/pendingPaycheckReview.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        try {
            Long employeeId = Long.parseLong(request.getParameter("employeeId"));
            EmployeeDto employeeDto = employeeProvider.findById(employeeId);

            PaymentInfoDto paymentInfoDto = paymentInfoProvider.findPaymentInfoById(employeeDto.getPaymentInfoDto().getId());
            Double basicSalary = Double.parseDouble(request.getParameter("basicSalary"));
            Double successBonus = Double.parseDouble(request.getParameter("successBonus"));
            Integer sharesNumber = Integer.parseInt(request.getParameter("sharesNumber"));
            Integer cumulatedSharesNumber = Integer.parseInt(request.getParameter("cumulatedSharesNumber"));
            Double salaryBeforeTax = Double.parseDouble(request.getParameter("salaryBeforeTax"));
            Double salaryAfterTax = Double.parseDouble(request.getParameter("salaryAfterTax"));
            Double tax = salaryCalculatorProvider.TaxRate(paymentInfoDto.getSalaryLevel());
            Double socialCharge = 10d;
            String[] extraBonusesAsIds = request.getParameterValues("extraBonusIds");

            CreatePaycheckDto createPaycheckDto = new CreatePaycheckDto(paymentInfoDto.getId(),
                    LocalDate.now(),
                    basicSalary,
                    successBonus,
                    sharesNumber,
                    cumulatedSharesNumber,
                    salaryBeforeTax,
                    tax,
                    socialCharge,
                    salaryAfterTax);


            paycheckProvider.createPaycheck(createPaycheckDto);

            PaycheckDto paycheckDto = paycheckProvider.getLastPaycheckByPaymentInfoId(paymentInfoDto.getId());

            if (extraBonusesAsIds != null) {
                List<BonusDto> bonuses = Stream.of(extraBonusesAsIds)
                        .map(Long::parseLong)
                        .map(id -> {
                            try {
                                return bonusProvider.getBonusDtoById(id);
                            } catch (UnknownBonusException e) {
                                throw new RuntimeException(e);
                            }
                        }).collect(Collectors.toList());

                for (BonusDto bonus : bonuses) {
                    CreatePaycheckBonusDto createPaycheckBonusDto = new CreatePaycheckBonusDto(paycheckDto.getId(), bonus.getValue(), bonus.getBonusDescription());
                    paycheckBonusProvider.createPaycheckBonus(createPaycheckBonusDto);
                }
            }

            response.sendRedirect(request.getContextPath() + "/PendingPaycheckReview?employeeId=" + employeeId);

        } catch (UnknownPaymentInfoException | InvalidPaycheckBonusException e) {
            throw new RuntimeException(e);
        }
    }
}
