<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : sessionScope.locale}" scope="session" />
<c:set var="bundle" value="Error" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="${bundle}" />

<html>
<head>
    <title>Error</title>
</head>
<body>
<jsp:include page="/jsp/block/header.jsp"/>
<jsp:include page="/jsp/block/logout.jsp"/>
<div class="container">
    <h2>${requestScope.errorMessage}</h2>
</div>

</body>
</html>
