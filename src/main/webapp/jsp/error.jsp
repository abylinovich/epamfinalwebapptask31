<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="block/header.jsp"%>

<fmt:setBundle basename="Error" var="err" scope="session" />


<html>
<head>
    <title>Error</title>
</head>
<body>
<div class="container">
    <c:choose>
        <c:when test = "${methodError}">
            <h1><fmt:message key="error.method" bundle="${err}"/></h1>
        </c:when>
        <c:when test = "${generalError}">
            <h1><fmt:message key="error.general" bundle="${err}"/></h1>
        </c:when>
        <c:when test = "${noSuchUserError}">
            <h1><fmt:message key="error.noSuchUser" bundle="${err}"/></h1>
        </c:when>
        <c:when test = "${cannotLoginError}">
            <h1><fmt:message key="error.cannotLogin" bundle="${err}"/></h1>
        </c:when>
        <c:when test = "${cannotRegisterError}">
            <h1><fmt:message key="error.cannotRegister" bundle="${err}"/></h1>
        </c:when>
        <c:when test = "${addQuestionError}">
            <h1><fmt:message key="error.addQuestion" bundle="${err}"/></h1>
        </c:when>
        <c:when test = "${addAnswerError}">
            <h1><fmt:message key="error.addAnswer" bundle="${err}"/></h1>
        </c:when>
        <c:otherwise>
            <h1><fmt:message key="error.general" bundle="${err}"/></h1>
        </c:otherwise>
    </c:choose>
    <br/>
    <form>
        <input type="button" class="btn button-lang" value="<fmt:message key="back" bundle="${err}" />" onclick="history.back()">
    </form>
</div>

</body>
</html>
