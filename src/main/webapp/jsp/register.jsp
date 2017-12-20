<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : sessionScope.locale}" scope="session" />
<c:set var="bundle" value="Register" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="${bundle}" />

<html>
<head>
  <title>Registration</title>
</head>
<script src="../resources/js/libs/jquery-3.2.1.min.js"></script>
<script src="../resources/js/register.js"></script>
<body>
<jsp:include page="/jsp/block/header.jsp">
	<jsp:param name="url" value="action?command=register" />
</jsp:include>

<div class="container">
	<section id="content">
		<form name="registerForm" action="action" method="post">
			<input type="hidden" name="command" value="register" />
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
				<input type="password" class="jsPasswordRepeat" placeholder="<fmt:message key="repeatPassword"/>" name="passwordRepeat" />
			</div>
			<div class="alert alert-danger jsPasswordRepeatError" role="alert" style="display: none"><fmt:message key="passwordRepeat.error"/></div>
			<div>
				<input type="text" class="jsFirstName" placeholder="<fmt:message key="firstName"/>" name="firstname" />
			</div>
			<div class="alert alert-danger jsFirstNameError" role="alert" style="display: none"><fmt:message key="textInput.error"/></div>
			<div>
				<input type="text" class="jsLastName" placeholder="<fmt:message key="lastName"/>" name="lastname" />
			</div>
			<div class="alert alert-danger jsLastNameError" role="alert" style="display: none"><fmt:message key="textInput.error"/></div>
			<div>
				<input type="text" class="jsEmail" placeholder="<fmt:message key="email"/>" name="email" />
			</div>
			<div class="alert alert-danger jsEmailError" role="alert" style="display: none"><fmt:message key="emailInput.error"/></div>
			<div>
				<input type="text" class="jsAge" placeholder="<fmt:message key="age"/>" name="age" />
			</div>
			<div class="alert alert-danger jsAgeError" role="alert" style="display: none"><fmt:message key="ageInput.error"/></div>
			<div>
				<input type="submit" class="jsSubmitRegister"  value="<fmt:message key="button.register"/>" />
				<a href="action?command=login"><fmt:message key="link.goToLogin"/></a>
			</div>
		</form>
		<div class="alert alert-danger jsSubmitError" role="alert" style="display: none"><fmt:message key="formSubmit.error"/></div>
	</section>
</div>



</body>
</html>
