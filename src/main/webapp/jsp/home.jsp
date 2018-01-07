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
    <section id="content">
        <h1>User information</h1>
        <h3>Login: ${requestScope.user.username}</h3>
        <h3>Password: ${requestScope.user.password}</h3>
        <h3>Age: ${requestScope.user.age}</h3>
        <h3>First name: ${requestScope.user.firstName}</h3>
        <h3>Last name: ${requestScope.user.lastName}</h3>
        <h3>Email: ${requestScope.user.email}</h3>
    </section>
</div>

</body>
</html>
