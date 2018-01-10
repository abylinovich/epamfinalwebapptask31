<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="Footer" var="foot" scope="session" />

<footer>
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-2">
            </div>
            <div class="col-sm-8">
            <h1><fmt:message key="discuss" bundle="${foot}" /></h1>
                <c:forEach items="${random}" var="item">
                    <c:set var="question" value="${item}" scope="request" />
                    <%@include file="/jsp/block/question.jsp"%>
                </c:forEach>
            </div>
            <div class="col-sm-2">
            </div>
        </div>
    </div>
</footer>
