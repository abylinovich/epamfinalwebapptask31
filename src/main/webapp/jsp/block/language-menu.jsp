<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="Language" var="lang" scope="session" />


<div class="btn-group-vertical">
    <a class="btn button-lang" href="?language=en"> <fmt:message key="EN" bundle="${lang}" /></a>
    <a class="btn button-lang" href="?language=ru"> <fmt:message key="RU" bundle="${lang}" /></a>
</div>