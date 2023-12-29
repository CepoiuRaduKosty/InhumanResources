package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.excep.PayDayAlreadyExistsException;
import com.ihr.ihr.common.excep.PayDayDoesNotExistException;
import jakarta.validation.ValidationException;

public interface PayDayProvider {
    Integer getDayOfMonth();
    void addPayDayOfMonth(Integer dayOfMonth) throws ValidationException, PayDayAlreadyExistsException;
    void updatePayDay(Integer dayOfMonth) throws ValidationException, PayDayDoesNotExistException;
    boolean isPayDateSet();
}
