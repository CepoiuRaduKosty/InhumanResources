<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Create account">
    <form class="form" method="POST" action="${pageContext.request.contextPath}/CreateAccount">
        <h1 class="h3 mb-3 font-weight-normal">Create account</h1>

        <label for="username" class="sr-only">Username</label>
        <input type="text" id="username" name="j_username" class="form-control" placeholder="Username" required
               autofocus/>

        <label for="password" class="sr-only">Password</label>
        <input type="password" id="password" name="j_password" class="form-control" placeholder="Password" required/>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Create Account</button>
    </form>
    <script src="${pageContext.request.contextPath}/scripts/account-validation.js"></script>
    <a class="w-10 btn btn-primary btn-lg nav-link active"
       href="${pageContext.request.contextPath}/Login">Login</a>
</t:pageTemplate>