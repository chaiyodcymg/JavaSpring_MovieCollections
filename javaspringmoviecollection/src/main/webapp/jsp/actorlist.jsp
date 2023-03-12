<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Collections</title>

<link href="../css/style.css" rel="stylesheet">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Labrada">

<!--  JavaScript -->


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
	<nav class="navbar navbar-expand-lg navbar-light bg-light shadow-lg">
		<!-- Container wrapper -->
		<div class="container">
			<!-- Navbar brand -->
			<a href="/" class="navbar-brand me-5">
				<h1 style="font-family: 'Kodchasan';">
					<span>C</span>ollect<span>M</span>ovie
				</h1>
			</a>

			<!-- Toggle button -->
			<button class="navbar-toggler" type="button"
				data-mdb-toggle="collapse" data-mdb-target="#navbarButtonsExample"
				aria-controls="navbarButtonsExample" aria-expanded="false"
				aria-label="Toggle navigation">
				<i class="fas fa-bars"></i>
			</button>

			<!-- Collapsible wrapper -->
			<div class="collapse navbar-collapse" id="navbarButtonsExample">
				<!-- Left links -->
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" href="/">หน้าแรก</a>
					</li>
					<li class="nav-item"><a class="nav-link active"
						href="/actorlist">นักแสดง</a></li>
					<li class="nav-item">
						<div class="dropdown">
							<a class="nav-link dropdown-toggle" role="button"
								id="dropdownMenuLink" data-bs-toggle="dropdown"
								aria-expanded="false"> ประเภทหนัง </a>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
								<c:forEach items="${listcat}" var="cat">
									<li><a class="dropdown-item" href="${cat.category_id}">${cat.category}</a></li>
								</c:forEach>
							</ul>
						</div>
					</li>
				</ul>
				<!-- Left links -->
				<div class="d-flex align-items-center">
					<c:if test="${sessionScope.session != null}">
						<a href="admin/logout" class="signin-btn">Logout</a>
					</c:if>

					<c:if test="${sessionScope.session == null}">
						<a href="admin/login" class="signin-btn">Login</a>
					</c:if>
				</div>

			</div>
			<!-- Collapsible wrapper -->
		</div>
		<!-- Container wrapper -->
	</nav>
	<!-- Navbar -->

	<div class="container mt-5 mb-5">
		<h1>นักแสดง</h1>
		<form class="d-flex" role="search" action="../movie/actorsearch">
			<c:if test="${searchact != null}">
				<input type="search" id="filter" name="actor" value="${searchact}"
					class="shadow-lg mt-3 mb-4" placeholder="ค้นหานักแสดง" />
			</c:if>
			<c:if test="${searchact == null}">
				<input type="search" id="filter" name="actor"
					class="shadow-lg mt-3 mb-4" placeholder="ค้นหานักแสดง" />
			</c:if>

		</form>
		<c:if test="${searchact != null}">
			<p>ค้นหา '${searchact}' ใน นักแสดง</p>
		</c:if>



		<ul class="actor-list">
			<c:forEach items="${actlist}" var="act">
				
			
				<li class="li-actor-box">
				<c:if test="${sessionScope.session != null}">
					<button type="button" class="btn add-actor-btn"
						data-bs-toggle="modal"
						data-bs-target="#ActorModal${act.actors_id}">แก้ไขนักแสดง</button>
					<a href="admin/deleteactors/${act.actors_id}"> ลบ </a>
				</c:if>
				<a href="../actor/${act.actors_id}">
				<div class="actor-box">
				
						<p class="actor-name">${act.actorName}</p> 
						<img src="../${act.image}" alt="#">
				
				</div>
				</a>
				</li>

				<!-- Modal -->
				<div class="modal fade " id="ActorModal${act.actors_id}"
					tabindex="-1" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<form action="/movie/editactor" enctype="multipart/form-data"
						method="post">
						<div class="modal-dialog modal-add">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5" id="exampleModalLabel">
										แก้ไขนักแสดง</h1>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">

									<div class="mb-3">
										<input type="text" value="${act.actors_id}" name="actors_id"
											style="display: none"> <label for="InputNameActor"
											class="form-label">ชื่อ</label> <input type="text"
											name="Actorname" class="form-control" id="InputActorName"
											aria-describedby="emailHelp" value="${act.actorName}">
										<label for="InputAge" class="form-label">วันเกิด</label> <input
											type="date" name="birthday" class="form-control"
											id="InputAge" aria-describedby="emailHelp"
											value="${act.birthday}"> <label for="InputGender"
											class="form-label">เพศ</label><br> <select
											class="form-select" id="actorage"
											aria-label="Floating label select example" name="actorgender">
											<c:if test="${act.gender != 'm'}">
												<option selected value="f">ผู้หญิง</option>
												<option value="m">ผู้ชาย</option>
											</c:if>
											<c:if test="${act.gender == 'm'}">
												<option selected value="m">ผู้ชาย</option>
												<option value="f">ผู้หญิง</option>
											</c:if>


										</select>
										<div class="mb-3 mt-2">
											<img alt="" src="${act.image}"
												id="actpreview${act.actors_id}" width="200px"> <label
												for="formFile" class="form-label">รูปภาพ</label> <input
												class="form-control" type="file" id="formFile" name="img"
												onchange="actorPreview${act.actors_id}(event);">
										</div>


									</div>

								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Close</button>
									<button type="submit" class="btn">Save changes</button>
								</div>
							</div>
						</div>
					</form>
				</div>
				<!-- Modal -->
				<script>
					function actorPreview${act.actors_id}(event) {
						if (event.target.files.length > 0) {
							var src = URL
									.createObjectURL(event.target.files[0]);
							var preview = document
									.getElementById("actpreview${act.actors_id}");
							preview.src = src;
							preview.style.display = "block";
						}
					}
				</script>
			

			</c:forEach>

		</ul>
	</div>

	<a href="insert_movie.html" class="float"> <i
		class="fa fa-plus my-float"></i>
	</a>


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