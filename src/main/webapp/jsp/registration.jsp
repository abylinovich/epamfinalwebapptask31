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
		<form action="">
			<h1>Register</h1>
			<div>
				<input type="text" placeholder="First name" required="" id="name" />
			</div>
			<div>
				<input type="text" placeholder="Last name" required="" id="lastname" />
			</div>
			<div>
				<input type="text" placeholder="Username" required="" id="username" />
			</div>
			<div>
				<input type="password" placeholder="Password" required="" id="password" />
			</div>
			<div>
				<input type="submit" value="Log in" />
				<a href="/login">Back to login page</a>
			</div>
		</form><!-- form -->
	</section><!-- content -->
</div><!-- container -->

    <script  src="../resources/js/index.js"></script>

</body>
</html>
