<%@ tag import="jakarta.inject.Inject" %>
<%@ tag import="com.ihr.ihr.common.interf.HTTPSessionManagement" %>
<%@ tag import="com.ihr.ihr.ejb.HTTPSessionManagerBean" %>
<%@ tag description="navbar" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top" style="background: linear-gradient(to right, #192a56, #0097e6);">
        <div class="container-fluid">
            <a class="navbar-brand" href="#" style="color: white; padding: 10px;">IHR</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
                    aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation" style="border-color: white;">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}" style="color: white;">Home</a>
                    </li>
                    <% request.setAttribute("navIsAdmin", request.isUserInRole("ADMIN")); %>
                    <% request.setAttribute("navIsEmployee", request.isUserInRole("EMPLOYEE")); %>
                    <c:choose>
                        <c:when test="${requestScope.navIsAdmin}">
                            <li><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/FindEmployee">Employees</a></li>
                            <li><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/AllPaychecks">All Paychecks</a></li>
                            <li><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/PayDay">Pay Day</a></li>
                            <li><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/SearchBonus">Custom Bonuses</a></li>
                            <li><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/PaycheckManagement">Paycheck Management</a></li>
                        </c:when>
                        <c:when test="${!requestScope.navIsAdmin && requestScope.navIsEmployee}">
                            <li><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/EmployeeDetails?id_link=${sessionScope.sessionEmployeeID}">My Profile</a></li>
                            <li><a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/EmployeePaychecks?employeeId=${sessionScope.sessionEmployeeID}">My Paychecks</a></li>
                        </c:when>
                    </c:choose>
                </ul>
                <ul class="navbar-nav login-btn-container">
                    <li class="nav-item login-btn">
                        <c:choose>
                            <c:when test="${pageContext.request.getRemoteUser() == null}">
                                <a class="nav-link" href="${pageContext.request.contextPath}/Login" style="color: white;">Login</a>
                            </c:when>
                            <c:otherwise>
                                <a class="nav-link" href="${pageContext.request.contextPath}/Logout" style="color: white;">Logout</a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>

