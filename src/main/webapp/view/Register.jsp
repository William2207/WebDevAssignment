<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	background-color: black;
}

* {
	box-sizing: border-box;
}

/* Add padding to containers */
.container {
	padding: 16px;
	background-color: white;
}

/* Full-width input fields */
input[type=text], input[type=password] {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	display: inline-block;
	border: none;
	background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
	background-color: #ddd;
	outline: none;
}

/* Overwrite default styles of hr */
hr {
	border: 1px solid #f1f1f1;
	margin-bottom: 25px;
}

/* Set a style for the submit button */
.registerbtn {
	background-color: #04AA6D;
	color: white;
	padding: 16px 20px;
	margin: 8px 0;
	border: none;
	cursor: pointer;
	width: 100%;
	opacity: 0.9;
}

.registerbtn:hover {
	opacity: 1;
}

/* Add a blue text color to links */
a {
	color: dodgerblue;
}

/* Set a grey background color and center the text of the "sign in" section */
.signin {
	background-color: #f1f1f1;
	text-align: center;
}
</style>
<body>
	<form action="register" method="post">
		<div class="container">
			<h1>Register</h1>
			<p>Please fill in this form to create an account.</p>
			<hr>

			<label for="Username"><b>Username</b></label> <input type="text"
				placeholder="Enter Username" name="username" id="username" required>
			
			<label for="Fullname"><b>Fullname</b></label>
			<input type="text" placeholder="Enter Fullname" name="fullname"
				required>
			<label
				for="phone">Phone:</label> <input type="text" id="phone"
				name="phone" maxlength="10" required /> 
			<label for="psw"><b>Password</b></label> <input
				type="password" placeholder="Enter Password" name="password"
				id="password" required> <label for="psw-repeat"><b>Repeat
					Password</b></label> <input type="password" placeholder="Repeat Password"
				name="psw-repeat" id="psw-repeat" required> 
			<label for="email">Email:</label> <input type="email" id="email"
				name="email" required />
			<hr>
			<p>
				By creating an account you agree to our <a href="#">Terms &
					Privacy</a>.
			</p>

			<button type="submit" class="registerbtn">Register</button>
		</div>

		<div class="container signin">
			<p>
				Already have an account? <a href="${pageContext.request.contextPath }/login">Sign in</a>.
			</p>
		</div>
	</form>
</body>
</html>