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
        <form action="FindEmployee" method="post">
           <div class="row">
               <div class="form-group col">
                   <label for="employeeName">Employee Name:</label>
                   <input type="text" class="form-control" id="employeeName" name="employeeName"
                          placeholder="Enter employee name" required>
               </div>

               <button type="submit" class="btn btn-primary col">Search</button>
               <a class="btn btn-secondary col"
                  href="${pageContext.request.contextPath}/AddEmployee">Add a new employee</a>
           </div>
        </form>
        <br>
        <br>
        <br>
        <c:if test="${not empty matchingEmployees}">


            <c:forEach var="employee" items="${matchingEmployees}" varStatus="status">
                <div class="row">
                    <br>
                    <div class="col" value="entryNumber">  ${status.index + 1}  </div>
                    <div class="col" value="name"> Name: ${employee.getName()}   </div>
                    <div class="col" value="surname"> Surname: ${employee.getSurname()}  </div>
                    <div class="col" value="username"> Username: ${employee.userDto.username}  </div>
                    <div class="col" value="email"> Email: ${employee.userDto.email}  </div>
                    <div class="col" value="details">
                        <a class="btn btn-secondary col" href="${pageContext.request.contextPath}/EmployeeDetails?id_link=${employee.id}">Details</a>
                    </div>
                    <div class="col" value="edit">
                        <a class="btn btn-primary col" href="${pageContext.request.contextPath}/EditEmployee?id_link=${employee.id}">Edit</a>
                    </div>
                </div>
            </c:forEach>

        </c:if>

    </div>
</t:pageTemplate>
