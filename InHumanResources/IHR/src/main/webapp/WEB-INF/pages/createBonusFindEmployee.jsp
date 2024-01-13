<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Create Bonus For Employee">
    <div class="container mt-5">
        <h1 class="d-flex justify-content-center">Find Employee to Add Bonus To</h1>
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                    ${error}
            </div>
        </c:if>
        <form action="CreateBonusFindEmployee" method="post">
            <div class="form-group">
                <label for="employeeName">Employee Name:</label>
                <input type="text" class="form-control" id="employeeName" name="employeeName" placeholder="Enter employee name" required>
            </div>
            <button type="submit" class="btn btn-primary mt-2">Search</button>
        </form>

        <c:if test="${not empty matchingEmployees}">
            <form action="CreateBonusForEmployee" method="get">
                <div class="form-group mt-3">
                    <label for="selectedEmployee">Select Employee:</label>
                    <select class="form-select" id="selectedEmployee" name="selectedEmployee" required>
                        <c:forEach var="employee" items="${matchingEmployees}">
                            <option value="${employee.getId()}">${employee.getName()} ${employee.getSurname()}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-success mt-2">Select</button>
            </form>
        </c:if>
    </div>
</t:pageTemplate>
