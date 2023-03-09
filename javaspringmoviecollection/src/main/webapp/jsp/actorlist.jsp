<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Collections</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Labrada">

<!--  JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<!-- Navbar -->

	<jsp:include page="navbar.jsp"></jsp:include>
	<!-- Navbar -->

	<div class="container mt-5 mb-5">
		<h1>นักแสดง</h1>
		<input type="search" id="filter" class="shadow-lg mt-3 mb-4"
			placeholder="ค้นหานักแสดง" onkeyup="search_actor()" />
		<ul class="actor-list">
			<c:forEach items="${actlist}" var="act">
				<li class="actor-box"><a href="${act.actors_id}"> <span class="actor-name">${act.actorName}</span>
						<img
						src="../${act.image}"
						alt="">
				</a></li>
			</c:forEach>

		</ul>
	</div>

	<a href="insert_movie.html" class="float"> <i
		class="fa fa-plus my-float"></i>
	</a>

	<!-- <script>
        const moviePics = document.querySelectorAll('.mov-pic');
        moviePics.forEach((moviePic) => {
        const movieName = moviePic.querySelector('.moviename-text').textContent;
        const movieType = moviePic.querySelector('.movtype').textContent;
        })
    </script> -->

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		crossorigin="anonymous">
		
	</script>
	<script src="https://kit.fontawesome.com/7ef6297bb4.js"
		crossorigin="anonymous"></script>



	<footer class="py-3 my-4 mt-5">
		<ul class="nav justify-content-center border-bottom pb-3 mb-3">
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">Home</a></li>
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">Features</a></li>
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">Pricing</a></li>
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">FAQs</a></li>
			<li class="nav-item"><a href="#"
				class="nav-link px-2 text-muted">About</a></li>
		</ul>
		<p class="text-center text-muted">© 2022 Company, Inc</p>
	</footer>

</body>
</html>