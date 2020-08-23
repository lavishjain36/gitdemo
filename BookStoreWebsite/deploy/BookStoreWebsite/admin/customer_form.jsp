<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Customer</title>
<link rel="stylesheet" href="../css/style.css">
<script type="text/javascript" src="../js/jquery-3.5.0.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>



</head>
<body>
<jsp:directive.include file="header.jsp"/>

<div align="center">
<h2 class="pageheading">
<c:if test="${customer!=null}">
      Edit Customer
</c:if>
<c:if test="${customer==null }">
      Create New Customer
</c:if>
</h2> 
</div>


<div align="center">
<c:if test="${customer!=null}">
<form action="update_customer" method="post" id="customerForm" >
<input type="hidden" name="customerId" value="${customer.customerId}">

</c:if>
<c:if test="${customer==null }">
<form action="create_customer" method="post" id="customerForm">
</c:if>

<table class="form">
<tr>
<td align="right">E-mail:</td>
<td align="left"><input type="text" id="email" name="email" size="45" value="${customer.email}"/></td>
</tr>

<tr>
<td align="right">Full Name:</td>
<td align="left"><input type="text" id="fullName" name="fullName" size="45" value="${customer.fullname}"/></td>
</tr>

<tr>
<td align="right">Password:</td>
<td align="left"><input type="password" id="password" name="password" size="45" value="${customer.password}"/></td>
</tr>

<tr>
<td align="right">Confirm password:</td>
<td align="left"><input type="Password" id="confirmPassword" name="confirmPassword" size="45" value="${customer.password}"/></td>
</tr>


<tr>
<td align="right">Phone Number:</td>
<td align="left"><input type="text" id="phone" name="phone" size="20" value="${customer.phone}"/></td>
</tr>

<tr>
<td align="right">Address:</td>
<td align="left"><input type="text" id="address" name="address" size="20" value="${customer.address}"/></td>
</tr>


<tr>
<td align="right">City:</td>
<td align="left"><input type="text" id="city" name="city" size="45" value="${customer.city}"/></td>
</tr>


<tr>
<td align="right">ZipCode:</td>
<td align="left"><input type="text" id="zipcode" name="zipcode" size="10" value="${customer.zipcode}"/></td>
</tr>


<tr>
<td align="right">Country:</td>
<td align="left"><input type="text" id="country" name="country" size="45" value="${customer.country}"/></td>
</tr>


<tr><td>&nbsp;</td></tr>
<tr>
  <td colspan="2" align="center">
      <button type="submit">Save</button>&nbsp;&nbsp;&nbsp;&nbsp;
      <button id="buttonCancel">Cancel</button>
  </td>
</tr>


</table>
</form>
</div>


<jsp:directive.include file="footer.jsp"/>
</body>
<script type="text/javascript">

 $(document).ready(function(){
	 $("#customerForm").validate({
		 
		rules: {
			email:{
				required:true,
				email:true
				
			},
            fullName:"required",
			password:"required",
			
			confirmPassword:{
				required:true,
				equalTo:"#password"
			},
            phone:"required",
            address:"required",
            city:"required",
            zipCode:"required",
            country:"required",

		},
			
			
			
      	   message:{
      		 email:{
 				required:"please enter e-mail address",
 				email:"please enter a valid e-mail address"
 				
 			},
             fullName:"please enter full name",
 			 password:"please enter password",
 			 
 			 confirmPassword:{
 				required:"please confirm password",
 				equalTo:"Confirm password does not match password with entered password"	 
 			 },
             phone:"please enter phone number",
             address:"please enter address",
             city:"please enter city",
             zipCode:"please enter zip code",
             country:"please enter country",
                }
	 });
	 
	 $("#buttonCancel").click(function(){
		 history.go(-1);
	 });
	 
 });
 
 
</script>
</html>