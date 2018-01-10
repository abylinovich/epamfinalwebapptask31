<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="Greetings" var="greet" scope="session" />


<div class="container">
    <c:if test="${not empty user}">
        <fmt:message key="greeting" bundle="${greet}" />, ${user.firstName}!
    </c:if>
</div>