package com.ihr.ihr.ejb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PayDayValidatorBeanTest {

    @InjectMocks
    PayDayValidatorBean payDayValidatorBean;


    @Test
    void testIsPayDayValid_Positive() {
        assertTrue(payDayValidatorBean.isPayDayValid(15));
    }

    @Test
    void testIsPayDayValid_Negative() {
        assertFalse(payDayValidatorBean.isPayDayValid(30));
    }
}