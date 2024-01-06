<%--
  Created by IntelliJ IDEA.
  User: Darius
  Date: 1/6/2024
  Time: 2:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:pageTemplate pageTitle="Paycheck Details">
<h2>Paycheck Details</h2>

<c:if test="${not empty paycheck}">
    <p>Paycheck Date: ${paycheck.date}</p>
    <p>Basic Salary: ${paycheck.basicSalary}</p>

    <c:choose>
        <c:when test="${salaryLevel == 'ASSOCIATE' or salaryLevel == 'EXECUTIVE'}">
            <p>Bonus for Success: ${paycheck.bonusForSuccess}</p>
        </c:when>
        <c:when test="${salaryLevel == 'EXECUTIVE'}">
            <p>Number of Shares: ${paycheck.numberOfShares}</p>
        </c:when>
        <c:when test="${salaryLevel == 'PROFESSOR' and monthIsAugust(paycheck.date)}">
            <p>Cumulated Shares: ${paycheck.cumulatedShares}</p>
        </c:when>
    </c:choose>

    <c:if test="${not empty paycheckBonus}">
        <p>Additional Bonuses:</p>
        <ul>
            <c:forEach var="bonus" items="${paycheckBonus}">
                <li>${bonus.bonusDescription}: ${bonus.value}</li>
            </c:forEach>
        </ul>
    </c:if>

    <p>Salary Before Taxes: ${paycheck.salaryBeforeTaxes}</p>
    <p>Taxes (including percentage): ${paycheck.tax}</p>
    <p>Social Charge (including percentage): ${paycheck.socialCharge}</p>
    <p>Final Salary: ${paycheck.finalSalary}</p>

</c:if>

<script>
    function monthIsAugust(date) {
        var parsedDate = new Date(date);

        var month = parsedDate.getMonth();

        return month === 7;
    }
</script>
</t:pageTemplate>
