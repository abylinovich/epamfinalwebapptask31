<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
</head>

<body>
<jsp:include page="/jsp/blocks/header.jsp"/>

<div class="container">
	<section id="content">
		<form name="loginForm" action="/action" method="post">
			<input type="hidden" name="command" value="login" />
			<h1>Login Form</h1>
			<div>
				<input type="text" placeholder="Username" required="" name="login" />
			</div>
			<div>
				<input type="password" placeholder="Password" required="" name="password" />
			</div>
			<div>
				<input type="submit" value="Log in" />
				<a href="/jsp/registration.jsp">Register</a>
			</div>
		</form>
	</section>
</div>
<%--<input type="button" onclick="location.href='/registration'" value="Register" />--%>

    <script  src="../resources/js/index.js"></script>

</body>
</html>
