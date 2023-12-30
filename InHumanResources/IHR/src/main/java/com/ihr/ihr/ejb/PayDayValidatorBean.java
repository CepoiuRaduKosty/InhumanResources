package com.ihr.ihr.ejb;

import com.ihr.ihr.common.interf.PayDayValidation;
import jakarta.ejb.Stateless;

import java.util.logging.Logger;

@Stateless
public class PayDayValidatorBean implements PayDayValidation {
    private static final Logger LOG = Logger.getLogger(PayDayValidatorBean.class.getName());
    @Override
    public boolean isPayDayValid(Integer payDayOfMonth) {
        return payDayOfMonth >= 1 && payDayOfMonth <= 28;
    }
}
