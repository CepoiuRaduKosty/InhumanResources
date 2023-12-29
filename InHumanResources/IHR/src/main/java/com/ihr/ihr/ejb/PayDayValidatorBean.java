package com.ihr.ihr.ejb;

import com.ihr.ihr.common.interf.PayDayValidation;

public class PayDayValidatorBean implements PayDayValidation {
    @Override
    public boolean isPayDayValid(Integer payDayOfMonth) {
        return payDayOfMonth >= 1 && payDayOfMonth <= 28;
    }
}
