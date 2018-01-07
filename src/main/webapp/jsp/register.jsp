<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="block/header.jsp"%>

<fmt:setBundle basename="Register" var="reg" scope="session" />


<html>
<head>
  <title>Registration</title>
</head>
<script src="../resources/js/libs/jquery-3.2.1.min.js"></script>
<script src="../resources/js/register.js"></script>
<body>


<div class="container">
	<section id="content">
		<form name="registerForm" action="action" method="post">
			<input type="hidden" name="command" value="register" />
			<h1><fmt:message key="form.name" bundle="${reg}" /></h1>
			<div>
				<input type="text" class="jsUsername" placeholder="<fmt:message key="username" bundle="${reg}" />" name="username" />
			</div>
			<div class="alert alert-danger jsUsernameError" role="alert" style="display: none"><fmt:message key="alphanumericInput.error" bundle="${reg}" /></div>
			<div>
				<input type="password" class="jsPassword" placeholder="<fmt:message key="password" bundle="${reg}" />" name="password" />
			</div>
			<div class="alert alert-danger jsPasswordError" role="alert" style="display: none"><fmt:message key="alphanumericInput.error" bundle="${reg}" /></div>
			<div>
				<input type="password" class="jsPasswordRepeat" placeholder="<fmt:message key="repeatPassword" bundle="${reg}" />" name="passwordRepeat" />
			</div>
			<div class="alert alert-danger jsPasswordRepeatError" role="alert" style="display: none"><fmt:message key="passwordRepeat.error" bundle="${reg}" /></div>
			<div>
				<input type="text" class="jsFirstName" placeholder="<fmt:message key="firstName" bundle="${reg}" />" name="firstname" />
			</div>
			<div class="alert alert-danger jsFirstNameError" role="alert" style="display: none"><fmt:message key="textInput.error" bundle="${reg}" /></div>
			<div>
				<input type="text" class="jsLastName" placeholder="<fmt:message key="lastName" bundle="${reg}" />" name="lastname" />
			</div>
			<div class="alert alert-danger jsLastNameError" role="alert" style="display: none"><fmt:message key="textInput.error" bundle="${reg}" /></div>
			<div>
				<input type="text" class="jsEmail" placeholder="<fmt:message key="email" bundle="${reg}" />" name="email" />
			</div>
			<div class="alert alert-danger jsEmailError" role="alert" style="display: none"><fmt:message key="emailInput.error" bundle="${reg}" /></div>
			<div>
				<input type="text" class="jsAge" placeholder="<fmt:message key="age" bundle="${reg}" />" name="age" />
			</div>
			<div class="alert alert-danger jsAgeError" role="alert" style="display: none"><fmt:message key="ageInput.error" bundle="${reg}" /></div>
			<select name="userLocale" class="form-control jsUserLocale">
				<option value=""><fmt:message key="selectLocale" bundle="${reg}" /></option>
				<option value="en"><fmt:message key="selectLocaleEN" bundle="${reg}" /></option>
				<option value="ru"><fmt:message key="selectLocaleRU" bundle="${reg}" /></option>
			</select>
			<div class="alert alert-danger jsUserLocaleError" role="alert" style="display: none">
				<fmt:message key="selectLocale.error" bundle="${reg}" />
			</div>
			<div>
				<input type="submit" class="jsSubmitRegister" value="<fmt:message key="button.register" bundle="${reg}" />" />
				<a href="/action?command=login"><fmt:message key="link.goToLogin" bundle="${reg}" /></a>
			</div>
		</form>
		<div class="alert alert-danger jsSubmitError" role="alert" style="display: none"><fmt:message key="formSubmit.error" bundle="${reg}" /></div>
	</section>
</div>

</body>
</html>
