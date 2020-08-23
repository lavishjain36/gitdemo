<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Books in ${category.name} - Online Book Store</title>
<link rel="stylesheet" href="/css/style.css" >

</head>
<body>
     <jsp:directive.include file="header.jsp"/>
      
      <div align ="center">
     <h2>${category.name }</h2>
      </div>
      
      <div align="center" style="width:80%; margin:0 auto;">
      <c:forEach items="${listBooks}" var="book">
      <div style="float:left;display:inline-block; margin:20px">
      
     <div>
     <a href="view_book?id=${book.bookId}">
    <img class="book-small" src="data:image/jpg;base64,${book.base64Image}"/>
     </a>
     </div>
     
     <div>
     <a href="view_book?id=${book.bookId}">
     <b>${book.title }</b>
     </a>
     </div>   
     <div>
           <jsp:directive.include file="book_rating.jsp"/>
     </div>   
     
     <div><i>by ${book.author }</i></div>
     <div><b>$${book.price}</b></div>
    </div>      
  </c:forEach>
  </div>   
    
  <jsp:directive.include file="footer.jsp"/>
</body>
</html>