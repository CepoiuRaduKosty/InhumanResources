<%--
  Created by IntelliJ IDEA.
  User: Darius
  Date: 1/6/2024
  Time: 2:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>Paycheck Details</title>
</head>
<body>
<h2>Paycheck Details</h2>

<c:if test="${not empty paycheckDto}">
    <p>Paycheck Date: ${paycheckDto.date}</p>
    <p>Basic Salary: ${paycheckDto.basicSalary}</p>

    <c:choose>
        <c:when test="${paycheckDto.salaryLevel == 'ASSOCIATE' or paycheckDto.salaryLevel == 'EXECUTIVE'}">
            <p>Bonus for Success: ${paycheckDto.bonusForSuccess}</p>
        </c:when>
        <c:when test="${paycheckDto.salaryLevel == 'EXECUTIVE'}">
            <p>Number of Shares: ${paycheckDto.numberOfShares}</p>
        </c:when>
        <c:when test="${paycheckDto.salaryLevel == 'PROFESSOR' and paycheckDto.isAugust}">
            <p>Cumulated Shares: ${paycheckDto.cumulatedShares}</p>
        </c:when>
    </c:choose>

    <c:if test="${not empty paycheckDto.paycheckBonuses}">
        <p>Additional Bonuses:</p>
        <ul>
            <c:forEach var="bonus" items="${paycheckDto.paycheckBonuses}">
                <li>${bonus.bonusDescription}: ${bonus.value}</li>
            </c:forEach>
        </ul>
    </c:if>
    <p>Salary Before Taxes: ${paycheckDto.salaryBeforeTaxes}</p>
    <p>Taxes (including percentage): ${paycheckDto.tax}</p>
    <p>Social Charge (including percentage): ${paycheckDto.socialCharge}</p>
    <p>Final Salary: ${paycheckDto.finalSalary}</p>

    <label>Select Salary Level:</label>
    <select id="salaryLevel" name="salaryLevel" onchange="filterPaychecks()">
        <option value="ALL">All</option>
        <c:forEach var="level" items="${salaryLevels}">
            <option value="${level}">${level}</option>
        </c:forEach>
    </select>
</c:if>

<script>
    function filterPaychecks() {
        var salaryLevel = document.getElementById("salaryLevel").value;
        window.location.href = "AllPaychecks?salaryLevel=" + salaryLevel;
    }
</script>
</body>
</html>
