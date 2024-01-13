<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="Search Bonus">
  <br>
  <div class="container mt-5">
    <h2 class="d-flex justify-content-center">Search Bonus</h2>

    <!-- Add Bonus Button -->

    <!-- Search Form -->
    <form action="${pageContext.request.contextPath}/SearchBonus" method="post">
      <div class="mb-3">
        <label for="employeeName">Employee Name:</label>
        <input type="text" class="form-control" id="employeeName" name="employeeName" placeholder="Enter employee name" required>
      </div>

      <!-- Add other search input fields as needed -->

      <button type="submit" class="btn btn-success">Search</button>
      <button href="${pageContext.request.contextPath}/CreateBonusFindEmployee" class="btn btn-primary">Add Bonus</button>
    </form>

    <!-- Display Bonus Results -->
    <c:if test="${not empty bonusEntries}">
      <table class="table table-bordered">
        <thead>
        <tr>
          <th>Entry Number</th>
          <th>Name and Surname of Employee</th>
          <th>Bonus Description</th>
          <th>Bonus Value</th>
          <th>Edit</th>
          <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="bonusEntry" items="${bonusEntries}" varStatus="rowNum">
          <tr>
            <td>${rowNum.index + 1}</td>
            <td>${bonusEntry.employeeName} ${bonusEntry.employeeSurname}</td>
            <td>${bonusEntry.bonusDescription}</td>
            <td>${bonusEntry.bonusValue}</td>
            <td><a class="btn-link" href="${pageContext.request.contextPath}/EditBonus?bonusId=${bonusEntry.id}">Edit</a></td>
            <td>
              <form action="${pageContext.request.contextPath}/SearchBonus" method="post">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="bonusId" value="${bonusEntry.id}">
                <a type="submit" class="btn-link" onclick="return confirm('Are you sure you want to delete this bonus?')">Delete</a>
              </form>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:if>

  </div>

</t:pageTemplate>
