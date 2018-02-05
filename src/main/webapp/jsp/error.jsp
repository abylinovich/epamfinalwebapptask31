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
            <h2><fmt:message key="error.method" bundle="${err}"/></h2>
        </c:when>
        <c:when test = "${noSuchUserError}">
            <h2><fmt:message key="error.noSuchUser" bundle="${err}"/></h2>
        </c:when>
        <c:when test = "${cannotLoginError}">
            <h2><fmt:message key="error.cannotLogin" bundle="${err}"/></h2>
        </c:when>
        <c:when test = "${cannotRegisterError}">
            <h2><fmt:message key="error.cannotRegister" bundle="${err}"/></h2>
        </c:when>
        <c:when test = "${addQuestionError}">
            <h2><fmt:message key="error.addQuestion" bundle="${err}"/></h2>
        </c:when>
        <c:when test = "${addAnswerError}">
            <h2><fmt:message key="error.addAnswer" bundle="${err}"/></h2>
        </c:when>
        <c:when test="${badRequest}">
            <h2><fmt:message key="badRequest.error" bundle="${ques}" /></h2>
        </c:when>
        <c:when test="${serverError}">
            <h2><fmt:message key="server.error" bundle="${ques}" /></h2>
        </c:when>
        <c:otherwise>
            <h2><fmt:message key="error.general" bundle="${err}"/></h2>
        </c:otherwise>
    </c:choose>
    <br/>
    <form>
        <input type="button" class="btn button-lang" value="<fmt:message key="back" bundle="${err}" />" onclick="history.back()">
    </form>
</div>

</body>
</html>
