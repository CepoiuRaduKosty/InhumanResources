package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BonusDtos.BonusDto;
import com.ihr.ihr.common.dtos.BonusDtos.CreateBonusDto;
import com.ihr.ihr.common.dtos.BonusDtos.UpdateBonusDto;
import com.ihr.ihr.common.excep.InvalidBonusException;
import com.ihr.ihr.common.excep.UnknownBonusException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;
import com.ihr.ihr.entities.BonusInfo;
import com.ihr.ihr.entities.PaymentInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BonusesBeanTest {

    @Mock
    EntityManager entityManager;

    @InjectMocks
    BonusesBean bonusesBean;

    @Spy
    BonusValidatorBean bonusValidatorBean;

    @Test
    void getBonusDtoById_positive_idExists() throws UnknownBonusException {
        BonusInfo bonusInfo = new BonusInfo();
        bonusInfo.setId(1L);
        bonusInfo.setBonusDescription("Sample Bonus");
        bonusInfo.setValue(100.0);

        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setId(1L);
        bonusInfo.setPaymentInfo(paymentInfo);

        when(entityManager.find(BonusInfo.class, 1L)).thenReturn(bonusInfo);

        BonusDto result = bonusesBean.getBonusDtoById(1L);

        assertNotNull(result);
        assertEquals(bonusInfo.getId(), result.getId());
        assertEquals(bonusInfo.getValue(), result.getValue());
        assertEquals(bonusInfo.getBonusDescription(), result.getBonusDescription());
    }


    @Test
    void getBonusDtoById_negative_idDoesNotExist() {
        when(entityManager.find(BonusInfo.class, 1L)).thenReturn(null);

        assertThrows(UnknownBonusException.class, () -> bonusesBean.getBonusDtoById(1L));
    }


    @Test
    void updateBonusById_positive_idExists() throws UnknownBonusException, InvalidBonusException {
        UpdateBonusDto updateBonusDto = new UpdateBonusDto(100.0, "Updated Bonus");
        BonusInfo existingBonusInfo = new BonusInfo();
        existingBonusInfo.setId(1L);

        when(entityManager.find(BonusInfo.class, 1L)).thenReturn(existingBonusInfo);

        bonusesBean.updateBonusById(1L, updateBonusDto);

        assertEquals(updateBonusDto.getValue(), existingBonusInfo.getValue());
        assertEquals(updateBonusDto.getBonusDescription(), existingBonusInfo.getBonusDescription());
    }

    @Test
    void updateBonusById_negative_idDoesNotExist() {
        UpdateBonusDto updateBonusDto = new UpdateBonusDto(100.0, "Updated Bonus");

        when(entityManager.find(BonusInfo.class, 999L)).thenReturn(null);

        assertThrows(UnknownBonusException.class, () -> bonusesBean.updateBonusById(999L, updateBonusDto));
    }

    @Test
    void createBonusByDto_positive() throws UnknownPaymentInfoException, InvalidBonusException {
        CreateBonusDto createBonusDto = new CreateBonusDto(100.0, "New Bonus", 1L);
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setId(1L);

        when(entityManager.find(PaymentInfo.class, createBonusDto.getIdPayment())).thenReturn(paymentInfo);

        bonusesBean.createBonusByDto(createBonusDto);

        ArgumentCaptor<BonusInfo> captor = ArgumentCaptor.forClass(BonusInfo.class);
        verify(entityManager).persist(captor.capture());

        BonusInfo capturedBonusInfo = captor.getValue();
        assertNotNull(capturedBonusInfo);
        assertEquals(createBonusDto.getBonusDescription(), capturedBonusInfo.getBonusDescription());
        assertEquals(createBonusDto.getValue(), capturedBonusInfo.getValue());
    }


    @Test
    void createBonusByDto_negative_invalidBonus() {
        CreateBonusDto createBonusDto = new CreateBonusDto(-100.0, "Invalid Bonus", 1L);

        assertThrows(InvalidBonusException.class, () -> bonusesBean.createBonusByDto(createBonusDto));
    }

    @Test
    void removeBonusById_positive_idExists() throws UnknownBonusException {
        BonusInfo existingBonusInfo = new BonusInfo();
        existingBonusInfo.setId(1L);

        PaymentInfo paymentInfo = new PaymentInfo();

        existingBonusInfo.setPaymentInfo(paymentInfo);

        when(entityManager.find(BonusInfo.class, 1L)).thenReturn(existingBonusInfo);

        assertDoesNotThrow(() -> bonusesBean.removeBonusById(1L));

        verify(entityManager).remove(existingBonusInfo);
    }


    @Test
    void removeBonusById_negative_idDoesNotExist() {
        when(entityManager.find(BonusInfo.class, 999L)).thenReturn(null);

        assertThrows(UnknownBonusException.class, () -> bonusesBean.removeBonusById(999L));
    }

    @Test
    void getAllBonuses_positive() {
        List<BonusInfo> bonusInfos = new ArrayList<>();
        BonusInfo bonusInfo1 = new BonusInfo();
        bonusInfo1.setId(1L);
        bonusInfo1.setValue(100.0);
        bonusInfo1.setBonusDescription("Bonus 1");
        bonusInfo1.setPaymentInfo(new PaymentInfo());

        BonusInfo bonusInfo2 = new BonusInfo();
        bonusInfo2.setId(2L);
        bonusInfo2.setValue(150.0);
        bonusInfo2.setBonusDescription("Bonus 2");
        bonusInfo2.setPaymentInfo(new PaymentInfo());

        bonusInfos.add(bonusInfo1);
        bonusInfos.add(bonusInfo2);

        TypedQuery<BonusInfo> typedQuery = Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT C FROM BonusInfo c", BonusInfo.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(bonusInfos);

        List<BonusDto> result = bonusesBean.getAllBonuses();

        assertEquals(bonusInfos.size(), result.size());
    }

    @Test
    void getAllBonusesByPaymentId_positive() {
        Long paymentId = 1L;
        List<BonusInfo> bonusInfos = new ArrayList<>();
        BonusInfo bonusInfo1 = new BonusInfo();
        bonusInfo1.setId(1L);
        bonusInfo1.setValue(100.0);
        bonusInfo1.setBonusDescription("Bonus 1");
        bonusInfo1.setPaymentInfo(new PaymentInfo());

        BonusInfo bonusInfo2 = new BonusInfo();
        bonusInfo2.setId(2L);
        bonusInfo2.setValue(150.0);
        bonusInfo2.setBonusDescription("Bonus 2");
        bonusInfo2.setPaymentInfo(new PaymentInfo());

        TypedQuery<BonusInfo> typedQuery = Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT b FROM BonusInfo b WHERE b.paymentInfo.id = :paymentId", BonusInfo.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("paymentId", paymentId)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(bonusInfos);

        List<BonusDto> result = bonusesBean.getAllBonusesByPaymentId(paymentId);

        assertEquals(bonusInfos.size(), result.size());
    }
}
