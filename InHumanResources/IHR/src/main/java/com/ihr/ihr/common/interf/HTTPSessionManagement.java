package com.ihr.ihr.common.interf;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

public interface HTTPSessionManagement {
    Long getUserIdLoggedIn(HttpServletRequest request) throws ServletException;

    Long getEmployeeIdLoggedIn(HttpServletRequest request) throws ServletException;

    Long getPaymentIdLoggedIn(HttpServletRequest request) throws ServletException;
}
