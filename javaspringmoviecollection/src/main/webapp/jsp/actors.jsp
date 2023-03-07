<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Collections</title>
<jsp:include page="header.jsp" />
</head>
<body>

	<c:if test="${sessionScope.msg != null}">
		<div class="alert alert-danger" role="alert">
			${msg}
			<c:remove var="msg"/>
		</div>
	</c:if>


	<h1>Actors</h1>
	

	<a href="admin/addmovies">addmovies</a>


	<c:forEach items="${actlist}" var="act">
		<img alt="" src="${act.image}" width="200px" height="200px">
		${act.actorName} <br>
		${act.birthday} <br>
		${act.gender} <br>
			<a href="admin/editmovies/${mov.movies_id }">แก้ไข</a>
			<a href="admin/deletemovies/${mov.movies_id }">ลบ</a>
		<br>
	</c:forEach>


</body>
</html>