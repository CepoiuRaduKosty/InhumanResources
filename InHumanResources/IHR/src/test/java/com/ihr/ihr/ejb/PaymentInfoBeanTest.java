package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.CreatePaymentInfoDto;
import com.ihr.ihr.common.dtos.PaymentInfoDto;
import com.ihr.ihr.common.enums.SalaryLevelEnum;
import com.ihr.ihr.common.excep.InvalidPaymentInfoException;
import com.ihr.ihr.entities.PaymentInfo;
import jakarta.ejb.EJBException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentInfoBeanTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<PaymentInfo> typedQuery;

    @InjectMocks
    private PaymentInfoBean paymentInfoBean;

    PaymentInfoDto sampleValidPaymentInfoDto;

    private PaymentInfo getPaymentInfoFromDto(PaymentInfoDto dto) {
        PaymentInfo paymentInfoToReturn = new PaymentInfo();
        paymentInfoToReturn.setId(dto.getId());
        paymentInfoToReturn.setEmployee(null);
        //paymentInfoToReturn.setBonuses();

        //todo this func

        return paymentInfoToReturn;
    }

    @BeforeEach
    void setUp() {
        sampleValidPaymentInfoDto = new PaymentInfoDto(1L, 5000.0, SalaryLevelEnum.LECTURER, 200.0, 100, 0);

        when(entityManager.createQuery("SELECT p FROM PaymentInfo p WHERE p.id = :id", PaymentInfo.class))
                .thenReturn(typedQuery);
    }

    @Test
    void findPaymentInfoById_positive_idExists() {
        PaymentInfoDto expectedDto = sampleValidPaymentInfoDto;
        Long givenId = sampleValidPaymentInfoDto.getId();
         //todo return entity correspond to expectedDto when getSingleResult of query; get entity using method

        // todo build the execution

        // todo build the asserts, incl asserting query good
    }

    // todo correct this test
    @Test
    void findPaymentInfoById_negative_idDoesNotExist() {
        TypedQuery<PaymentInfo> typedQuery = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT p FROM PaymentInfo p WHERE p.id = :id", PaymentInfo.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("id"), anyLong())).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenThrow(EJBException.class);

        assertThrows(EJBException.class, () -> paymentInfoBean.findPaymentInfoById(999L));
    }

    // todo correct this test
    @Test
    void addPaymentInfo_positive() throws InvalidPaymentInfoException {
        CreatePaymentInfoDto samplePaymentInfoDto = new CreatePaymentInfoDto(5000.0, SalaryLevelEnum.LECTURER, 200.0, 100, 0);

        paymentInfoBean.addPaymentInfo(samplePaymentInfoDto);

        verify(entityManager).persist(any(PaymentInfo.class));
    }

    // todo correct this test
    @Test
    void addPaymentInfo_negative_null() {
        assertThrows(NullPointerException.class, () -> paymentInfoBean.addPaymentInfo(null));

        // Optionally, you can add additional assertions to ensure specific behavior after the exception is thrown
    }

    // todo correct this test
    @Test
    void updatePaymentInfo_positive() throws InvalidPaymentInfoException {
        Long paymentInfoIdToUpdate = 1L;
        PaymentInfoDto updatedPaymentInfoDto = new PaymentInfoDto(paymentInfoIdToUpdate, 6000.0, SalaryLevelEnum.LECTURER, 300.0, 150, 0);

        PaymentInfo existingPaymentInfo = new PaymentInfo();
        existingPaymentInfo.setId(paymentInfoIdToUpdate);
        existingPaymentInfo.setMonthlyBasicSalary(5000.0);
        existingPaymentInfo.setSalaryLevel(SalaryLevelEnum.ASSOCIATE);
        existingPaymentInfo.setBonusForSuccess(200.0);
        existingPaymentInfo.setNumberOfShares(100);

        when(entityManager.find(PaymentInfo.class, paymentInfoIdToUpdate)).thenReturn(existingPaymentInfo);

        paymentInfoBean.updatePaymentInfo(paymentInfoIdToUpdate, updatedPaymentInfoDto);

        verify(entityManager).find(PaymentInfo.class, paymentInfoIdToUpdate);

        assertEquals(updatedPaymentInfoDto.getMonthlyBasicSalary(), existingPaymentInfo.getMonthlyBasicSalary());
        assertEquals(updatedPaymentInfoDto.getSalaryLevel(), existingPaymentInfo.getSalaryLevel());
        assertEquals(updatedPaymentInfoDto.getBonusForSuccess(), existingPaymentInfo.getBonusForSuccess());
        assertEquals(updatedPaymentInfoDto.getNumberOfShares(), existingPaymentInfo.getNumberOfShares());
    }

    // todo correct this test
    @Test
    void updatePaymentInfo_negative_idNotFound() {
        Long nonExistingPaymentInfoId = 999L;
        PaymentInfoDto nonExistingPaymentInfoDto = new PaymentInfoDto(nonExistingPaymentInfoId, 6000.0, SalaryLevelEnum.ASSOCIATE, 300.0, 150, 0);

        when(entityManager.find(PaymentInfo.class, nonExistingPaymentInfoId)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> paymentInfoBean.updatePaymentInfo(nonExistingPaymentInfoId, nonExistingPaymentInfoDto));
    }

    // todo correct this test
    @Test
    void deletePaymentInfo_positive() {
        Long paymentInfoIdToDelete = 1L;

        PaymentInfo existingPaymentInfo = new PaymentInfo();
        existingPaymentInfo.setId(paymentInfoIdToDelete);

        when(entityManager.find(PaymentInfo.class, paymentInfoIdToDelete)).thenReturn(existingPaymentInfo);

        paymentInfoBean.deletePaymentInfo(paymentInfoIdToDelete);

        verify(entityManager).remove(existingPaymentInfo);
    }

    // todo correct this test
    @Test
    void deletePaymentInfo_negative_idNotFound() {
        Long nonExistingPaymentInfoId = 999L;

        when(entityManager.find(PaymentInfo.class, nonExistingPaymentInfoId)).thenReturn(null);

        paymentInfoBean.deletePaymentInfo(nonExistingPaymentInfoId);

        ArgumentCaptor<PaymentInfo> captor = ArgumentCaptor.forClass(PaymentInfo.class);
        verify(entityManager).remove(captor.capture());

        PaymentInfo capturedPaymentInfo = captor.getValue();
        assertNull(capturedPaymentInfo);
    }
}

