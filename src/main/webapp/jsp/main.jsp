<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="block/header.jsp"%>


<html>
<head>
    <title>OutOfMemory</title>
</head>
<script src="../resources/js/libs/jquery-3.2.1.min.js"></script>
<script src="../resources/js/main.js"></script>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2">
        </div>
        <div class="col-sm-8">
            <c:choose>
                <c:when test="${not empty questions}">
                    <c:forEach items="${questions}" var="item">
                        <c:set var="question" value="${item}" scope="request" />
                        <%@include file="block/question/question.jsp"%>
                    </c:forEach>
                    <c:if test="${sessionScope.totalQuestions gt 1}">
                        <%@include file="block/pagination-menu.jsp"%>
                    </c:if>
                </c:when>
                <c:when test="${not empty question}">
                    <%@include file="/jsp/block/question/edit-bar.jsp"%>
                    <%@include file="block/question/question.jsp"%>
                    <%@include file="block/answer-input.jsp"%>
                </c:when>
                <c:when test="${input}">
                    <%@include file="block/question/question-input.jsp"%>
                </c:when>
                <c:when test="${deleteSuccess}">
                    <h3><fmt:message key="deleteSuccess" bundle="${ques}" /></h3>
                    <%@include file="block/go-home-reference.jsp"%>
                </c:when>
                <c:otherwise>
                    <h3><fmt:message key="noQuestionsMessage" bundle="${ques}" /></h3>
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
