<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Registration</title>
</head>

<body>
<jsp:include page="/jsp/blocks/header.jsp"/>

<div class="container">
	<section id="content">
		<form name="registerForm" action="/action" method="post">
			<input type="hidden" name="command" value="registration" />
			<h1>Register</h1>
			<div>
				<input type="text" placeholder="login" required="" name="login" />
			</div>
			<div>
				<input type="password" placeholder="Password" required="" name="password" />
			</div>
			<div>
				<input type="password" placeholder="Repeat password" required="" name="repeatpassword" />
			</div>
			<div>
				<input type="text" placeholder="First name" required="" name="firstname" />
			</div>
			<div>
				<input type="text" placeholder="Last name" required="" name="lastname" />
			</div>
			<div>
				<input type="text" placeholder="E-mail" required="" name="email" />
			</div>
			<div>
				<input type="text" placeholder="Age" required="" name="age" />
			</div>
			<div>
				<input type="submit" value="Register" />
				<a href="/jsp/login.jsp">Back to login page</a>
			</div>
		</form><!-- form -->
	</section><!-- content -->
</div><!-- container -->

    <script  src="../resources/js/index.js"></script>

</body>
</html>
