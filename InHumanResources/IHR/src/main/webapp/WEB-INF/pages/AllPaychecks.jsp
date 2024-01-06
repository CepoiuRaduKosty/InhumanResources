<%--
  Created by IntelliJ IDEA.
  User: Darius
  Date: 1/6/2024
  Time: 2:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>All Paychecks</title>
</head>
<body>
<h2>All Paychecks</h2>

<c:if test="${not empty allPaychecks && allPaychecks.size() > 0}">
    <table border="1">
        <tr>
            <th>Row Number</th>
            <th>Paycheck Date</th>
            <th>Employee Name</th>
            <th>Salary Before Taxes</th>
            <th>Salary After Taxes</th>
            <th>Details</th>
        </tr>

        <c:forEach var="paycheck" items="${allPaychecks}" varStatus="rowNum">
            <tr>
                <td>${rowNum.index + 1}</td>
                <td>${paycheck.date}</td>
                <td>${paycheck.employeeName}</td>
                <td>${paycheck.salaryBeforeTaxes}</td>
                <td>${paycheck.salaryAfterTaxes}</td>
                <td><a href="paycheck-view?paycheckId=${paycheck.id}">View Details</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>