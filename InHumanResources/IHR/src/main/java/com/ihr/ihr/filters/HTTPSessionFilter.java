package com.ihr.ihr.filters;

import com.ihr.ihr.common.interf.HTTPSessionManagement;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class HTTPSessionFilter implements Filter {

    @Inject
    private HTTPSessionManagement httpSessionManagement;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        httpSessionManagement.getEmployeeIdLoggedIn((HttpServletRequest) request);
        httpSessionManagement.getUserIdLoggedIn((HttpServletRequest) request);
        httpSessionManagement.getPaymentIdLoggedIn((HttpServletRequest) request);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Clean up resources
    }
}
