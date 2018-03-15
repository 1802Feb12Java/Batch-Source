<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ERS Reimbursement Form</title>
<link type="text/css" rel="stylesheet" href="css/styles.css"/>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet"/>
</head>
<body>
	<nav>
		<a href="homepage.jsp">Home</a>
		<a href="R_Form.jsp">Reimbursement Form</a>
		<a href="Signout">Logout</a>
	</nav>
	
	<h1>Reimbursement Form</h1>
	
	<form action="" method="" id="rForm">
		<table>
			<tr>
				<td><label for="r_amount">Reimbursement Amount:</label></td>
				<td><input type="number" id="r_Amount" name="r_Amount" required/></td>
			</tr>
			<tr>
				<td><label for="r_description">Description:</label></td>
				<td><textarea rows="4" cols="50" placeholder="Enter description" id="r_description" name="r_Description"></textarea></td>
			</tr>
			<tr>
				<td><label for="r_receipt">Receipt (upload an image of receipt):</label></td>
				<td><input type="file" accept=".png, .jpg, .jpeg" id="r_receipt" name="r_receipt"/></td>
			</tr>
			<tr>
				<td><label for="u_id_author">Author:</label></td>
				<td><input type="text" id="u_id_author" name="u_id_author" /></td>
			</tr>
			<tr>
				<td><label for="u_id_resolver">Resolver:</label></td>
				<td><input type="text" value="Michael Scott" id="u_id_resolver" name="u_id_resolver" disabled/></td>
			</tr>
			<tr>
				<td><label>Reimbursement Type:</label></td>
				<td>
				<select required>
					<option name="">Party Planning Committee</option>
					<option name="">Travel/Lodging</option>
					<option name="">Food</option>
					<option name="">Other</option>
				</select>
				</td>
			</tr>
			<tr>
				<td><label>Reimbursement Status:</label></td>
				<td><input type="text" value="pending" disabled/></td>
			</tr>
			<tr>
				<td><label></label></td>
				<td></td>
			</tr>
			<tr>
				<td><label></label></td>
				<td></td>
			</tr>
		</table>
		<input type="submit" value="submit"/>
	</form>
	
</body>
</html>