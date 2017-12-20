<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : sessionScope.locale}" scope="session" />
<c:set var="bundle" value="Login" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="${bundle}" />

<html>
<head>
  <title>Login</title>
</head>
<script src="../resources/js/libs/jquery-3.2.1.min.js"></script>
<script src="../resources/js/login.js"></script>
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
				<input type="text" class="jsUsername" placeholder="<fmt:message key="username"/>" name="username" />
			</div>
			<div class="alert alert-danger jsUsernameError" role="alert" style="display: none"><fmt:message key="alphanumericInput.error"/></div>
			<div>
				<input type="password" class="jsPassword" placeholder="<fmt:message key="password"/>" name="password" />
			</div>
			<div class="alert alert-danger jsPasswordError" role="alert" style="display: none"><fmt:message key="alphanumericInput.error"/></div>
			<div>
				<input type="submit" class="jsSubmitLogin" value="<fmt:message key="button.login"/>">
				<a href="action?command=register"><fmt:message key="link.goToregister"/></a>
			</div>
		</form>
		<div class="alert alert-danger jsSubmitError" role="alert" style="display: none"><fmt:message key="formSubmit.error"/></div>
	</section>
</div>

</body>
</html>
