<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer profile-Online Books Store</title>
<link rel="stylesheet" href="css/style.css">

</head>
<body>

	<jsp:directive.include file="header.jsp" />

	<div align="center">
		<br />
		<h2>Welcome, ${loggedCustomer.fullname }</h2>
		<br />
		<table class="normal">
             <tr>
				<td><b>E-mail Address:</b></td>
				<td>${loggedCustomer.email }</td>
			</tr>

			<tr>
				<td><b>First Name:</b></td>
				<td>${loggedCustomer.firstname }</td>
			</tr>
            
            <tr>
				<td><b>Last Name:</b></td>
				<td>${loggedCustomer.lastname }</td>
			</tr>

			<tr>
				<td><b>Phone Number:</b></td>
				<td>${loggedCustomer.phone }</td>
			</tr>

			<tr>
				<td><b>Address Line 1:</b></td>
				<td>${loggedCustomer.addressLine1 }</td>
			</tr>
			<tr>
				<td><b>Address Line 2:</b></td>
				<td>${loggedCustomer.addressLine2 }</td>
			</tr>

			<tr>
				<td><b>City:</b></td>
				<td>${loggedCustomer.city }</td>
			</tr>
				<tr>
				<td><b>State:</b></td>
				<td>${loggedCustomer.state }</td>
			</tr>

			<tr>
				<td><b>Zip Code:</b></td>
				<td>${loggedCustomer.zipcode }</td>
			</tr>

			<tr>
				<td><b>Country:</b></td>
				<td>${loggedCustomer.countryName }</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
			
			<td colspan="2" align="center" ><b> </b><a href="edit_profile">Edit My Profile</a></td>
            </tr>
		</table>
	</div>
	
	<jsp:directive.include file="footer.jsp" />
	
</body>

</html>
