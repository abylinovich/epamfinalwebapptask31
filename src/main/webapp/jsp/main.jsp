<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="block/header.jsp"%>

<fmt:setBundle basename="Home" var="home" scope="session" />

<html>
<head>
    <title>Home</title>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2">
        </div>
        <div class="col-sm-8">
            <c:choose>
                <c:when test="${not empty questions}">
                    <h2>${title}</h2>
                    <c:forEach items="${questions}" var="item">
                        <c:set var="question" value="${item}" scope="request" />
                        <%@include file="block/question.jsp"%>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h2><fmt:message key="noQuestionsMessage" bundle="${ques}" /></h2>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="col-sm-2">
        </div>
    </div>
</div>

<%@include file="block/footer.jsp"%>
</body>
</html>
