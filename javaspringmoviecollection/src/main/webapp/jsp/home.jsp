<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Collections</title>
 <jsp:include page="header.jsp"/>
</head>
<body>
 <h1>Home</h1>
 <a href="admin/login">Login</a> <br>
 
 <a href="admin/logout">Logout</a><br><br>
 
  <a href="admin/addmovies">addmovies</a>
  
  <c:forEach items="${Listimg}" var="img">
  	<img alt="#" src="${img.getImagename()}" width="200px" height="200px">
  </c:forEach>
  
</body>
</html>