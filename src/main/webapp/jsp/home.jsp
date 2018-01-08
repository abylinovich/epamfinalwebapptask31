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

<div class="container">
    <section id="content" style="width: 100%">
        <jsp:include page="block/random_question.jsp" />
        <%--<%@include file="block/random_question.jsp"%>--%>
    </section>
</div>

</body>
</html>
