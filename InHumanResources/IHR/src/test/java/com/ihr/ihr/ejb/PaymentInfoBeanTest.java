package com.ihr.ihr.ejb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.ihr.ihr.common.dtos.PaymentInfoDtos.CreatePaymentInfoDto;
import com.ihr.ihr.common.dtos.PaymentInfoDtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.GenderEnum;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.excep.InvalidPaymentInfoException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;
import com.ihr.ihr.common.excep.NonPositiveIncomeException;
import com.ihr.ihr.entities.Employee;
import com.ihr.ihr.entities.PaymentInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentInfoBeanTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<PaymentInfo> typedQuery;

    @Mock
    private PaymentInfoValidatorBean paymentInfoValidatorBean;

    @InjectMocks
    private PaymentInfoBean paymentInfoBean;

    PaymentInfoDto sampleValidPaymentInfoDto;
    PaymentInfoDto sampleInvalidPaymentInfoDto;

    private PaymentInfo getPaymentInfoFromDto(PaymentInfoDto dto) {
        PaymentInfo paymentInfoToReturn = new PaymentInfo();
        paymentInfoToReturn.setId(dto.getId());
        paymentInfoToReturn.setEmployee(null);
        paymentInfoToReturn.setBonuses(new ArrayList<>());
        paymentInfoToReturn.setBonusForSuccess(dto.getBonusForSuccess());
        paymentInfoToReturn.setCumulatedShares(dto.getCumulatedShares());
        paymentInfoToReturn.setMonthlyBasicSalary(dto.getMonthlyBasicSalary());
        paymentInfoToReturn.setNumberOfShares(dto.getNumberOfShares());
        paymentInfoToReturn.setPaychecks(new ArrayList<>());
        paymentInfoToReturn.setSalaryLevel(dto.getSalaryLevel());
        return paymentInfoToReturn;
    }

    private void assertPaymentInfoDtosEqual(PaymentInfoDto dto1, PaymentInfoDto dto2) {
        assertEquals(dto1.getId(), dto2.getId());
        assertEquals(dto1.getBonusForSuccess(), dto2.getBonusForSuccess());
        assertEquals(dto1.getCumulatedShares(), dto2.getCumulatedShares());
        assertEquals(dto1.getSalaryLevel(), dto2.getSalaryLevel());
        assertEquals(dto1.getMonthlyBasicSalary(), dto2.getMonthlyBasicSalary());
        assertEquals(dto1.getNumberOfShares(), dto2.getNumberOfShares());
    }

    private void assertPaymentInfoEqualNoID(PaymentInfo paymentInfo1, PaymentInfo paymentInfo2) {
        assertEquals(paymentInfo1.getBonusForSuccess(), paymentInfo2.getBonusForSuccess());
        assertEquals(paymentInfo1.getCumulatedShares(), paymentInfo2.getCumulatedShares());
        assertEquals(paymentInfo1.getSalaryLevel(), paymentInfo2.getSalaryLevel());
        assertEquals(paymentInfo1.getMonthlyBasicSalary(), paymentInfo2.getMonthlyBasicSalary());
        assertEquals(paymentInfo1.getNumberOfShares(), paymentInfo2.getNumberOfShares());
    }

    private void assertPaymentInfoEqual(PaymentInfo paymentInfo1, PaymentInfo paymentInfo2) {
        assertEquals(paymentInfo1.getId(), paymentInfo2.getId());
        assertPaymentInfoEqualNoID(paymentInfo1, paymentInfo2);
    }

    @BeforeEach
    void setUp() {
        sampleValidPaymentInfoDto = new PaymentInfoDto(1L, 5000.0, SalaryLevelEnum.LECTURER, 200.0, 100, 0);
        sampleInvalidPaymentInfoDto = new PaymentInfoDto(2L, -5000.0, SalaryLevelEnum.EXECUTIVE, -200.0, -100, 33);
    }

    @Test
    void findPaymentInfoById_positive_idExists() {
        PaymentInfoDto expectedDto = sampleValidPaymentInfoDto;
        Long givenId = sampleValidPaymentInfoDto.getId();
        PaymentInfo mockPaymentInfo = getPaymentInfoFromDto(expectedDto);
        Employee mockEmployee = new Employee();
        mockEmployee.setId(1L);
        mockEmployee.setAddress("aaa");
        mockEmployee.setPaymentInfo(mockPaymentInfo);
        mockEmployee.setGender(GenderEnum.MALE);
        mockEmployee.setDateOfBirth(LocalDate.of(2002,2,2));
        mockEmployee.setHoursPerWeek(40);
        mockEmployee.setName("aaa");
        mockEmployee.setReligion("aaa");
        mockEmployee.setSurname("aaa");
        mockPaymentInfo.setEmployee(mockEmployee);
        when(typedQuery.getSingleResult()).thenReturn(mockPaymentInfo);
        when(entityManager.createQuery("SELECT p FROM PaymentInfo p WHERE p.id = :id", PaymentInfo.class))
                .thenReturn(typedQuery);


        PaymentInfoDto actualDto = paymentInfoBean.findPaymentInfoById(givenId);

        assertPaymentInfoDtosEqual(expectedDto, actualDto);
        verify(typedQuery).getSingleResult();
        verify(entityManager).createQuery("SELECT p FROM PaymentInfo p WHERE p.id = :id", PaymentInfo.class);
        verify(typedQuery).setParameter("id", givenId);
    }

    @Test
    void findPaymentInfoById_negative_idDoesNotExist() {
        Long givenId = 1L;
        when(typedQuery.getSingleResult()).thenReturn(null);
        when(entityManager.createQuery("SELECT p FROM PaymentInfo p WHERE p.id = :id", PaymentInfo.class))
                .thenReturn(typedQuery);

        PaymentInfoDto actualDto = paymentInfoBean.findPaymentInfoById(givenId);

        assertNull(actualDto);
        verify(typedQuery).getSingleResult();
        verify(entityManager).createQuery("SELECT p FROM PaymentInfo p WHERE p.id = :id", PaymentInfo.class);
        verify(typedQuery).setParameter("id", givenId);
    }

    @Test
    void addPaymentInfo_positive() throws InvalidPaymentInfoException {
        when(paymentInfoValidatorBean.isPaymentInfoDtoValid(any(PaymentInfoDto.class))).thenReturn(true);
        PaymentInfo expectedPayment = getPaymentInfoFromDto(sampleValidPaymentInfoDto);

        Long returnedId = paymentInfoBean.addPaymentInfo(new CreatePaymentInfoDto(sampleValidPaymentInfoDto));

        //assertEquals(expectedPayment.getId(), returnedId); -> cannot verify this because id is assigned by em.persist automatically
        ArgumentCaptor<PaymentInfo> captor = ArgumentCaptor.forClass(PaymentInfo.class);
        verify(entityManager).persist(captor.capture());
        assertPaymentInfoEqualNoID(expectedPayment, captor.getValue());
    }

    @Test
    void addPaymentInfo_negative_invalidDto() {
        assertThrows(InvalidPaymentInfoException.class, () -> paymentInfoBean.addPaymentInfo(new CreatePaymentInfoDto(sampleInvalidPaymentInfoDto)));
        verify(entityManager, never()).persist(any());
    }

    @Test
    void updatePaymentInfo_positive() throws InvalidPaymentInfoException, UnknownPaymentInfoException {
        when(paymentInfoValidatorBean.isPaymentInfoDtoValid(any(PaymentInfoDto.class))).thenReturn(true);
        PaymentInfo actualPaymentInfo = getPaymentInfoFromDto(sampleInvalidPaymentInfoDto);
        actualPaymentInfo.setId(1L);
        when(entityManager.find(PaymentInfo.class, sampleValidPaymentInfoDto.getId())).thenReturn(actualPaymentInfo);

        paymentInfoBean.updatePaymentInfo(sampleValidPaymentInfoDto.getId(), sampleValidPaymentInfoDto);

        assertPaymentInfoEqual(getPaymentInfoFromDto(sampleValidPaymentInfoDto), actualPaymentInfo);
    }

    @Test
    void updatePaymentInfo_negative_idNotFound() {
        Long nonExistingPaymentInfoId = 999L;
        when(paymentInfoValidatorBean.isPaymentInfoDtoValid(any(PaymentInfoDto.class))).thenReturn(true);
        when(entityManager.find(PaymentInfo.class, nonExistingPaymentInfoId)).thenReturn(null);

        assertThrows(UnknownPaymentInfoException.class, () -> paymentInfoBean.updatePaymentInfo(nonExistingPaymentInfoId, sampleValidPaymentInfoDto));
    }

    @Test
    void updatePaymentInfo_negative_dtoNotValid() {
        when(paymentInfoValidatorBean.isPaymentInfoDtoValid(any(PaymentInfoDto.class))).thenReturn(false);

        assertThrows(InvalidPaymentInfoException.class, () -> paymentInfoBean.updatePaymentInfo(1L, sampleInvalidPaymentInfoDto));
    }

    @Test
    void deletePaymentInfo_positive() throws UnknownPaymentInfoException {
        when(entityManager.find(PaymentInfo.class, sampleValidPaymentInfoDto.getId())).thenReturn(getPaymentInfoFromDto(sampleValidPaymentInfoDto));

        paymentInfoBean.deletePaymentInfo(sampleValidPaymentInfoDto.getId());

        ArgumentCaptor<PaymentInfo> captor = ArgumentCaptor.forClass(PaymentInfo.class);
        verify(entityManager).remove(captor.capture());
        assertPaymentInfoEqual(getPaymentInfoFromDto(sampleValidPaymentInfoDto), captor.getValue());
    }

    @Test
    void deletePaymentInfo_negative_idNotFound() {
        when(entityManager.find(PaymentInfo.class, sampleValidPaymentInfoDto.getId())).thenReturn(null);

        assertThrows(UnknownPaymentInfoException.class, () -> paymentInfoBean.deletePaymentInfo(sampleValidPaymentInfoDto.getId()));
        verify(entityManager, never()).remove(any());
    }

    @Test
    void incrementCumulatedSharesByNumberOfShares_positive() throws UnknownPaymentInfoException {
        PaymentInfo actualPayment = getPaymentInfoFromDto(sampleValidPaymentInfoDto);
        when(entityManager.find(PaymentInfo.class, sampleValidPaymentInfoDto.getId())).thenReturn(actualPayment);

        paymentInfoBean.incrementCumulatedSharesByNumberOfShares(sampleValidPaymentInfoDto.getId());

        assertEquals(sampleValidPaymentInfoDto.getCumulatedShares()+sampleValidPaymentInfoDto.getNumberOfShares(), actualPayment.getCumulatedShares());
    }

    @Test
    void incrementCumulatedSharesByNumberOfShares_negative() {
        when(entityManager.find(PaymentInfo.class, sampleValidPaymentInfoDto.getId())).thenReturn(null);

        assertThrows(UnknownPaymentInfoException.class, () -> paymentInfoBean.incrementCumulatedSharesByNumberOfShares(sampleValidPaymentInfoDto.getId()));
    }

    @Test
    void resetCumulatedShares_positive() throws UnknownPaymentInfoException {
        PaymentInfo actualPayment = getPaymentInfoFromDto(sampleValidPaymentInfoDto);
        actualPayment.setCumulatedShares(360);
        when(entityManager.find(PaymentInfo.class, sampleValidPaymentInfoDto.getId())).thenReturn(actualPayment);

        paymentInfoBean.resetCumulatedShares(sampleValidPaymentInfoDto.getId());

        assertEquals(0, actualPayment.getCumulatedShares());
    }

    @Test
    void resetCumulatedShares_negative() {
        when(entityManager.find(PaymentInfo.class, sampleValidPaymentInfoDto.getId())).thenReturn(null);

        assertThrows(UnknownPaymentInfoException.class, () -> paymentInfoBean.resetCumulatedShares(sampleValidPaymentInfoDto.getId()));
    }
}

