<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : sessionScope.locale}" scope="session" />
<c:set var="bundle" value="Home" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="${bundle}" />

<html>
<head>
    <title>Home</title>
</head>
<body>
<jsp:include page="/jsp/block/header.jsp">
    <jsp:param name="url" value="action?command=home" />
</jsp:include>
<jsp:include page="/jsp/block/logout.jsp">
    <jsp:param name="url" value="action?command=logout" />
</jsp:include>
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
