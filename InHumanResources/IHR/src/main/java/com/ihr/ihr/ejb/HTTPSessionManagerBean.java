package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.common.interf.EmployeeProvider;
import com.ihr.ihr.common.interf.HTTPSessionManagement;
import com.ihr.ihr.common.interf.UserProvider;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.logging.Logger;

@Stateless
public class HTTPSessionManagerBean implements HTTPSessionManagement {
    private static final Logger LOG = Logger.getLogger(EmployeeValidatorBean.class.getName());

    @Inject
    UserProvider userProvider;

    @Inject
    EmployeeProvider employeeProvider;

    private Long getIdFromRequestSession(HttpServletRequest request, String attribute) {
        HttpSession session = request.getSession();
        Object idStr = session.getAttribute(attribute);
        if (idStr == null) return null;
        return (Long) idStr;
    }

    private boolean refreshHTTPSessionIDs(HttpServletRequest request) throws ServletException {
        String username = request.getRemoteUser();
        if(username == null)
            return false;

        UserDto userDto = userProvider.getUserDtoByUsername(username);
        if(userDto == null)
            throw new ServletException("request lied!!1!");

        HttpSession session = request.getSession();
        request.setAttribute("sessionUserID", userDto.getId());
        request.setAttribute("sessionEmployeeID", userDto.getEmployeeId());

        EmployeeDto employeeDto = employeeProvider.findById(userDto.getEmployeeId());
        request.setAttribute("sessionPaymentID", employeeDto.getPaymentInfoDto().getId());

        return true;
    }

    private Long getAttributeIdLoggedIn(HttpServletRequest request, String attribute) throws ServletException {
        Long userID = getIdFromRequestSession(request, attribute);
        if (userID == null) {
            boolean userLoggedIn = refreshHTTPSessionIDs(request);
            if(userLoggedIn)
                return getIdFromRequestSession(request, attribute);
            else
                return null;
        } else return userID;
    }

    @Override
    public Long getUserIdLoggedIn(HttpServletRequest request) throws ServletException {
        return getAttributeIdLoggedIn(request, "sessionUserID");
    }

    @Override
    public Long getEmployeeIdLoggedIn(HttpServletRequest request) throws ServletException {
        return getAttributeIdLoggedIn(request, "sessionEmployeeID");
    }

    @Override
    public Long getPaymentIdLoggedIn(HttpServletRequest request) throws ServletException {
        return getAttributeIdLoggedIn(request, "sessionPaymentID");
    }
}
