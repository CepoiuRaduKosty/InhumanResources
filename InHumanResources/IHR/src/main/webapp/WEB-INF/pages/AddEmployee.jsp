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


<t:pageTemplate pageTitle="Add Employee">
    <br>
    <br>
    <h1>Add Employee</h1>
    <br>
    <br>

    <h1>Employee personal information:</h1>
    <br>
    <form class="needs-validation" novalidate method="post" action="${pageContext.request.contextPath}/AddEmployee">

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name" placeholder=""
                       value="${name}" required>
                <div class="invalid-feedback">
                    Please input a correct name.
                </div>
            </div>

            <div class="col-md-6 mb-3">
                <label for="surname">Surname</label>
                <input type="text" class="form-control" id="surname" name="surname" placeholder=""
                       value="${surname}" required>
                <div class="invalid-feedback">
                    Please input a correct surname.
                </div>
            </div>

            <div class="col-md-6 mb-3">
                <label for="gender" class="form-label">Gender</label>
                <select class="form-select" id="gender" name="gender" required>
                    <option selected value="">Choose</option>
                    <option value="MALE">Male</option>
                    <option value="FEMALE">Female</option>
                </select>
                <div class="invalid-feedback">
                    Please input a gender.
                </div>
            </div>

            <div class="col-md-6 mb-3">
                <label for="dateOfBirth">Date Of Birth</label>
                <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" placeholder=""
                       value="${dateOfBirth}" required>
                <div class="invalid-feedback">
                    Please input a correct date of birth.
                </div>
            </div>
        </div>
        <div class="row">

            <div class="col-md-6 mb-3">
                <label for="address">Address</label>
                <input type="text" class="form-control" id="address" name="address" placeholder=""
                       value="${address}" required>
                <div class="invalid-feedback">
                    Please input a correct address.
                </div>
            </div>


            <div class="col-md-6 mb-3">
                <label for="religion">Religion</label>
                <input type="text" class="form-control" id="religion" name="religion" placeholder=""
                       value="${religion}" required>
                <div class="invalid-feedback">
                    Please input a correct religion.
                </div>
            </div>

            <div class="col-md-6 mb-3">
                <label for="hoursPerWeek">Hours Per Week;</label>
                <input type="text" class="form-control" id="hoursPerWeek" name="hoursPerWeek" placeholder=""
                       value="${hoursPerWeek}" required>
                <div class="invalid-feedback">
                    Please input the hours per week between 10 and 40.
                </div>
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
                       value="${iBan}" required>
                <div class="invalid-feedback">
                    Please input a correct iBan.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="bankName">Bank Name</label>
                <input type="text" class="form-control" id="bankName" name="bankName" placeholder=""
                       value="${bankName}" required>
                <div class="invalid-feedback">
                    Please input a correct bank name.
                </div>
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
                       value="${monthlyBasicSalary}" required>
                <div class="invalid-feedback">
                    Please input a correct Monthly Basic Salary.
                </div>
            </div>

            <div class="col-md-6 mb-3">
                <label for="salaryLevel" class="form-label">Salary level</label>
                <select class="form-select" id="salaryLevel" name="salaryLevel" required>
                    <option selected value="">Choose</option>
                    <option value="LECTURER">LECTURER</option>
                    <option value="ASSOCIATE">ASSOCIATE</option>
                    <option value="PROFESSOR">PROFESSOR</option>
                    <option value="EXECUTIVE">EXECUTIVE</option>
                </select>
                <div class="invalid-feedback">
                    Please input a salary level.
                </div>
            </div>
        </div>


        <div class="col-md-6 mb-3">
            <label for="bonusForSuccess">Bonus for success</label>
            <input type="text" class="form-control" id="bonusForSuccess" name="bonusForSuccess" placeholder=""
                   value="${bonusForSuccess}" required>
            <div class="invalid-feedback">
                Please input a correct bonus for success.
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="numberOfShares">Number of shares</label>
                <input type="text" class="form-control" id="numberOfShares" name="numberOfShares" placeholder=""
                       value="${numberOfShares}" required>
                <div class="invalid-feedback">
                    Please input a correct number of shares.
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <label for="cumulatedShares">Cumulated shares</label>
                <input type="text" class="form-control" id="cumulatedShares" name="cumulatedShares" placeholder=""
                       value="${cumulatedShares}" required>
                <div class="invalid-feedback">
                    Please input a correct cumulated shares.
                </div>
            </div>
        </div>

        <hr>
        <button class="w-10 btn btn-primary btn-lg" type="submit">Save</button>
    </form>
    <script src="${pageContext.request.contextPath}/scripts/employee-form-validation.js"></script>
</t:pageTemplate>