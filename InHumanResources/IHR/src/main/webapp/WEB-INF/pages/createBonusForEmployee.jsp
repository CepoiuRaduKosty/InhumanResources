<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Create Bonus For Employee">
    <div class="container">
        <div class="row">
            <div class="col-md-6 offset-md-3">
                <h1 class="d-flex justify-content-center">Create Bonus For Employee ${employeeDto.getName()} ${employeeDto.getSurname()}</h1>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                            ${error}
                    </div>
                </c:if>
                <form action="CreateBonusForEmployee" method="post">
                    <div class="mb-3">
                        <label for="bonusValue" class="form-label">Bonus Value</label>
                        <input type="number" step="0.01" class="form-control" id="bonusValue" name="value" required>
                    </div>
                    <div class="mb-3">
                        <label for="bonusDescription" class="form-label">Bonus Description</label>
                        <input type="text" class="form-control" id="bonusDescription" name="description" required>
                    </div>
                    <input type="number" id="hiddenPassEmployeeId" name="employeeId" value="${employeeDto.getId()}" hidden required>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>
</t:pageTemplate>
