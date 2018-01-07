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
<jsp:include page="/jsp/block/header.jsp"/>
<div class="container">
    <h2>${requestScope.errorMessage}</h2>
</div>

</body>
</html>
