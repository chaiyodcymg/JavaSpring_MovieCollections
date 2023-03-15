<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Collections</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Kodchasan">

 <c:if test="${searchmov == null}">
      <link href="../../css/style.css" rel="stylesheet">
 </c:if>
 <c:if test="${searchmov != null}">
      <link href="../../css/style.css" rel="stylesheet">
 </c:if>

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Labrada">


<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">


</head>
<body>

	<c:if test="${sessionScope.msg != null}">
		<div class="alert alert-danger" role="alert">
			${msg}
			<c:remove var="msg" />
		</div>
	</c:if>
<!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light shadow-lg">
        <!-- Container wrapper -->
        <div class="container">
            <!-- Navbar brand -->
            <a href="/" class="navbar-brand me-5">
                <h1 style="font-family: 'Kodchasan';"><span>C</span>ollect<span>M</span>ovie</h1>
            </a>

            <!-- Toggle button -->
            <button class="navbar-toggler" type="button" data-mdb-toggle="collapse"
                data-mdb-target="#navbarButtonsExample" aria-controls="navbarButtonsExample" aria-expanded="false"
                aria-label="Toggle navigation">
                <i class="fas fa-bars"></i>
            </button>

            <!-- Collapsible wrapper -->
            <div class="collapse navbar-collapse" id="navbarButtonsExample">
                <!-- Left links -->
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="/">หน้าแรก</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/actorlist">นักแสดง</a>
                    </li>
                    <li class="nav-item">
                        <div class="dropdown">
                            <a class="nav-link dropdown-toggle" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                              ประเภทหนัง
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                              <c:forEach items="${listcat}" var="cat">
                               	<li><a class="dropdown-item" href="../../movie/category/${cat.category_id}" >${cat.category}</a></li>
                              </c:forEach>
                            </ul>
                          </div>
                    </li>
                </ul>
                <!-- Left links -->
                <div class="d-flex align-items-center">
                    <c:if test="${sessionScope.session != null}">
								<a href="../../admin/logout" class="signin-btn">Logout</a>
							</c:if>
							
							<c:if test="${sessionScope.session == null}">
								 <a href="../../admin/login" class="signin-btn">Login</a>
							</c:if>
                </div>

            </div>
            <!-- Collapsible wrapper -->
        </div>
        <!-- Container wrapper -->
    </nav>
    <!-- Navbar -->

	<h1  class="text-center my-5" style="font-family: 'Kodchasan';" >${catename.category}</h1>
	

	<div class="moviecard">
		<div class="movcard">
			<c:forEach items="${listmovcat}" var="mov">
			<div class="mov-pic">
                <a href="../../movie_detail/${mov.movies_id}" class="mov-pic-a">
                    <div class="wrap-pic">
                        <c:if test="${searchmov == null}">
         					<img src="../../${mov.posterimage}" class="img-mov" alt="...">
         				</c:if>
						<c:if test="${searchmov != null}">
         					<img src="../${mov.posterimage}" class="img-mov" alt="...">
         				</c:if>
                    </div>
                    <div class="movie-name">
                        <p class="moviename-text">${mov.moviename}</p>
                    </div>
                 
                    
                </a>
                <div class="mov-edit-div">
	                <c:if test="${sessionScope.session != null}">
						<a class="mov-edit btn btn-primary" href="../../admin/editmovies/${mov.movies_id}">แก้ไข</a>
	                    <a class="mov-edit btn btn-danger" href="../../admin/deletemovies/${mov.movies_id}">ลบ</a>
					</c:if>
                </div>
            </div>

			</c:forEach>
			
		</div>
	</div>
	
	<c:if test="${sessionScope.session != null}">		
		<a href="../../admin/addmovies" class="float"> <i
		class="fa fa-plus my-float"></i>
		</a>
	</c:if>
	

	<!-- <script>
        const moviePics = document.querySelectorAll('.mov-pic');
        moviePics.forEach((moviePic) => {
        const movieName = moviePic.querySelector('.moviename-text').textContent;
        const movieType = moviePic.querySelector('.movtype').textContent;
        })
    </script> -->


	<script src="https://kit.fontawesome.com/e09289cc9f.js"
		crossorigin="anonymous"></script>
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