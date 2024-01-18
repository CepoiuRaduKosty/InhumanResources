package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.excep.InvalidPaycheckBonusException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;
import jakarta.servlet.http.HttpServletRequest;

public interface PendingPaycheckReviewServiceProvider {
    void populatePendingPaycheckReviewDetails(EmployeeDto employee, HttpServletRequest request) throws UnknownPaymentInfoException;
    void processPendingPaycheckReview(EmployeeDto employee, HttpServletRequest request) throws UnknownPaymentInfoException, InvalidPaycheckBonusException;
}
