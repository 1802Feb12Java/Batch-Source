<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ERS Reimbursement Form</title>
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
			<a href="managerPages/mReimburseForm.jsp">Reimbursement Form</a>
		</li>
		<li class="nav-item">
			<a href="Signout">Logout</a>
		</li>
	
	</ul>
	
	
	
	<form action="EditStatus" method="POST" id="rForm">
		<table>
			<tr>
				<th><h1>Reimbursement Form</h1></th>
			</tr>
				<tr>
						<td><label for="r_id">Reimbursement ID:</label></td>
						<td><input type="text" id="r_id" name="re_id" value="${reimbursement.r_id}" /></td>
					</tr>
			<tr>
				<td><label for="r_amount">Reimbursement Amount:</label></td>
				<td><input type="text" id="r_Amount" name="r_Amount" value="${reimbursement.r_amount}" disabled/></td>
			</tr>
			<tr>
				<td><label for="r_description">Description:</label></td>
				<td><input id="r_description" name="r_Description" value="${reimbursement.description}" disabled/></td>
			</tr>
			<tr>
				<td><label for="r_receipt">Receipt:</label></td>
				<td><input type="text" id="r_receipt" name="r_receipt" disabled/></td>
			</tr>
			<tr>
					<td><label for="r_type">Reimbursement Type:</label></td>
					<td><input type="text" id="r_Amount" name="r_Amount" value="${reimbursement.rt_type}" disabled/></td>
			</tr>
			<tr>
					<td><label for="r_status">Reimbursement Status:</label></td>
					<td><input type="text" id="r_status" name="r_status" value="${reimbursement.rt_status}"/></td>
			</tr>
			<tr>
					<td><label for="r_timeSubmitted">Time Submitted:</label></td>
					<td><input type="text" id="r_timeSubmitted" name="r_timeSubmitted" value="${reimbursement.r_submitted}" disabled/></td>
				</tr>
			<tr>
				<td><label for="r_timeResolved">Time resolved</label></td>
				<td><input type="time" id="r_timeResolved" name="r_timeResolved" /></td>
			</tr>
			<tr>
				<td><label for="u_id_author">Author ID:</label></td>
				<td><input type="text" id="u_id_author" name="u_id_author" value="${reimbursement.u_id_author}" disabled/></td>
			</tr>
			<tr>
					<td><label for="author_firstname">Author Firstname:</label></td>
					<td><input type="text" id="author_firstname" name="author_firstname" value="${reimbursement.author_firstname}" disabled/></td>
				</tr>
			<tr>
					<td><label for="author_lastname">Author Lastname:</label></td>
					<td><input type="text" id="author_lastname" name="author_lastname" value="${reimbursement.author_lastname}" disabled/></td>
				</tr>
			<tr>
				<td><label for="u_id_resolver">Resolver ID:</label></td>
				<td><input type="text" value="" id="u_id_resolver" name="u_id_resolver" /></td>
			</tr>
			<tr>
				<td><label for="resolver_firstname">Resolver Firstname:</label></td>
				<td><input type="text" id="resolver_firstname" name="resolver_firstname" /></td>
			</tr>
			<tr>
				<td><label for="resolver_lastname">Resolver Lastname:</label></td>
				<td><input type="text" id="resolver_lastname" name="resolver_lastname" /></td>
			</tr>
			
		</table>
		<input type="submit" value="submit"/>
	</form>
	
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
</body>
</html>