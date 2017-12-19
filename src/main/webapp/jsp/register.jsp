<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : sessionScope.locale}" scope="session" />
<c:set var="bundle" value="Register" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="${bundle}" />

<html>
<head>
  <meta charset="UTF-8">
  <title>Registration</title>
</head>
<script  src="../resources/js/index.js"></script>
<body>
<jsp:include page="/jsp/block/header.jsp">
	<jsp:param name="url" value="action?command=register" />
</jsp:include>

<div class="container">
	<section id="content">
		<form name="registerForm" action="action" method="post">
			<input type="hidden" name="command" value="registration" />
			<h1><fmt:message key="form.name"/></h1>
			<div>
				<input type="text" placeholder="<fmt:message key="username"/>" required="" name="username" />
			</div>
			<div>
				<input type="password" placeholder="<fmt:message key="password"/>" required="" name="password" />
			</div>
			<div>
				<input type="password" placeholder="<fmt:message key="repeatPassword"/>" required="" name="passwordRepeat" />
			</div>
			<div>
				<input type="text" placeholder="<fmt:message key="firstName"/>" required="" name="firstname" />
			</div>
			<div>
				<input type="text" placeholder="<fmt:message key="lastName"/>" required="" name="lastname" />
			</div>
			<div>
				<input type="text" placeholder="<fmt:message key="email"/>" required="" name="email" />
			</div>
			<div>
				<input type="text" placeholder="<fmt:message key="age"/>" required="" name="age" />
			</div>
			<div>
				<input type="submit" value="<fmt:message key="button.register"/>" />
				<a href="action?command=login"><fmt:message key="link.goToLogin"/></a>
			</div>
		</form>
	</section>
</div>



</body>
</html>
