<%--
  Created by IntelliJ IDEA.
  User: Darius
  Date: 1/6/2024
  Time: 2:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:pageTemplate pageTitle="All Paychecks">
    <h2>All Paychecks</h2>

    <c:if test="${not empty paycheckWithNames && paycheckWithNames.size() > 0}">
        <table border="1">
            <tr>
                <th>Row Number</th>
                <th>Paycheck Date</th>
                <th>Employee Name</th>
                <th>Salary Before Taxes</th>
                <th>Salary After Taxes</th>
                <th>Details</th>
            </tr>

            <c:forEach var="paycheckWithNames" items="${paycheckWithNames}" varStatus="rowNum">
                <tr>
                    <td>${rowNum.index + 1}</td>
                    <td>${paycheckWithNames.paycheckDto.date}</td>
                    <td>${paycheckWithNames.name}</td>
                    <td>${paycheckWithNames.paycheckDto.salaryBeforeTaxes}</td>
                    <td>${paycheckWithNames.paycheckDto.finalSalary}</td>
                    <td><a href="PaycheckView?paycheckId=${paycheckWithNames.paycheckDto.id}">View Details</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</t:pageTemplate>
