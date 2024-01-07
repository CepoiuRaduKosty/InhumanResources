<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="Employee Paychecks">

    <div class="container mt-5">
        <br>
        <br>
        <h2 class="mb-4">Employee Paychecks</h2>

        <c:if test="${not empty employeePaychecks}">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Row Number</th>
                    <th>Paycheck Date</th>
                    <th>Salary Before Taxes</th>
                    <th>Salary After Taxes</th>
                    <th>Details</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="paycheck" items="${employeePaychecks}" varStatus="rowNum">
                    <tr>
                        <td>${rowNum.index + 1}</td>
                        <td>${paycheck.date}</td>
                        <td>${paycheck.salaryBeforeTaxes}</td>
                        <td>${paycheck.finalSalary}</td>
                        <td><a href="${pageContext.request.contextPath}/PaycheckView?paycheckId=${paycheck.id}">View Details</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

    </div>

</t:pageTemplate>
