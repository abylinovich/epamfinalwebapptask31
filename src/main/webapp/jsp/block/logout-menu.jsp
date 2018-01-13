<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="Logout" var="logout" scope="session" />

<c:if test="${not empty user}">
    <div class="btn-group-vertical">
        <a class="btn button-lang" href="/action?command=question&do=my"><fmt:message key="home" bundle="${logout}" /></a>
        <a class="btn button-lang" href="/action?command=logout"><fmt:message key="logout" bundle="${logout}" /></a>
    </div>
</c:if>
