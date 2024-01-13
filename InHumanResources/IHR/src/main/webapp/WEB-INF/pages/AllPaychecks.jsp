<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:pageTemplate pageTitle="All Paychecks">

    <div class="container mt-5">
        <br>
        <br>
        <h1 class="d-flex justify-content-center">All Paychecks</h1>

        <div class=" row d-flex justify-content-center">
            <div class="col-md-3">
                <label for="filterDate">Filter by Date:</label>
                <input type="date" id="filterDate" class="form-control" oninput="filterTable()" />
            </div>
            <div class="col-md-3">
                <label for="filterEmployeeName">Filter by Employee Name:</label>
                <input type="text" id="filterEmployeeName" class="form-control" oninput="filterTable()" />
            </div>
        </div>

        <c:if test="${not empty paycheckWithNames && paycheckWithNames.size() > 0}">
            <table class="table table-bordered" id="paycheckTable">
                <thead>
                <tr>
                    <th>Row Number</th>
                    <th>Paycheck Date</th>
                    <th>Employee Name</th>
                    <th>Salary Before Taxes</th>
                    <th>Salary After Taxes</th>
                    <th>Details</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="paycheckWithNames" items="${paycheckWithNames}" varStatus="rowNum">
                    <tr>
                        <td>${rowNum.index + 1}</td>
                        <td>${paycheckWithNames.paycheckDto.date}</td>
                        <td>${paycheckWithNames.name}</td>
                        <td>${paycheckWithNames.paycheckDto.salaryBeforeTaxes}</td>
                        <td>${paycheckWithNames.paycheckDto.finalSalary}</td>
                        <td><a href="${pageContext.request.contextPath}/PaycheckView?paycheckId=${paycheckWithNames.paycheckDto.id}">View Details</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>

    </div>

    <script>
        function filterTable() {
            var inputDate = document.getElementById("filterDate").value;
            var inputEmployeeName = document.getElementById("filterEmployeeName").value.toUpperCase();
            var table = document.getElementById("paycheckTable");
            var rows = table.getElementsByTagName("tr");

            for (var i = 1; i < rows.length; i++) {
                var date = rows[i].getElementsByTagName("td")[1].innerText;
                var employeeName = rows[i].getElementsByTagName("td")[2].innerText.toUpperCase();

                if (date.includes(inputDate) && employeeName.includes(inputEmployeeName)) {
                    rows[i].style.display = "";
                } else {
                    rows[i].style.display = "none";
                }
            }
        }
    </script>

</t:pageTemplate>
