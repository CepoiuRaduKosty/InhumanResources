<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Employee Search">
    <div class="container mt-5">

        <h1>Find Employee</h1>
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                    ${error}
            </div>
        </c:if>
        <div class="card" style="background-color: #17202a; color: white; border: none;">
            <div class="card-body">
                <form action="FindEmployee" method="post">
                    <div class="row">
                        <div class="row">
                            <div class="form-group">
                                <label for="employeeName">Employee Name:</label>
                                <input type="text" class="form-control" id="employeeName" name="employeeName"
                                       placeholder="Enter employee name" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col mt-3">
                                <button type="submit" class="btn btn-primary btn-block">Search</button>
                            </div>
                            <div class="col mt-3">
                                <a class="btn btn-secondary btn-block"
                                   href="${pageContext.request.contextPath}/AddEmployee">Add a new
                                    employee</a>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>


        <c:if test="${not empty matchingEmployees}">
            <div class="table-responsive mt-3">
                <table class="table table-bordered table-striped">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Index</th>
                        <th scope="col">Name</th>
                        <th scope="col">Surname</th>
                        <th scope="col">Username</th>
                        <th scope="col">Email</th>
                        <th scope="col">Details</th>
                        <th scope="col">Edit</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="employee" items="${matchingEmployees}" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${employee.getName()}</td>
                            <td>${employee.getSurname()}</td>
                            <td>${employee.userDto.username}</td>
                            <td>${employee.userDto.email}</td>
                            <td>
                                <a class="btn btn-secondary btn-sm"
                                   href="${pageContext.request.contextPath}/EmployeeDetails?id_link=${employee.id}">Details</a>
                            </td>
                            <td>
                                <a class="btn btn-primary btn-sm"
                                   href="${pageContext.request.contextPath}/EditEmployee?id_link=${employee.id}">Edit</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </c:if>
    </div>


</t:pageTemplate>
