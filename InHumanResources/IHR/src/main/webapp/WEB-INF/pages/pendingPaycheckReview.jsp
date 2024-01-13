<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Pending Paycheck Review">
    <c:choose>
        <c:when test="${error == 'invalidId'}">
            <div class="alert alert-danger" role="alert">
                Resource not found! Invalid Employee ID!
            </div>
        </c:when>
        <c:otherwise>
            <h2 class="text-center mb-4">Pending Paycheck Review</h2>
            <br>
            <form class="needs-validation" novalidate action="${pageContext.request.contextPath}/PendingPaycheckReview" method="POST">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6 mx-auto">
                            <div class="form-group mb-3">
                                <p class="font-weight-bold h4">Employee: ${employee.surname} ${employee.name}</p>
                                <input type="hidden" name="employeeId" value="${employee.id}">
                            </div>

                            <div class="form-group">
                                <label for="basicSalary">Basic Salary:</label>
                                <input type="text" class="form-control" id="basicSalary" name="basicSalary" value="${basicSalary}" readonly>
                            </div>

                            <br>

                            <div class="form-group">
                                <label for="successBonus">Success Bonus:</label>
                                <input type="text" class="form-control" id="successBonus" name="successBonus" value="${successBonus}">
                            </div>

                            <br>

                            <div class="form-group">
                                <label for="sharesNumber">Number of Shares:</label>
                                <input type="text" class="form-control" id="sharesNumber" name="sharesNumber" value="${sharesNumber}">
                            </div>

                            <br>

                            <div class="form-group">
                                <label for="cumulatedSharesNumber">Cumulated Shares Number:</label>
                                <input type="text" class="form-control" id="cumulatedSharesNumber" name="cumulatedSharesNumber" value="${cumulatedSharesNumber}">
                            </div>

                            <br>

                            <div class="form-group">
                                <label>Extra Bonus:</label>
                                <c:forEach items="${extraBonuses}" var="extraBonus">
                                    <div class="row">
                                        <div class="col">
                                            <input type="checkbox" name="extraBonusIds" value="${extraBonus.id}">
                                        </div>
                                        <div class="col">
                                            <label>Description: ${extraBonus.bonusDescription}</label>
                                        </div>
                                        <div class="col">
                                            <label>Value: ${extraBonus.value}</label>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>

                            <br>

                            <div class="form-group">
                                <label for="salaryBeforeTax">Salary Before Tax:</label>
                                <input type="text" class="form-control" id="salaryBeforeTax" name="salaryBeforeTax" value="${salaryBeforeTax}" readonly>
                            </div>

                            <br>

                            <div class="form-group">
                                <label for="salaryAfterTax">Salary After Tax:</label>
                                <input type="text" class="form-control" id="salaryAfterTax" name="salaryAfterTax" value="${salaryAfterTax}" readonly>
                            </div>

                            <br>

                            <c:choose>
                                <c:when test="${isPayDateSet == false}">
                                    <button type="submit" class="btn btn-primary" disabled>Submit</button>
                                    <div class="alert alert-danger mt-3" role="alert">
                                        Today is not the pay day, hence paychecks cannot be approved.
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn btn-primary">Submit</button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </form>
        </c:otherwise>
    </c:choose>
</t:pageTemplate>


