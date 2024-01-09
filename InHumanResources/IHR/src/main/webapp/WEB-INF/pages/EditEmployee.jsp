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
<t:pageTemplate pageTitle="Edit Employee">
    <br>
    <br>
    <h1>Edit Employee</h1>
    <br>
    <br>

    <h1>Employee personal information:</h1>
    <br>
    <form class="needs-validation" novalidate method="post" action="${pageContext.request.contextPath}/EditEmployee">
        <input type="hidden" name="employee_id" value="${employee_id}"/>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name" placeholder=""
                       value="${employee.name}" required>
                <div class="invalid-feedback">
                    Please input a correct name.
                </div>
            </div>

            <div class="col-md-6 mb-3">
                <label for="surname">Surname</label>
                <input type="text" class="form-control" id="surname" name="surname" placeholder=""
                       value="${employee.surname}" required>
                <div class="invalid-feedback">
                    Please input a correct surname.
                </div>
            </div>

            <div class="col-md-6 mb-3">
                <label for="gender" class="form-label">Gender</label>
                <select class="form-select" id="gender" name="gender" required>
                    <option selected value="">Choose</option>
                    <option value="MALE" ${employee.gender == 'MALE' ? 'selected' : ''}>Male</option>
                    <option value="FEMALE" ${employee.gender == 'FEMALE' ? 'selected' : ''}>Female</option>
                </select>
                <div class="invalid-feedback">
                    Please input a gender.
                </div>
            </div>

            <div class="col-md-6 mb-3">
                <label for="dateOfBirth">Date Of Birth</label>
                <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" placeholder=""
                       value="${employee.dateOfBirth}" required>
                <div class="invalid-feedback">
                    Please input a correct date of birth.
                </div>
            </div>
        </div>
        <div class="row">

            <div class="col-md-6 mb-3">
                <label for="address">Address</label>
                <input type="text" class="form-control" id="address" name="address" placeholder=""
                       value="${employee.address}" required>
                <div class="invalid-feedback">
                    Please input a correct address.
                </div>
            </div>


            <div class="col-md-6 mb-3">
                <label for="religion">Religion</label>
                <input type="text" class="form-control" id="religion" name="religion" placeholder=""
                       value="${employee.religion}" required>
                <div class="invalid-feedback">
                    Please input a correct religion.
                </div>
            </div>

            <div class="col-md-6 mb-3">
                <label for="hoursPerWeek">Hours Per Week;</label>
                <input type="text" class="form-control" id="hoursPerWeek" name="hoursPerWeek" placeholder=""
                       value="${employee.hoursPerWeek}" required>
                <div class="invalid-feedback">
                    Please input the hours per week between 10 and 40.
                </div>
            </div>
        </div>
        <br>
        <br>

        <h1>Login information:</h1>
        <br>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="username" class="sr-only col-md-6 mb-3">Username</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="Username" required
                       autofocus value="${employee.userDto.username}"/>
            </div>
            <div class="col-md-6 mb-3">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" class="form-control" placeholder="Email" required value="${employee.userDto.email}"/>
            </div>
            <div class="col-md-6 mb-3">
                <label for="password" class="sr-only col-md-6 mb-3">Password</label>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password"/>
            </div>
        </div>

        <h1>Employee bank information:</h1>
        <br>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="iBan">iBan</label>
                <input type="text" class="form-control" id="iBan" name="iBan" placeholder=""
                       value="${bankInfo.IBAN}" required>
                <div class="invalid-feedback">
                    Please input a correct iBan.
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="bankName">Bank Name</label>
                <input type="text" class="form-control" id="bankName" name="bankName" placeholder=""
                       value="${bankInfo.bankName}" required>
                <div class="invalid-feedback">
                    Please input a correct bank name.
                </div>
            </div>
        </div>
        <br>
        <br>

        <c:if test="${isAdmin == true}">
            <h1>Employee payment information:</h1>
            <br>
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label for="monthlyBasicSalary">Monthly basic salary</label>
                    <input type="text" class="form-control" id="monthlyBasicSalary" name="monthlyBasicSalary" placeholder=""
                           value="${paymentInfo.monthlyBasicSalary}" required>
                    <div class="invalid-feedback">
                        Please input a correct Monthly Basic Salary.
                    </div>
                </div>

                <div class="col-md-6 mb-3">
                    <label for="salaryLevel" class="form-label">Salary level</label>
                    <select class="form-select" id="salaryLevel" name="salaryLevel" required>
                        <option selected value="">Choose</option>
                        <option value="LECTURER" ${paymentInfo.salaryLevel == 'LECTURER' ? 'selected' : ''}>LECTURER
                        </option>
                        <option value="ASSOCIATE" ${paymentInfo.salaryLevel == 'ASSOCIATE' ? 'selected' : ''}>ASSOCIATE
                        </option>
                        <option value="PROFESSOR" ${paymentInfo.salaryLevel == 'PROFESSOR' ? 'selected' : ''}>PROFESSOR
                        </option>
                        <option value="EXECUTIVE" ${paymentInfo.salaryLevel == 'EXECUTIVE' ? 'selected' : ''}>EXECUTIVE
                        </option>
                    </select>
                    <div class="invalid-feedback">
                        Please input a salary level.
                    </div>
                </div>
            </div>
        </c:if>



        <div class="col-md-6 mb-3">
            <label for="bonusForSuccess">Bonus for success</label>
            <input type="text" class="form-control" id="bonusForSuccess" name="bonusForSuccess" placeholder=""
                   value="${paymentInfo.bonusForSuccess}" required>
            <div class="invalid-feedback">
                Please input a correct bonus for success.
            </div>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="numberOfShares">Number of shares</label>
                <input type="text" class="form-control" id="numberOfShares" name="numberOfShares" placeholder=""
                       value="${paymentInfo.numberOfShares}" required>
                <div class="invalid-feedback">
                    Please input a correct number of shares.
                </div>
            </div>
            <div class="col-md-6 mb-3" hidden>
                <label for="cumulatedShares">Cumulated shares</label>
                <input type="text" class="form-control" id="cumulatedShares" name="cumulatedShares" placeholder=""
                       value="${paymentInfo.cumulatedShares}" required>
                <div class="invalid-feedback">
                    Please input a correct cumulated shares.
                </div>
            </div>
        </div>

        <hr>
        <button class="w-10 btn btn-primary btn-lg" type="submit" name="action" value="save">Save</button>
        <button class="w-10 btn btn-danger btn-lg" type="submit" name="action" value="delete">Delete Employee</button>

    </form>
    <script src="${pageContext.request.contextPath}/scripts/employee-form-validation.js"></script>
</t:pageTemplate>