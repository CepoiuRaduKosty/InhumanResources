<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="InHumanResources">
    <c:forEach var="a" items="${debugtxt}">
        <p>${a}</p>
    </c:forEach>
</t:pageTemplate>