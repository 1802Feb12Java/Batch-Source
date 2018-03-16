<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ERS Employee Home Page</title>
<link type="text/css" rel="stylesheet" href="css/styles.css"/>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet"/>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    
<!-- Bootstrap Font Awesome -->
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"/>
</head>
<body>
	<ul class="nav justify-content-end">
		<li class="nav-item">
			<a href="employeePages/employeeHomepage.jsp">Home</a>
		</li>
		<li class="nav-item">
			<a href="SubmitReimbursement" name="formLink">Reimbursement Form</a>
		</li>
		<li class="nav-item">
			<a href="Signout">Logout</a>
		</li>
	
	</ul>

	<h1>Welcome to the ERS Homepage, ${firstName} ${lastName}</h1>

	
	
</body>
</html>