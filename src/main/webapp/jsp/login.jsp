<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : sessionScope.locale}" scope="session" />
<c:set var="bundle" value="Login" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="${bundle}" />

<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
</head>
<body>
<jsp:include page="/jsp/block/header.jsp">
	<jsp:param name="url" value="action?command=login" />
</jsp:include>

<div class="container">
	<section id="content">
		<form name="loginForm" action="action" method="post">
			<input type="hidden" name="command" value="login" />
			<h1><fmt:message key="form.name"/></h1>
			<div>
				<input type="text" placeholder="<fmt:message key="username"/>" required="" name="username"  />
			</div>
			<div>
				<input type="password" placeholder="<fmt:message key="password"/>" required="" name="password" />
			</div>
			<div>
				<input type="submit" value="<fmt:message key="button.login"/>">
				<a href="action?command=register"><fmt:message key="link.goToregister"/></a>
			</div>
		</form>
	</section>
</div>

</body>
</html>
