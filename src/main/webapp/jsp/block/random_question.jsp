<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="random" value="${param.random}" scope="page" />

<h1>${random.questionId}</h1>
<h1>${random.title}</h1>
<h1>${random.question}</h1>
<h1>${random.theme.title}</h1>
<h1>${random.user.username}</h1>