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

<t:pageTemplate pageTitle="Employee Details">
    <br>
    <br>
    <h1 class="d-flex justify-content-center">Employee Details</h1>
    <br>
    <br>

    <h1>Employee personal information:</h1>
    <br>
    <form novalidate method="post" action="" >

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name" placeholder=""
                       value="${employee.name}" readonly>
            </div>

            <div class="col-md-6 mb-3">
                <label for="surname">Surname</label>
                <input type="text" class="form-control" id="surname" name="surname" placeholder=""
                       value="${employee.surname}" readonly>
            </div>

            <div class="col-md-6 mb-3">
                <label for="gender">Gender</label>
                <input type="text" class="form-control" id="gender" name="gender" placeholder=""
                       value="${employee.gender}" readonly>
            </div>

            <div class="col-md-6 mb-3">
                <label for="dateOfBirth">Date of birth</label>
                <input type="text" class="form-control" id="dateOfBirth" name="dateOfBirth" placeholder=""
                       value="${employee.dateOfBirth}" readonly>
            </div>


            <div class="col-md-6 mb-3">
                <label for="address">Address</label>
                <input type="text" class="form-control" id="address" name="address" placeholder=""
                       value="${employee.address}" readonly>
            </div>


            <div class="col-md-6 mb-3">
                <label for="religion">Religion</label>
                <input type="text" class="form-control" id="religion" name="religion" placeholder=""
                       value="${employee.religion}" readonly>
            </div>

            <div class="col-md-6 mb-3">
                <label for="hoursPerWeek">Hours Per Week;</label>
                <input type="text" class="form-control" id="hoursPerWeek" name="hoursPerWeek" placeholder=""
                       value="${employee.hoursPerWeek}" readonly>
            </div>
        </div>
        <br>
        <br>

        <h1>Employee bank information:</h1>
        <br>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="iBan">iBan</label>
                <input type="text" class="form-control" id="iBan" name="iBan" placeholder=""
                       value="${bankInfo.IBAN}" readonly>
            </div>


            <div class="col-md-6 mb-3">
                <label for="bankName">Bank Name</label>
                <input type="text" class="form-control" id="bankName" name="bankName" placeholder=""
                       value="${bankInfo.bankName}" readonly>
            </div>
        </div>
        <br>
        <br>

        <h1>Employee payment information:</h1>
        <br>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="monthlyBasicSalary">Monthly basic salary</label>
                <input type="text" class="form-control" id="monthlyBasicSalary" name="monthlyBasicSalary" placeholder=""
                       value="${paymentInfo.monthlyBasicSalary}" readonly>
            </div>

            <div class="col-md-6 mb-3">
                <label for="salaryLevel">Salary Level</label>
                <input type="text" class="form-control" id="salaryLevel" name="salaryLevel" placeholder=""
                       value="${paymentInfo.salaryLevel}" readonly>
            </div>


            <div class="col-md-6 mb-3" ${isBonusForSuccessHidden ? 'hidden' : ''}>
                <label for="bonusForSuccess">Bonus for success</label>
                <input type="text" class="form-control" id="bonusForSuccess" name="bonusForSuccess" placeholder=""
                       value="${paymentInfo.bonusForSuccess}" readonly>
            </div>


            <div class="col-md-6 mb-3" ${isnumberOfSharesHidden ? 'hidden' : ''}>
                <label for="numberOfShares">Number of shares</label>
                <input type="text" class="form-control" id="numberOfShares" name="numberOfShares" placeholder=""
                       value="${paymentInfo.numberOfShares}" readonly>
            </div>
            <div class="col-md-6 mb-3" ${isCumulatedSharesHidden ? 'hidden' : ''}>
                <label for="cumulatedShares">Cumulated shares</label>
                <input type="text" class="form-control" id="cumulatedShares" name="cumulatedShares" placeholder=""
                       value="${paymentInfo.cumulatedShares}" readonly>
            </div>

        </div>
        <br>
        <br>
        <a class="w-10 btn btn-primary btn-lg nav-link active"  href="${pageContext.request.contextPath}/EditEmployee?id_link=${employee.id}">Edit Employee</a>


        <hr>

    </form>
</t:pageTemplate>