<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Reimbursement Form</title>

<link type="text/css" rel="stylesheet" href="css/styles.css"/>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet"/>

<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    
	
	<form action="SubmitReimbursement" method="POST" id="rForm">
		<table>
			<tr>
				<th><h1>Employee Reimbursement Form</h1></th>
			</tr>
				
			<tr>
				<td><label for="r_amount">Reimbursement Amount:</label></td>
				<td><input type="text" id="r_Amount" name="r_Amount" /></td>
			</tr>
			<tr>
				<td><label for="r_description">Description:</label></td>
				<td><input id="r_description" name="r_Description" /></td>
			</tr>
			<tr>
				<td><label for="r_receipt">Receipt:</label></td>
				<td><input type="file" accept="file_extension" id="r_receipt" name="r_receipt" /></td>
			</tr>
			<tr>
					<td><label for="r_type">Reimbursement Type:</label></td>
					<td><input type="text" id="r_type" name="r_type" /></td>
			</tr>
			<tr>
				<td><label for="u_id_author">Author ID:</label></td>
				<td><input type="text" id="u_id_author" name="u_id_author" /></td>
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
