<%--
  Created by IntelliJ IDEA.
  User: Radu
  Date: 1/3/2024
  Time: 8:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:pageTemplate pageTitle="Edit Pay Day">
    <h1>Edit Pay Day</h1>
    <form class="needs-validation" novalidate method="post" action="${pageContext.request.contextPath}/EditPayDay">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="payDayOfMonth">payDayOfMonth</label>
                <input type="text" class="form-control" id="payDayOfMonth" name="payDayOfMonth" placeholder=""
                       value="${payDayOfMonth}" required>
                <div class="invalid-feedback">
                    Please input a correct pay day.
                </div>
            </div>
        </div>
        <hr>
        <button class="w-10 btn btn-primary btn-lg" type="submit">Save</button>
    </form>
    <script src="${pageContext.request.contextPath}/scripts/payDay-form-validation.js"></script>
</t:pageTemplate>