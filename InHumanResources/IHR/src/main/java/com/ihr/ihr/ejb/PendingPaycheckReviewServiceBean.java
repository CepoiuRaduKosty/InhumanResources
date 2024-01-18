package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BonusDtos.BonusDto;
import com.ihr.ihr.common.dtos.BonusDtos.UpdateBonusDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.PaycheckBonusDtos.CreatePaycheckBonusDto;
import com.ihr.ihr.common.dtos.PaycheckDtos.CreatePaycheckDto;
import com.ihr.ihr.common.dtos.PaycheckDtos.PaycheckDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.excep.InvalidPaycheckBonusException;
import com.ihr.ihr.common.excep.UnknownBonusException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;
import com.ihr.ihr.common.interf.*;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
public class PendingPaycheckReviewServiceBean implements PendingPaycheckReviewServiceProvider {
    @Inject
    BonusProvider bonusProvider;

    @Inject
    SalaryCalculatorProvider salaryCalculatorProvider;

    @Inject
    PayDayProvider payDayProvider;

    @Inject
    PaycheckProvider paycheckProvider;

    @Inject
    PaymentInfoProvider paymentInfoProvider;

    @Inject
    PaycheckBonusProvider paycheckBonusProvider;

    @Override
    public void populatePendingPaycheckReviewDetails(EmployeeDto employee, HttpServletRequest request) throws UnknownPaymentInfoException {
        request.setAttribute("employee", employee);

        SalaryLevelEnum salaryLevel = employee.getPaymentInfoDto().getSalaryLevel();
        request.setAttribute("basicSalary", employee.getPaymentInfoDto().getMonthlyBasicSalary());

        if (salaryLevel == SalaryLevelEnum.ASSOCIATE || salaryLevel == SalaryLevelEnum.EXECUTIVE) {
            Double bonus = employee.getPaymentInfoDto().getBonusForSuccess();
            request.setAttribute("successBonus", bonus);
        } else {
            request.setAttribute("successBonus", 0.0);
        }

        if (salaryLevel == SalaryLevelEnum.EXECUTIVE) {
            Integer shares = employee.getPaymentInfoDto().getNumberOfShares();
            request.setAttribute("sharesNumber", shares);
        } else {
            request.setAttribute("sharesNumber", 0);
        }

        if (salaryLevel == SalaryLevelEnum.PROFESSOR || LocalDate.now().getMonthValue() == 8) {
            Integer cumulatedShares = employee.getPaymentInfoDto().getCumulatedShares();
            request.setAttribute("cumulatedSharesNumber", cumulatedShares);
        } else {
            request.setAttribute("cumulatedSharesNumber", 0);
        }

        PaymentInfoDto paymentInfoDto = employee.getPaymentInfoDto();
        List<BonusDto> bonuses = bonusProvider.getBonusDtoByPaymentId(paymentInfoDto.getId());
        request.setAttribute("extraBonuses", bonuses);

        List<UpdateBonusDto> updateBonuses = bonuses.stream().map(bonus -> new UpdateBonusDto(bonus.getValue(), bonus.getBonusDescription())).collect(Collectors.toList());
        Double salaryBeforeTaxes = salaryCalculatorProvider.getSalaryBeforeTaxes(employee, paymentInfoDto, updateBonuses);
        request.setAttribute("salaryBeforeTax", salaryBeforeTaxes);

        Double salaryAfterTaxes = salaryCalculatorProvider.getSalaryAfterTaxes(employee, paymentInfoDto, updateBonuses);
        request.setAttribute("salaryAfterTax", salaryAfterTaxes);

        if (!payDayProvider.isPayDateSet()) {
            request.setAttribute("isPayDateSet", false);
        }

        if (payDayProvider.isPayDateSet() && payDayProvider.getDayOfMonth() != LocalDate.now().getDayOfMonth()) {
            request.setAttribute("isTodayPayDay", false);
        }
    }

    @Override
    public void processPendingPaycheckReview(EmployeeDto employeeDto, HttpServletRequest request) throws UnknownPaymentInfoException, InvalidPaycheckBonusException {
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

        CreatePaycheckDto createPaycheckDto = new CreatePaycheckDto(paymentInfoDto.getId(), LocalDate.now(), basicSalary, successBonus, sharesNumber, cumulatedSharesNumber, salaryBeforeTax, tax, socialCharge, salaryAfterTax);

        paycheckProvider.createPaycheck(createPaycheckDto);

        PaycheckDto paycheckDto = paycheckProvider.getLastPaycheckByPaymentInfoId(paymentInfoDto.getId());

        if (extraBonusesAsIds != null) {
            List<BonusDto> bonuses = Stream.of(extraBonusesAsIds).map(Long::parseLong).map(id -> {
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
    }
}
