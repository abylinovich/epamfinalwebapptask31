<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="Question" var="ques" scope="session" />


<c:if test="${not empty user}">
    <div class="btn-group-vertical">
        <a class="btn button-lang" href="/action?command=question&do=all"><fmt:message key="myQuestions" bundle="${ques}" /></a>
        <a class="btn button-lang" href="/action?command=question&do=ask"><fmt:message key="askQuestion" bundle="${ques}" /></a>
    </div>
</c:if>
