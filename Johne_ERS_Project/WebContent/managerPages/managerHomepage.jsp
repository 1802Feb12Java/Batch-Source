<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ERS Manager Home Page</title>
<link type="text/css" rel="stylesheet" href="css/styles.css"/>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet"/>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
	<ul class="nav justify-content-end">
		<li class="nav-item">
			<a href="managerPages/managerHomepage.jsp">Home</a>
		</li>
		<li class="nav-item">
			<a href="managerPages/mReimburseFormUpdate.jsp">Reimbursement Form</a>
		</li>
		<li class="nav-item">
			<a href="Signout">Logout</a>
		</li>
	
	</ul>

	<div id="welcome">
		<h1 align="center">Welcome to the ERS Homepage, ${firstName} ${lastName}</h1>
	</div>
	
	
	<button onclick="getUsers()" type="submit">View all Employees</button>
	
	<table id="users" class="table table-hover">

	</table>

	<table class="table table-sm">
		<tr>
			<th>Reimbursement Type</th>
			<th>Reimbursement Status</th>
		</tr>
		<tr>
			<td>
				<ol>
					<li>Party Planning Commitee</li>
					<li>Travel/Lodging/Gas</li>
					<li>Food</li>
					<li>Other</li>
				</ol>
			</td>

			<td>
				<ol>
					<li>Pending</li>
					<li>Approved</li>
					<li>Denied</li>
				</ol>
			</td>
		</tr>
	</table>
	
	<button onclick="getReimbursements()" type="submit">View all Reimbursements</button>
	<table id="reimbursement" class="table table-hover">
		
		
	</table>
</body>

<script src="script/script.js"></script>
</html>
