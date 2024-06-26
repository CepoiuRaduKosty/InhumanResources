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

<t:pageTemplate pageTitle="Pay Day">
    <br>
    <br>
    <br>
    <h1 class="d-flex justify-content-center">Pay Day</h1>
    <br>
    <br>
    <div class="container text-center">
        <div class="row">
            <div class="col text-center h3">
                Pay day is not set
            </div>
        </div>
        <div class="row">
            <div class="col text-center h3 pt-2">
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/AddPayDay">Add Pay Day</a>
            </div>
        </div>
    </div>
</t:pageTemplate>
