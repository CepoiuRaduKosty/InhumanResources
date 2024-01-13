<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="PaycheckManagement">
    <div class="container mt-5">
        <br>
        <br>
        <h2 class="mb-4">Paycheck Management</h2>

        <c:choose>
            <c:when test="${isPayDateSet == false}">
                <div class="alert alert-danger mt-3" role="alert">
                    A pay date must be set to in order to utilize Paycheck Management Tools.
                </div>
            </c:when>
            <c:otherwise>
                <div class="row mb-3">
                    <div class="col-md-3">
                        <label for="filterEmployeeName">Filter by Employee Name:</label>
                        <input type="text" id="filterEmployeeName" class="form-control" oninput="filterTable()" />
                    </div>
                </div>

                <c:if test="${not empty employees}">
                    <table class="table table-bordered" id="paycheckTable">
                        <thead>
                        <tr>
                            <th>Row Number</th>
                            <th>Employee Name</th>
                            <th>Paycheck Review</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="employee" items="${employees}" varStatus="rowNum">
                            <c:if test="${employee != null}">
                                <tr>
                                    <td>${rowNum.index}</td>
                                    <td>${employee.name} ${employee.surname}</td>
                                    <td>
                                        <a class="btn btn-secondary btn-sm"
                                           href="${pageContext.request.contextPath}/PendingPaycheckReview?employeeId=${employee.id}">Review Paycheck</a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>

                </div>

                <script>
                    function filterTable() {
                        var inputEmployeeName = document.getElementById("filterEmployeeName").value.toUpperCase();
                        var table = document.getElementById("paycheckTable");
                        var rows = table.getElementsByTagName("tr");

                        for (var i = 1; i < rows.length; i++) {
                            var employeeName = rows[i].getElementsByTagName("td")[1].innerText.toUpperCase();

                            if (employeeName.includes(inputEmployeeName)) {
                                rows[i].style.display = "";
                            } else {
                                rows[i].style.display = "none";
                            }
                        }
                    }
                </script>
            </c:otherwise>
        </c:choose>

</t:pageTemplate>
