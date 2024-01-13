<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Pay Day">
    <br>
    <br>
    <br>
    <h1 class="d-flex justify-content-center">Pay Day</h1>
    <br>
    <br>

    <div class="d-flex flex-column align-items-center">
        <div class="mb-3 d-flex justify-content-center"> <!-- Updated class for the container of "Pay day is set on day:" and "${payDayOfMonth}" divs -->
            <div class="h3">
                Pay day is set on day:&nbsp;
            </div>
            <div class="h3">
                    ${payDayOfMonth}
            </div>
        </div>

        <div class="pt-2">
            <a class="btn btn-primary btn-block" href="${pageContext.request.contextPath}/EditPayDay">Edit Pay Day</a>
        </div>
    </div>
</t:pageTemplate>
