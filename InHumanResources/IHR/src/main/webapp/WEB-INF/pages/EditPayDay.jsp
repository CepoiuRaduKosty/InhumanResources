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

<style>
    .page-container {
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 0 10%;
    }
</style>

<t:pageTemplate pageTitle="Edit Pay Day">
    <div class="container">
        <div class="page-container">
            <h1>Edit Pay Day</h1>
            <form class="needs-validation" novalidate method="post" action="${pageContext.request.contextPath}/EditPayDay">
                <div class="row">
                    <div >
                        <label for="payDayOfMonth">Pay Day of the Month</label>
                        <input type="text" class="form-control" id="payDayOfMonth" name="payDayOfMonth" placeholder=""
                               value="${payDayOfMonth}" required>
                        <div class="invalid-feedback">
                            Please input a correct pay day.
                        </div>
                    </div>
                </div>

                <div class="row">
                    <button class="w-10 btn btn-primary btn-lg mt-2" type="submit">Save</button>
                </div>
            </form>
            <script src="${pageContext.request.contextPath}/scripts/payDay-form-validation.js"></script>
        </div>
    </div>
</t:pageTemplate>
