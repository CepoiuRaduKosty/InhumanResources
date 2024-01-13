<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .box
    {
        width: 380px;
        min-height: 200px;
    }
</style>
<t:pageTemplate pageTitle="InHumanResources">
    <section class="hero text-center py-5" style="color: black;">
        <div class="container-fluid">
            <h1 class="display-4"
                style="background: linear-gradient(to right, #192a56, #55b8f6); -webkit-background-clip: text; -webkit-text-fill-color: transparent; font-weight: bold;">
                Welcome to InHumanResources!</h1>
            <p class="lead">Your comprehensive HR solution.</p>
        </div>
    </section>
    <section class="features py-5" style="background-color: white; color: #192a56;">
        <div class="container">
            <div class="row">
                <c:choose>
                    <c:when test="${requestScope.navIsAdmin}">
                        <div class="col-md-4">
                            <div class="card mb-4 my-card box">
                                <a class="nav-link active" aria-current="page"
                                   href="${pageContext.request.contextPath}/FindEmployee">
                                    <div class="card-body">
                                        <h2 class="card-title">Employee Management</h2>
                                        <p class="card-text">Effortlessly manage your employee records, roles, and
                                            permissions.</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card mb-4 my-card box">
                                <a class="nav-link active" aria-current="page"
                                   href="${pageContext.request.contextPath}/SearchBonus">
                                    <div class="card-body">
                                        <h2 class="card-title">Custom Bonuses Management</h2>
                                        <p class="card-text">Manage custom bonuses for employees.</p>
                                    </div>
                                </a>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="card mb-4 my-card box">
                                <a class="nav-link active" aria-current="page"
                                   href="${pageContext.request.contextPath}/PayDay">
                                    <div class="card-body">
                                        <h2 class="card-title">Custom Payment Date</h2>
                                        <p class="card-text">Set customized payment dates for employee compensation.</p>
                                    </div>
                                </a>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <div class="card mb-4 my-card box">
                                <div class="card-body">
                                    <h2 class="card-title">Paycheck Generator</h2>
                                    <p class="card-text">Generate employee paychecks with ease.</p>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4">
                            <a class="nav-link active" aria-current="page"
                               href="${pageContext.request.contextPath}/AllPaychecks">
                                <div class="card mb-4 my-card box">
                                    <div class="card-body">
                                        <h2 class="card-title">Paycheck History</h2>
                                        <p class="card-text">View the history of generated employee paychecks.</p>
                                    </div>
                                </div>
                            </a>
                        </div>

                    </c:when>
                    <c:when test="${!requestScope.navIsAdmin && requestScope.navIsEmployee}">

                        <div class="col-md-4">
                            <div class="card mb-4 my-card box">
                                <a class="nav-link active" aria-current="page"
                                   href="${pageContext.request.contextPath}/EmployeeDetails?id_link=${sessionScope.sessionEmployeeID}">
                                    <div class="card-body">
                                        <h2 class="card-title">Your Details</h2>
                                        <p class="card-text">View and edit your personal details.</p>
                                    </div>
                                </a>
                            </div>
                        </div>


                        <div class="col-md-4">
                            <div class="card mb-4 my-card box">
                                <a class="nav-link active" aria-current="page"
                                   href="${pageContext.request.contextPath}/EmployeePaychecks?employeeId=${sessionScope.sessionEmployeeID}">
                                    <div class="card-body">
                                        <h2 class="card-title">Your Paychecks</h2>
                                        <p class="card-text">View the history of paychecks and most recent paycheck.</p>
                                    </div>
                                </a>
                            </div>
                        </div>

                    </c:when>
                    <c:otherwise>
                        <div class="row">
                            <div class="col-md-4">
                                <div class="card mb-4 my-card box">
                                    <div class="card-body">
                                        <h2 class="card-title">Explore Employee Records</h2>
                                        <p class="card-text">Access a comprehensive database of employee
                                            information.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="card mb-4 my-card box">
                                    <div class="card-body">
                                        <h2 class="card-title">Custom Bonuses and Payments</h2>
                                        <p class="card-text">Manage custom bonuses and payment schedules for
                                            employees.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="card mb-4 my-card box">
                                    <div class="card-body">
                                        <h2 class="card-title">Generate Paychecks</h2>
                                        <p class="card-text">Create and access employee paychecks seamlessly.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="card mb-4 my-card box">
                                    <div class="card-body">
                                        <h2 class="card-title">Log in Now</h2>
                                        <p class="card-text">Sign in to access personalized features and more.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </section>

</t:pageTemplate>