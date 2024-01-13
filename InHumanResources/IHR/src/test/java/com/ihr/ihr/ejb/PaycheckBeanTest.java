package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.PaycheckDtos.CreatePaycheckDto;
import com.ihr.ihr.common.dtos.PaycheckDtos.PaycheckDto;
import com.ihr.ihr.common.excep.UnknownPaycheckException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;
import com.ihr.ihr.entities.Employee;
import com.ihr.ihr.entities.Paycheck;
import com.ihr.ihr.entities.PaymentInfo;
import jakarta.ejb.EJBException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaycheckBeanTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Paycheck> typedQuery;

    @InjectMocks
    private PaycheckBean paycheckBean;

    @Test
    public void testGetPaycheckById_PositiveCase() throws UnknownPaycheckException {
        Long existingPaycheckId = 1L;

        Paycheck mockPaycheck = new Paycheck();
        mockPaycheck.setId(existingPaycheckId);
        mockPaycheck.setDate(LocalDate.now());
        mockPaycheck.setBasicSalary(2500.0);
        mockPaycheck.setBonusForSuccess(1000.0);
        mockPaycheck.setNumberOfShares(5);
        mockPaycheck.setCumulatedShares(15);
        mockPaycheck.setSalaryBeforeTaxes(3000.0);
        mockPaycheck.setTax(500.0);
        mockPaycheck.setSocialCharge(200.0);
        mockPaycheck.setFinalSalary(2300.0);
        PaymentInfo mockPaymentInfo = new PaymentInfo();
        mockPaymentInfo.setId(100L); // Assuming ID for PaymentInfo
        mockPaycheck.setPaymentInfo(mockPaymentInfo);

        when(entityManager.find(Paycheck.class, existingPaycheckId)).thenReturn(mockPaycheck);
        PaycheckDto paycheckDto = paycheckBean.getPaycheckById(existingPaycheckId);
        assertNotNull(paycheckDto, "Retrieved PaycheckDto should not be null");

        assertEquals(existingPaycheckId, paycheckDto.getId(), "IDs should match");
        assertEquals(mockPaycheck.getDate(), paycheckDto.getDate(), "Date should match");
        assertEquals(mockPaycheck.getBasicSalary(), paycheckDto.getBasicSalary(), "Basic salary should match");
        assertEquals(mockPaycheck.getBonusForSuccess(), paycheckDto.getBonusForSuccess(), "Bonus for success should match");
        assertEquals(mockPaycheck.getNumberOfShares(), paycheckDto.getNumberOfShares(), "Number of shares should match");
        assertEquals(mockPaycheck.getCumulatedShares(), paycheckDto.getCumulatedShares(), "Cumulated shares should match");
        assertEquals(mockPaycheck.getSalaryBeforeTaxes(), paycheckDto.getSalaryBeforeTaxes(), "Salary before taxes should match");
        assertEquals(mockPaycheck.getTax(), paycheckDto.getTax(), "Tax should match");
        assertEquals(mockPaycheck.getSocialCharge(), paycheckDto.getSocialCharge(), "Social charge should match");
        assertEquals(mockPaycheck.getFinalSalary(), paycheckDto.getFinalSalary(), "Final salary should match");
    }

    @Test
    public void testGetPaycheckById_PaycheckNotFound() {
        Long nonExistingPaycheckId = 999L;

        when(entityManager.find(Paycheck.class, nonExistingPaycheckId)).thenReturn(null);

        assertThrows(UnknownPaycheckException.class, () -> paycheckBean.getPaycheckById(nonExistingPaycheckId));
    }

    @Test
    public void testUpdatePaycheckById_PositiveCase() {
        Long existingPaycheckId = 1L;

        CreatePaycheckDto updatedPaycheckDto = new CreatePaycheckDto(
                100L,
                LocalDate.of(2024, 1, 6),
                3000.0,
                1500.0,
                100,
                50,
                2500.0,
                100.0,
                50.0,
                2350.0
        );
        PaymentInfo existingPaymentInfo = new PaymentInfo();
        existingPaymentInfo.setId(100L);
        existingPaymentInfo.setPaychecks(new ArrayList<>());

        Paycheck existingPaycheck = new Paycheck();
        existingPaycheck.setId(existingPaycheckId);
        existingPaycheck.setDate(LocalDate.now());
        existingPaycheck.setBasicSalary(2500.0);
        existingPaycheck.setBonusForSuccess(1500.0);
        existingPaycheck.setNumberOfShares(100);
        existingPaycheck.setCumulatedShares(50);
        existingPaycheck.setSalaryBeforeTaxes(2500.0);
        existingPaycheck.setTax(100.0);
        existingPaycheck.setSocialCharge(50.0);
        existingPaycheck.setFinalSalary(2350.0);
        existingPaycheck.setPaymentInfo(existingPaymentInfo);

        when(entityManager.find(Paycheck.class, existingPaycheckId)).thenReturn(existingPaycheck);
        when(entityManager.find(PaymentInfo.class, updatedPaycheckDto.getPaymentId())).thenReturn(existingPaymentInfo);

        paycheckBean.updatePaycheckById(existingPaycheckId, updatedPaycheckDto);

        assertEquals(
                updatedPaycheckDto.getDate(),
                existingPaycheck.getDate(),
                "Date should match after update"
        );
        assertEquals(
                updatedPaycheckDto.getBasicSalary(),
                existingPaycheck.getBasicSalary(),
                "Basic salary should match after update"
        );
        assertEquals(
                updatedPaycheckDto.getPaymentId(),
                existingPaycheck.getPaymentInfo().getId(),
                "PaymentInfo ID should match after update"
        );
        assertEquals(
                updatedPaycheckDto.getBonusForSuccess(),
                existingPaycheck.getBonusForSuccess(),
                "Bonus for success should match after update"
        );
        assertEquals(
                updatedPaycheckDto.getNumberOfShares(),
                existingPaycheck.getNumberOfShares(),
                "Number of shares should match after update"
        );
        assertEquals(
                updatedPaycheckDto.getCumulatedShares(),
                existingPaycheck.getCumulatedShares(),
                "Cumulated shares should match after update"
        );
        assertEquals(
                updatedPaycheckDto.getSalaryBeforeTaxes(),
                existingPaycheck.getSalaryBeforeTaxes(),
                "Salary before taxes should match after update"
        );
        assertEquals(
                updatedPaycheckDto.getTax(),
                existingPaycheck.getTax(),
                "Tax should match after update"
        );
        assertEquals(
                updatedPaycheckDto.getSocialCharge(),
                existingPaycheck.getSocialCharge(),
                "Social charge should match after update"
        );
        assertEquals(
                updatedPaycheckDto.getFinalSalary(),
                existingPaycheck.getFinalSalary(),
                "Final salary should match after update"
        );
    }

    @Test
    void testUpdatePaycheckById_invalidDto() {
        CreatePaycheckDto invalidPaycheckDto = new CreatePaycheckDto(
                null,
                null,
                -1000.0,
                -500.0,
                -100,
                -50,
                -2000.0,
                -100.0,
                -150.0,
                -3000.0
        );

        when(entityManager.find(eq(Paycheck.class), anyLong())).thenReturn(mock(Paycheck.class));

        assertThrows(EJBException.class, () -> paycheckBean.updatePaycheckById(1L, invalidPaycheckDto));

        verify(entityManager, never()).merge(any());
    }

    @Test
    void testCreatePaycheck_PositiveCase() throws UnknownPaymentInfoException {
        CreatePaycheckDto validPaycheckDto = new CreatePaycheckDto(
                1L,
                LocalDate.now(),
                5000.0,
                1000.0,
                10,
                5,
                4500.0,
                500.0,
                300.0,
                4200.0
        );

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setPaychecks(new ArrayList<>());

        when(entityManager.find(eq(PaymentInfo.class), anyLong())).thenReturn(paymentInfo);
        ArgumentCaptor<Paycheck> paycheckCaptor = ArgumentCaptor.forClass(Paycheck.class);

        paycheckBean.createPaycheck(validPaycheckDto);

        verify(entityManager, times(1)).persist(paycheckCaptor.capture());

        Paycheck persistedPaycheck = paycheckCaptor.getValue();
        assertNotNull(persistedPaycheck);

        assertEquals(validPaycheckDto.getDate(), persistedPaycheck.getDate());
        assertEquals(validPaycheckDto.getBasicSalary(), persistedPaycheck.getBasicSalary());
        assertEquals(validPaycheckDto.getBonusForSuccess(), persistedPaycheck.getBonusForSuccess());
        assertEquals(validPaycheckDto.getNumberOfShares(), persistedPaycheck.getNumberOfShares());
        assertEquals(validPaycheckDto.getCumulatedShares(), persistedPaycheck.getCumulatedShares());
        assertEquals(validPaycheckDto.getSalaryBeforeTaxes(), persistedPaycheck.getSalaryBeforeTaxes());
        assertEquals(validPaycheckDto.getTax(), persistedPaycheck.getTax());
        assertEquals(validPaycheckDto.getSocialCharge(), persistedPaycheck.getSocialCharge());
        assertEquals(validPaycheckDto.getFinalSalary(), persistedPaycheck.getFinalSalary());
    }

    @Test
    void testCreatePaycheck_InvalidPaymentId() {
        CreatePaycheckDto invalidPaycheckDto = new CreatePaycheckDto(
                999L,
                LocalDate.now(),
                5000.0,
                1000.0,
                10,
                5,
                4500.0,
                500.0,
                300.0,
                4200.0
        );

        when(entityManager.find(eq(PaymentInfo.class), anyLong())).thenReturn(null);

        assertThrows(UnknownPaymentInfoException.class, () -> paycheckBean.createPaycheck(invalidPaycheckDto));

        verify(entityManager, never()).persist(any());
    }

    @Test
    void testDeletePaycheckById_PositiveCase() throws UnknownPaycheckException {
        Long validPaycheckId = 1L;

        Paycheck mockedPaycheck = new Paycheck();
        PaymentInfo mockedPaymentInfo = new PaymentInfo();
        mockedPaycheck.setPaymentInfo(mockedPaymentInfo);
        mockedPaymentInfo.setPaychecks(new ArrayList<>());
        when(entityManager.find(eq(Paycheck.class), eq(validPaycheckId))).thenReturn(mockedPaycheck);

        paycheckBean.deletePaycheckById(validPaycheckId);

        verify(entityManager, times(1)).remove(mockedPaycheck);
    }

    @Test
    void testDeletePaycheckById_NonExistingPaycheck() {
        Long invalidPaycheckId = 999L;

        when(entityManager.find(eq(Paycheck.class), eq(invalidPaycheckId))).thenReturn(null);

        assertThrows(UnknownPaycheckException.class, () -> paycheckBean.deletePaycheckById(invalidPaycheckId));

        verify(entityManager, never()).remove(any());
    }

    List<Paycheck> getMockedPaychecksList() {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setId(99L);

        Paycheck paycheck1 = new Paycheck();
        paycheck1.setId(1L);
        paycheck1.setDate(LocalDate.of(2023, 1, 15));
        paycheck1.setBasicSalary(5000.0);
        paycheck1.setBonusForSuccess(1000.0);
        paycheck1.setNumberOfShares(10);
        paycheck1.setCumulatedShares(100);
        paycheck1.setSalaryBeforeTaxes(6000.0);
        paycheck1.setTax(1000.0);
        paycheck1.setSocialCharge(500.0);
        paycheck1.setFinalSalary(4500.0);
        paycheck1.setPaymentInfo(paymentInfo);

        Paycheck paycheck2 = new Paycheck();
        paycheck2.setId(2L);
        paycheck2.setDate(LocalDate.of(2023, 2, 28));
        paycheck2.setBasicSalary(5500.0);
        paycheck2.setBonusForSuccess(1200.0);
        paycheck2.setNumberOfShares(12);
        paycheck2.setCumulatedShares(120);
        paycheck2.setSalaryBeforeTaxes(6700.0);
        paycheck2.setTax(1200.0);
        paycheck2.setSocialCharge(600.0);
        paycheck2.setFinalSalary(5100.0);
        paycheck2.setPaymentInfo(paymentInfo);

        return Arrays.asList(paycheck1, paycheck2);
    }

    @Test
    void testGetAllPaychecks_PositiveCase() {
        List<Paycheck> mockedPaychecks = getMockedPaychecksList();

        when(entityManager.createQuery("SELECT p FROM Paycheck p", Paycheck.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(mockedPaychecks);

        List<PaycheckDto> result = paycheckBean.getAllPaychecks();

        verify(entityManager).createQuery("SELECT p FROM Paycheck p", Paycheck.class);
        verify(typedQuery).getResultList();

        assertEquals(mockedPaychecks.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            assertEquals(mockedPaychecks.get(i).getId(), result.get(i).getId());
            assertEquals(mockedPaychecks.get(i).getDate(), result.get(i).getDate());
            assertEquals(mockedPaychecks.get(i).getBasicSalary(), result.get(i).getBasicSalary());
            assertEquals(mockedPaychecks.get(i).getBonusForSuccess(), result.get(i).getBonusForSuccess());
            assertEquals(mockedPaychecks.get(i).getNumberOfShares(), result.get(i).getNumberOfShares());
            assertEquals(mockedPaychecks.get(i).getCumulatedShares(), result.get(i).getCumulatedShares());
            assertEquals(mockedPaychecks.get(i).getSalaryBeforeTaxes(), result.get(i).getSalaryBeforeTaxes());
            assertEquals(mockedPaychecks.get(i).getTax(), result.get(i).getTax());
            assertEquals(mockedPaychecks.get(i).getSocialCharge(), result.get(i).getSocialCharge());
            assertEquals(mockedPaychecks.get(i).getFinalSalary(), result.get(i).getFinalSalary());
        }

    }

    @Test
    void testGetAllPaychecksByPaymentInfoId_PositiveCase() {
        long paymentInfoId = 123L;

        List<Paycheck> mockedPaychecks = getMockedPaychecksList();

        when(entityManager.createQuery(
                "SELECT p FROM Paycheck p WHERE p.paymentInfo.id = :id", Paycheck.class))
                .thenReturn(typedQuery);
        when(typedQuery.setParameter("id", paymentInfoId)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(mockedPaychecks);

        List<PaycheckDto> result = paycheckBean.getAllPaychecksByPaymentInfoId(paymentInfoId);

        verify(entityManager).createQuery(
                "SELECT p FROM Paycheck p WHERE p.paymentInfo.id = :id", Paycheck.class);
        verify(typedQuery).setParameter("id", paymentInfoId);
        verify(typedQuery).getResultList();

        assertEquals(mockedPaychecks.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            assertEquals(mockedPaychecks.get(i).getId(), result.get(i).getId());
            assertEquals(mockedPaychecks.get(i).getDate(), result.get(i).getDate());
            assertEquals(mockedPaychecks.get(i).getBasicSalary(), result.get(i).getBasicSalary());
            assertEquals(mockedPaychecks.get(i).getBonusForSuccess(), result.get(i).getBonusForSuccess());
            assertEquals(mockedPaychecks.get(i).getNumberOfShares(), result.get(i).getNumberOfShares());
            assertEquals(mockedPaychecks.get(i).getCumulatedShares(), result.get(i).getCumulatedShares());
            assertEquals(mockedPaychecks.get(i).getSalaryBeforeTaxes(), result.get(i).getSalaryBeforeTaxes());
            assertEquals(mockedPaychecks.get(i).getTax(), result.get(i).getTax());
            assertEquals(mockedPaychecks.get(i).getSocialCharge(), result.get(i).getSocialCharge());
            assertEquals(mockedPaychecks.get(i).getFinalSalary(), result.get(i).getFinalSalary());
        }
    }

    @Test
    void testGetAllPaychecksByPaymentInfoId_NegativeCase() {
        long nonExistentPaymentInfoId = 999L;

        when(entityManager.createQuery(
                "SELECT p FROM Paycheck p WHERE p.paymentInfo.id = :id", Paycheck.class))
                .thenReturn(typedQuery);
        when(typedQuery.setParameter("id", nonExistentPaymentInfoId)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Collections.emptyList());

        List<PaycheckDto> result = paycheckBean.getAllPaychecksByPaymentInfoId(nonExistentPaymentInfoId);

        verify(entityManager).createQuery(
                "SELECT p FROM Paycheck p WHERE p.paymentInfo.id = :id", Paycheck.class);
        verify(typedQuery).setParameter("id", nonExistentPaymentInfoId);
        verify(typedQuery).getResultList();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetAllPaychecksByMonth_PositiveCase() {
        int month = 1;


        List<Paycheck> paychecksInMonth = getMockedPaychecksList();

        when(entityManager.createQuery(
                "SELECT p FROM Paycheck p WHERE FUNCTION('MONTH', p.date) = :month", Paycheck.class))
                .thenReturn(typedQuery);
        when(typedQuery.setParameter("month", month)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(paychecksInMonth);

        List<PaycheckDto> result = paycheckBean.getAllPaychecksByMonth(month);

        assertNotNull(result);
        assertEquals(paychecksInMonth.size(), result.size());

        for (int i = 0; i < result.size(); i++) {
            assertEquals(paychecksInMonth.get(i).getId(), result.get(i).getId());
            assertEquals(paychecksInMonth.get(i).getDate(), result.get(i).getDate());
            assertEquals(paychecksInMonth.get(i).getBasicSalary(), result.get(i).getBasicSalary());
            assertEquals(paychecksInMonth.get(i).getBonusForSuccess(), result.get(i).getBonusForSuccess());
            assertEquals(paychecksInMonth.get(i).getNumberOfShares(), result.get(i).getNumberOfShares());
            assertEquals(paychecksInMonth.get(i).getCumulatedShares(), result.get(i).getCumulatedShares());
            assertEquals(paychecksInMonth.get(i).getSalaryBeforeTaxes(), result.get(i).getSalaryBeforeTaxes());
            assertEquals(paychecksInMonth.get(i).getTax(), result.get(i).getTax());
            assertEquals(paychecksInMonth.get(i).getSocialCharge(), result.get(i).getSocialCharge());
            assertEquals(paychecksInMonth.get(i).getFinalSalary(), result.get(i).getFinalSalary());
        }
    }

    @Test
    public void testGetAllPaychecksByMonth_NegativeCase_NoResults() {
        int month = 12;

        when(entityManager.createQuery(
                "SELECT p FROM Paycheck p WHERE FUNCTION('MONTH', p.date) = :month", Paycheck.class))
                .thenReturn(typedQuery);
        when(typedQuery.setParameter("month", month)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Collections.emptyList());

        List<PaycheckDto> result = paycheckBean.getAllPaychecksByMonth(month);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetEmployeeByPaycheck_PositiveCase() throws UnknownPaycheckException {
        Long paycheckId = 1L;

        Paycheck paycheck = mock(Paycheck.class);
        when(entityManager.find(Paycheck.class, paycheckId)).thenReturn(paycheck);

        PaymentInfo paymentInfo = mock(PaymentInfo.class);
        when(paycheck.getPaymentInfo()).thenReturn(paymentInfo);

        Employee employee = mock(Employee.class);
        when(paymentInfo.getEmployee()).thenReturn(employee);

        when(employee.getId()).thenReturn(123L);
        when(employee.getName()).thenReturn("John");

        EmployeeDto employeeDto = paycheckBean.getEmployeeByPaycheck(paycheckId);

        assertNotNull(employeeDto);
        assertEquals(123L, employeeDto.getId().longValue());
        assertEquals("John", employeeDto.getName());
    }
}