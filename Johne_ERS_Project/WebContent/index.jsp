<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ERS Welcome Page</title>
<link type="text/css" rel="stylesheet" href="css/styles.css"/>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet"/>
</head>
<body>
	<h1>Welcome to the ERS</h1>
	<p id="msg">${msg}</p>
	<form action="SignIn" method="POST" name="loginForm">
		<label for="username">
			Username: <input type="text" name="username" id="username" >
		</label>
		<br>
		<label for="password">
			Password: <input type="password" name="password" id="password" >
		</label>
		<br>
		<input type="submit" value="Login">
	</form>
</body>

<script src="script/script.js"></script>
</html>