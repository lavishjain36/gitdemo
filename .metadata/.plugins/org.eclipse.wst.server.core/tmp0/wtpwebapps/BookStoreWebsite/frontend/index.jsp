<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Evergreen Books-Online Book Store</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<jsp:directive.include file="header.jsp" />

	<div class="center">
		<div>
			<h2>New Books:</h2>
			<c:forEach items="${listNewBooks}" var="book">
				<jsp:directive.include file="book_group.jsp" />
			</c:forEach>
		</div>

		<div class="next-row">
			<h2>Best selling Books:</h2>
			<c:forEach items="${listBestSellingBooks}" var="book">
				<jsp:directive.include file="book_group.jsp" />
			</c:forEach>
		</div>

		<div class="next-row">
			<h2>Most-favoured Books:</h2>
			<c:forEach items="${listFavoredBooks}" var="book">
				<jsp:directive.include file="book_group.jsp" />
			</c:forEach>
		</div>
		<br />
		<br />
		<jsp:directive.include file="footer.jsp" />
</body>
</html>