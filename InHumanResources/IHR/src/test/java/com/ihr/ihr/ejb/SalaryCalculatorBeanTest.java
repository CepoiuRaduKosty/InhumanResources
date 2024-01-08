package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BonusDtos.UpdateBonusDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.UpdateEmployeeDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.GenderEnum;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SalaryCalculatorBeanTest {

    @InjectMocks
    SalaryCalculatorBean salaryCalculatorBean;

    @Test
    public void testTaxRate() {
        double professorTaxRate = salaryCalculatorBean.TaxRate(SalaryLevelEnum.PROFESSOR);
        double lecturerTaxRate = salaryCalculatorBean.TaxRate(SalaryLevelEnum.LECTURER);
        double associateTaxRate = salaryCalculatorBean.TaxRate(SalaryLevelEnum.ASSOCIATE);
        double executiveTaxRate = salaryCalculatorBean.TaxRate(SalaryLevelEnum.EXECUTIVE);

        assertEquals(20d, professorTaxRate);
        assertEquals(25d, lecturerTaxRate);
        assertEquals(30d, associateTaxRate);
        assertEquals(0d, executiveTaxRate);
    }

    @Test
    public void testGetSalaryBeforeTaxes() {
        LocalDate dateOfBirth = LocalDate.of(1990, Month.JANUARY, 15);

        UpdateEmployeeDto updateEmployeeDto = new UpdateEmployeeDto(
                "John",
                "Doe",
                GenderEnum.MALE,
                dateOfBirth,
                "123 Main St, City",
                "Christian",
                35
        );

        PaymentInfoDto paymentInfoDto = new PaymentInfoDto(
                1L,
                5000.0,
                SalaryLevelEnum.LECTURER,
                1000.0,
                40,
                10
        );

        List<UpdateBonusDto> updateBonusDtoList = new ArrayList<>();
        updateBonusDtoList.add(new UpdateBonusDto(500.0, "Additional Bonus"));
        updateBonusDtoList.add(new UpdateBonusDto(300.0, "Performance Bonus"));

        SalaryCalculatorBean salaryCalculatorBean = new SalaryCalculatorBean();

        double salaryBeforeTaxes = salaryCalculatorBean.getSalaryBeforeTaxes(updateEmployeeDto, paymentInfoDto, updateBonusDtoList);

        double expectedSalary = (5000.0 * updateEmployeeDto.getHoursPerWeek()) / 40 + 500.0 + 300.0;

        assertEquals(expectedSalary, salaryBeforeTaxes);
    }

    @Test
    public void testGetSalaryAfterTaxes() {
        UpdateEmployeeDto updateEmployeeDto = new UpdateEmployeeDto(
                "Alice",
                "Smith",
                GenderEnum.FEMALE,
                LocalDate.of(1992, Month.DECEMBER, 25),
                "456 Elm St, Town",
                "Atheist",
                20
        );

        PaymentInfoDto paymentInfoDto = new PaymentInfoDto(
                2L,
                7000.0,
                SalaryLevelEnum.ASSOCIATE,
                1500.0,
                50,
                5
        );

        List<UpdateBonusDto> updateBonusDtoList = new ArrayList<>();
        updateBonusDtoList.add(new UpdateBonusDto(800.0, "Special Bonus"));
        updateBonusDtoList.add(new UpdateBonusDto(400.0, "Annual Bonus"));

        SalaryCalculatorBean salaryCalculatorBean = new SalaryCalculatorBean();

        double salaryAfterTaxes = salaryCalculatorBean.getSalaryAfterTaxes(updateEmployeeDto, paymentInfoDto, updateBonusDtoList);

        double salaryBeforeTaxes = salaryCalculatorBean.getSalaryBeforeTaxes(updateEmployeeDto, paymentInfoDto, updateBonusDtoList);
        double taxRate = salaryCalculatorBean.TaxRate(paymentInfoDto.getSalaryLevel()) / 100;
        final double socialCharge = 1/10d;
        double expectedSalary = salaryBeforeTaxes - (salaryBeforeTaxes * taxRate) - (salaryBeforeTaxes * socialCharge);

        assertEquals(expectedSalary, salaryAfterTaxes);
    }
}