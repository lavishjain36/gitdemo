<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Receipt- Online Book Store</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.5.0.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>


</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div align="center">
	<h2>You have made payment successfully.Thank you for purchasing!</h2>
	<jsp:directive.include file="receipt.jsp" />
	<div>
	<br/>
	<input type="button" value="Print Receipt" onclick="javascript:showPrintReceiptPopup();"/>
  </div>
<div>

<jsp:directive.include file="footer.jsp"/>
<script >
   function showPrintReceiptPopup(){
	   var width=600;
	   var height=250;
	   var left=(screen.width-width)/2;
	   var top=(screen.width-width)/2;

	   window.open('frontend/print_receipt.jsp', '_blank',
			   'width='+ width + ', height=' + height +
			   ', top=' + top + ', left=' +left);	   
   }


</script>
	<br/>
	<form action="execute_payment" method="post">
	<input type="hidden" name="paymentId" value="${param.paymentId}"/>
	<input type="hidden" name="PayerID" value="${param.PayerID}"/>
    <input type="submit" value="Pay Now"/>
	
	</form>
	
	</div>
</div>
</body>
</html>