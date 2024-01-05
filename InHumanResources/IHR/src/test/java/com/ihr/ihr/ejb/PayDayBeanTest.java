package com.ihr.ihr.ejb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.ihr.ihr.common.excep.InvalidPayDayException;
import com.ihr.ihr.common.excep.PayDayAlreadyExistsException;
import com.ihr.ihr.common.excep.PayDayDoesNotExistException;
import com.ihr.ihr.common.interf.PayDayValidation;
import com.ihr.ihr.entities.PayDay;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PayDayBeanTest {

    @Mock
    EntityManager entityManager;

    @Mock
    PayDayValidation payDayValidation;

    @Mock
    TypedQuery<PayDay> typedQuery;

    @InjectMocks
    PayDayBean payDayBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDayOfMonth_Positive() {
        PayDay mockPayDay = new PayDay();
        mockPayDay.setDayOfMonth(15);

        List<PayDay> payDays = List.of(mockPayDay);
        when(entityManager.createQuery(anyString(), eq(PayDay.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(payDays);

        Integer result = payDayBean.getDayOfMonth();

        assertEquals(15, result);
    }

    @Test
    void testGetDayOfMonth_NoPayDateSet() {
        when(entityManager.createQuery(anyString(), eq(PayDay.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Collections.emptyList());

        Integer result = payDayBean.getDayOfMonth();

        assertNull(result);
    }

    @Test
    void testAddPayDayOfMonth_Positive() throws InvalidPayDayException, PayDayAlreadyExistsException {
        int validDay = 15;

        when(entityManager.createQuery(anyString(), eq(PayDay.class))).thenReturn(typedQuery);
        when(payDayValidation.isPayDayValid(validDay)).thenReturn(true);

        payDayBean.addPayDayOfMonth(validDay);

        ArgumentCaptor<PayDay> argumentCaptor = ArgumentCaptor.forClass(PayDay.class);
        verify(entityManager).persist(argumentCaptor.capture());

        PayDay capturedPayDay = argumentCaptor.getValue();
        assertNotNull(capturedPayDay);
        assertEquals(validDay, capturedPayDay.getDayOfMonth());
    }

    @Test
    void testAddPayDayOfMonth_PayDayAlreadyExistsException() {
        int existingDay = 20;
        PayDay existingPayDay = new PayDay();
        existingPayDay.setDayOfMonth(existingDay);

        when(entityManager.createQuery(anyString(), eq(PayDay.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(existingPayDay));

        assertThrows(PayDayAlreadyExistsException.class, () -> payDayBean.addPayDayOfMonth(existingDay));
    }

    @Test
    void testAddPayDayOfMonth_InvalidPayDayException() {
        when(payDayValidation.isPayDayValid(anyInt())).thenReturn(false);
        when(entityManager.createQuery(anyString(), eq(PayDay.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Collections.emptyList());

        assertThrows(InvalidPayDayException.class, () -> payDayBean.addPayDayOfMonth(40));
    }

    @Test
    void testUpdatePayDay_Positive() throws InvalidPayDayException, PayDayDoesNotExistException {
        int newDay = 25;

        PayDay existingPayDay = new PayDay();
        existingPayDay.setDayOfMonth(20);

        when(entityManager.createQuery(anyString(), eq(PayDay.class))).thenReturn(typedQuery);
        when(payDayValidation.isPayDayValid(newDay)).thenReturn(true);
        when(typedQuery.getSingleResult()).thenReturn(existingPayDay);
        when(typedQuery.getResultList()).thenReturn(List.of(existingPayDay));

        payDayBean.updatePayDay(newDay);

        assertEquals(newDay, existingPayDay.getDayOfMonth());
    }

    @Test
    void testUpdatePayDay_PayDayDoesNotExistException() {
        when(entityManager.createQuery(anyString(), eq(PayDay.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Collections.emptyList());

        assertThrows(PayDayDoesNotExistException.class, () -> payDayBean.updatePayDay(25));
    }

    @Test
    void testUpdatePayDay_InvalidPayDayException() {
        int newDay = 40;

        PayDay existingPayDay = new PayDay();
        existingPayDay.setDayOfMonth(20);

        when(entityManager.createQuery(anyString(), eq(PayDay.class))).thenReturn(typedQuery);
        when(payDayValidation.isPayDayValid(newDay)).thenReturn(false);
        when(typedQuery.getResultList()).thenReturn(List.of(existingPayDay));

        assertThrows(InvalidPayDayException.class, () -> payDayBean.updatePayDay(newDay));
    }

    @Test
    void testIsPayDateSet_Positive() {
        PayDay existingPayDay = new PayDay();
        existingPayDay.setDayOfMonth(20);
        when(entityManager.createQuery(anyString(), eq(PayDay.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(List.of(existingPayDay));

        assertTrue(payDayBean.isPayDateSet());
    }

    @Test
    void testIsPayDateSet_Negative() {
        when(entityManager.createQuery(anyString(), eq(PayDay.class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Collections.emptyList());

        assertFalse(payDayBean.isPayDateSet());
    }
}
