<%--
  Created by IntelliJ IDEA.
  User: Cepoiul
  Date: 1/10/2024
  Time: 12:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Exception Occured">
    <div class="container error-container" style="max-width: 600px; margin: auto; text-align: center; padding: 20px;">
        <h1 class="error-title" style="font-size: 2em; margin-bottom: 20px;">Sorry, an error occurred!</h1>
        <p class="lead">${requestScope['javax.servlet.error.message']}</p>
        <p class="contact-support" style="margin-top: 30px;font-size: 1.2em;">Please contact support for assistance.</p>
    </div>
</t:pageTemplate>