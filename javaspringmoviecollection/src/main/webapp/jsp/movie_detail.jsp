<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Collections</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Labrada">

<!--  JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<a  onclick="history.back()"  style="position: absolute; left: 60px; top: 60px; z-index: 20;cursor: pointer;">
		<i class="fa-solid fa-circle-arrow-left fa-3x" style="color:white;"></i>
	</a>
	<div class=" mb-3 text-center img-movie-preview shadow-lg">
		<img src="../${detail.image}" alt="">
		<div class="overlay"></div>
	</div>
	<div class="text-preview text-center">
		<h1>${detail.moviename}</h1>
		
		<p>ประเภท: ${detail.category}</p>
	</div>
	<div class="container-md mt-5 p-5">
		<div class="">
			<p>${detail.description}</p>
		</div>
		<h3>นักแสดง</h3>
		<div class="mb-5 pb-4">
			<ul class="actor-row overflow-x-scroll">
			
			<c:forEach items="${Listactor}" var="actor">
				
				<li><a href="../actor/${actor.actors_id}" class="me-4 a-act">
						<div class="img-actor">
							<img src="../${actor.image}" alt="" >
						</div>
						<p class="mb-0">${actor.actorName} </p>
						<p class="mb-0">ตัวละคร : ${actor.role}</p>
				</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
		crossorigin="anonymous">
		
	</script>
	<script src="https://kit.fontawesome.com/7ef6297bb4.js"
		crossorigin="anonymous"></script>

</body>
</html>