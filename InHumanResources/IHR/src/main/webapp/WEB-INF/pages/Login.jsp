<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Login">
    <c:if test="${message != null}">
        <div class="alert alert-warning alert-dismissible fade show" role="alert">
                ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </c:if>

    <h1 class="h3 mb-3 font-weight-normal text-center login-title">Sign in</h1>
    <div class="mx-auto mt-5" style="max-width: 400px;">
        <div class="card" style="background-color: #17202a; color: white; border: none;">
            <div class="card-body">
                <form class="form-signin" method="POST" action="j_security_check">
                    <div class="mb-3">
                        <label for="username" class="sr-only">Username</label>
                        <input type="text" id="username" name="j_username" class="form-control" placeholder="Username" required autofocus/>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="sr-only">Password</label>
                        <input type="password" id="password" name="j_password" class="form-control" placeholder="Password" required/>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                        </div>
                    </div>

                    <hr class="my-2">

                    <p class="text-muted mb-0 text-center">By clicking Sign up, you agree to the terms of use.</p>
                </form>
                <script src="${pageContext.request.contextPath}/scripts/account-validation.js"></script>
            </div>
        </div>
    </div>




    <script src="${pageContext.request.contextPath}/scripts/account-validation.js"></script>

</t:pageTemplate>