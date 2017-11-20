<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
