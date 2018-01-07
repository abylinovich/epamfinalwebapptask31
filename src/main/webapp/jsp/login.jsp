<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="block/header.jsp"%>

<fmt:setBundle basename="Login" var="login" scope="session" />


<html>
<head>
  <title>Login</title>
</head>
<script src="../resources/js/libs/jquery-3.2.1.min.js"></script>
<script src="../resources/js/login.js"></script>
<body>

<div class="container">
	<section id="content">
		<form name="loginForm" action="/action" method="post">
			<input type="hidden" name="command" value="login" />
			<h1><fmt:message key="form.name" bundle="${login}" /></h1>
			<div>
				<input type="text" class="jsUsername" placeholder="<fmt:message key="username" bundle="${login}" />" name="username" />
			</div>
			<div class="alert alert-danger jsUsernameError" role="alert" style="display: none"><fmt:message key="alphanumericInput.error" bundle="${login}" /></div>
			<div>
				<input type="password" class="jsPassword" placeholder="<fmt:message key="password" bundle="${login}" />" name="password" />
			</div>
			<div class="alert alert-danger jsPasswordError" role="alert" style="display: none"><fmt:message key="alphanumericInput.error" bundle="${login}" /></div>
			<div>
				<input type="submit" class="jsSubmitLogin" value="<fmt:message key="button.login" bundle="${login}" />">
				<a href="/action?command=register"><fmt:message key="link.goToRegister" bundle="${login}" /></a>
			</div>
		</form>
		<div class="alert alert-danger jsSubmitError" role="alert" style="display: none"><fmt:message key="formSubmit.error" bundle="${login}" /></div>
	</section>
</div>

</body>
</html>
