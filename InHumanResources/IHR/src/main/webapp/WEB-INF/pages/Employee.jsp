<%--
  Created by IntelliJ IDEA.
  User: Radu
  Date: 1/6/2024
  Time: 1:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Employees">
    <br>
    <br>
    <br>
    <h1 class="d-flex justify-content-center">Employees</h1>
    <br>
    <br>
    <div class="container text-center">
        <div class="row">
            <div class="col">
                Employee:
            </div>
            <div class="col">
                    Gen
            </div>
            <div class="col">
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/EditEmployee">Edit Employee</a>
            </div>

        </div>
    </div>
    <br>
    <br>

    <div>
        <a class="btn btn-secondary d-flex justify-content-center"  href="${pageContext.request.contextPath}/AddEmployee">Add an employee</a>
    </div>
</t:pageTemplate>
