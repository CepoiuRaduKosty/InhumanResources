<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">IHR</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
                    aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="${pageContext.request.contextPath}">Home</a>
                    </li>
                    <li>
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/PayDay">|  Pay Day</a>
                    </li>
                    <li>
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/EmployeeDetails">|  Employee Details</a>
                    </li>
                    <li>
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/AddEmployee"> |  Add Employee</a>
                    </li>
                    <li>
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/EditEmployee">|  Edit Employee</a>
                    </li>

                    <li>
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/AllPaychecks">AllPaychecks</a>
                    </li>
                    <li>
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/EmployeePaychecks">EmployeePaychecks</a>
                    </li>
                    <li>
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/PaycheckView">PaycheckView</a>
                    </li>
                    <li>
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/Login">Login</a>
                    </li>
                    <li>
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/CreateAccount">Create Account</a>
                    </li>
                </ul>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <c:choose>
                            <c:when test="${pageContext.request.getRemoteUser() == null}">
                                <a class="nav-link" href="${pageContext.request.contextPath}/Login">Login</a>
                            </c:when>
                            <c:otherwise>
                                <a class="nav-link" href="${pageContext.request.contextPath}/Logout">Logout</a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
</body>
</html>