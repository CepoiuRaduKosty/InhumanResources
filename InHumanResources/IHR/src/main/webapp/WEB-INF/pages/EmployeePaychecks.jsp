<%--
  Created by IntelliJ IDEA.
  User: Darius
  Date: 1/6/2024
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:pageTemplate pageTitle="Employee Paychecks">
    <h2>Employee Paychecks</h2>

    <c:if test="${not empty employeePaychecks}">
        <table border="1">
            <tr>
                <th>Row Number</th>
                <th>Paycheck Date</th>
                <th>Salary Before Taxes</th>
                <th>Salary After Taxes</th>
                <th>Details</th>
            </tr>

            <c:forEach var="paycheck" items="${employeePaychecks}" varStatus="rowNum">
                <tr>
                    <td>${rowNum.index + 1}</td>
                    <td>${paycheck.date}</td>
                    <td>${paycheck.salaryBeforeTaxes}</td>
                    <td>${paycheck.finalSalary}</td>
                    <td><a href="PaycheckView?paycheckId=${paycheck.id}">View Details</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</t:pageTemplate>