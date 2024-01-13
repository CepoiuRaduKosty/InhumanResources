<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 1/9/2024
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:pageTemplate pageTitle="Pay Day">
    <div class="container">
        <div class="row justify-content-center mt-3 mb-3">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h1 class="card-title text-center">Access Denied</h1>
                        <p class="card-text text-center">Sorry, you don't have the necessary permissions to access this page.</p>
                        <p class="card-text text-center">Please contact the administrator for further assistance.</p>
                        <img src="${pageContext.request.contextPath}/resources/Error_black_man.png"></img>
                        <div>&#x1F468;&#x1F3FE;&#x200D;&#x1F9AF;</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</t:pageTemplate>