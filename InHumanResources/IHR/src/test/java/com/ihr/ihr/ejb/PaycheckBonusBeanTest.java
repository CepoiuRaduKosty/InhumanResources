package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.PaycheckBonusDtos.CreatePaycheckBonusDto;
import com.ihr.ihr.common.dtos.PaycheckBonusDtos.PaycheckBonusDto;
import com.ihr.ihr.common.excep.InvalidPaycheckBonusException;
import com.ihr.ihr.entities.Paycheck;
import com.ihr.ihr.entities.PaycheckBonus;
import jakarta.ejb.EJBException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaycheckBonusBeanTest {
    @Mock
    EntityManager entityManager;

    @Mock
    TypedQuery<PaycheckBonus> typedQuery;

    @Mock
    BonusValidatorBean bonusValidatorBean;

    @InjectMocks
    PaycheckBonusBean paycheckBonusBean;

    @Test
    void testGetPaycheckBonusDtoById_Positive() {
        Long bonusId = 1L;
        Double bonusValue = 100.0;
        String bonusDescription = "Test bonus description";
        Long paycheckId = 123L;

        PaycheckBonus samplePaycheckBonus = new PaycheckBonus();
        samplePaycheckBonus.setId(bonusId);
        samplePaycheckBonus.setValue(bonusValue);
        samplePaycheckBonus.setBonusDescription(bonusDescription);

        Paycheck paycheck = new Paycheck();
        paycheck.setId(paycheckId);
        samplePaycheckBonus.setPaycheck(paycheck);

        when(entityManager.find(PaycheckBonus.class, bonusId)).thenReturn(samplePaycheckBonus);

        PaycheckBonusDto result = paycheckBonusBean.getPaycheckBonusDtoById(bonusId);

        assertNotNull(result);
        assertEquals(bonusId, result.getId());
        assertEquals(bonusValue, result.getValue());
        assertEquals(bonusDescription, result.getBonusDescription());
        assertEquals(paycheckId, result.getPaycheckId());
    }

    @Test
    void testGetPaycheckBonusDtoById_Negative_InvalidId() {
        Long invalidBonusId = -1L;

        when(entityManager.find(PaycheckBonus.class, invalidBonusId)).thenReturn(null);

        PaycheckBonusDto result = paycheckBonusBean.getPaycheckBonusDtoById(invalidBonusId);

        assertNull(result);
    }

    @Test
    void testCreatePaycheckBonus_Positive_ValidInput() {
        CreatePaycheckBonusDto validPaycheckBonusDto = new CreatePaycheckBonusDto(1L,100.0, "Test Bonus");

        when(bonusValidatorBean.isPaycheckBonusDtoValid(validPaycheckBonusDto)).thenReturn(true);

        Paycheck mockedPaycheck = new Paycheck();
        mockedPaycheck.setId(1L);

        when(entityManager.find(Paycheck.class, validPaycheckBonusDto.getPaycheckId())).thenReturn(mockedPaycheck);

        ArgumentCaptor<PaycheckBonus> argumentCaptor = ArgumentCaptor.forClass(PaycheckBonus.class);
        doNothing().when(entityManager).persist(argumentCaptor.capture());

        assertDoesNotThrow(() -> paycheckBonusBean.createPaycheckBonus(validPaycheckBonusDto));

        PaycheckBonus capturedBonus = argumentCaptor.getValue();
        assertEquals(validPaycheckBonusDto.getBonusDescription(), capturedBonus.getBonusDescription());
        assertEquals(validPaycheckBonusDto.getValue(), capturedBonus.getValue());
    }

    @Test
    void testCreatePaycheckBonus_Negative_InvalidInput() {
        CreatePaycheckBonusDto invalidPaycheckBonusDto = new CreatePaycheckBonusDto(1L, -100.0, "");

        when(bonusValidatorBean.isPaycheckBonusDtoValid(invalidPaycheckBonusDto)).thenReturn(false);

        assertThrows(InvalidPaycheckBonusException.class,
                () -> paycheckBonusBean.createPaycheckBonus(invalidPaycheckBonusDto));

        verify(entityManager, never()).persist(any(PaycheckBonus.class));
    }

    @Test
    void testUpdatePaycheckBonusById_Positive_ValidInput() {
        CreatePaycheckBonusDto validPaycheckBonusDto = new CreatePaycheckBonusDto(2L, 150.0, "Updated Bonus");
        Long paycheckBonusId = 1L;

        when(bonusValidatorBean.isPaycheckBonusDtoValid(validPaycheckBonusDto)).thenReturn(true);

        PaycheckBonus existingPaycheckBonus = new PaycheckBonus();
        existingPaycheckBonus.setValue(validPaycheckBonusDto.getValue());
        existingPaycheckBonus.setBonusDescription(validPaycheckBonusDto.getBonusDescription());
        Paycheck paycheck = new Paycheck();
        paycheck.setPaycheckBonuses(new ArrayList<>());
        existingPaycheckBonus.setPaycheck(paycheck);

        when(entityManager.find(PaycheckBonus.class, paycheckBonusId)).thenReturn(existingPaycheckBonus);
        when(entityManager.find(Paycheck.class, validPaycheckBonusDto.getPaycheckId())).thenReturn(paycheck);

        paycheckBonusBean.updatePaycheckBonusById(paycheckBonusId, validPaycheckBonusDto);

        assertEquals(validPaycheckBonusDto.getValue(), existingPaycheckBonus.getValue());
        assertEquals(validPaycheckBonusDto.getBonusDescription(), existingPaycheckBonus.getBonusDescription());
    }

    @Test
    public void testUpdatePaycheckBonusByIdWithInvalidData() {
        Long invalidPaycheckBonusId = 999L;
        CreatePaycheckBonusDto invalidDto = new CreatePaycheckBonusDto(0L, 0.0, "");

        when(bonusValidatorBean.isPaycheckBonusDtoValid(invalidDto)).thenReturn(false);

        assertThrows(EJBException.class, () -> paycheckBonusBean.updatePaycheckBonusById(invalidPaycheckBonusId, invalidDto));

        verify(entityManager, never()).merge(any());
    }

}