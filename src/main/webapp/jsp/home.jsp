<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<jsp:include page="/jsp/block/header.jsp"/>
<jsp:include page="/jsp/block/logout.jsp"/>
<div class="container">
    <section id="content">
        <h1>User information</h1>
        <h3>Login: ${requestScope.user.login}</h3>
        <h3>Password: ${requestScope.user.password}</h3>
        <h3>Age: ${requestScope.user.age}</h3>
        <h3>First name: ${requestScope.user.firstName}</h3>
        <h3>Last name: ${requestScope.user.lastName}</h3>
        <h3>Email: ${requestScope.user.email}</h3>
    </section>
</div>

</body>
</html>
