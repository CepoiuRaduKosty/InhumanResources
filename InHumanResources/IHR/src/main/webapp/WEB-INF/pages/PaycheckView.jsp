<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<style>
    .page-container {
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 0 10%;
    }
</style>
<t:pageTemplate pageTitle="Paycheck View">

    <div class="mt-5 page-container">
        <br>
        <br>
        <h2 class="mb-4">Paycheck Details</h2>

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
                <c:when test="${salaryLevel == 'PROFESSOR' and paycheck.date.monthValue == 8}">
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
            <p>Taxes (including percentage): ${paycheck.tax}%</p>
            <p>Social Charge (including percentage): ${paycheck.socialCharge}%</p>
            <p>Final Salary: ${paycheck.finalSalary}</p>

        </c:if>

    </div>

</t:pageTemplate>
