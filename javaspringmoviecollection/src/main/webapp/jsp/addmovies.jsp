<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home Page</title>



<!--  CSS -->

   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<link href="../../css/style.css" rel="stylesheet">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Labrada">
<!--  JavaScript -->

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
	integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
	crossorigin="anonymous"></script>
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- JS & CSS library of MultiSelect plugin -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-multiselect/0.9.13/js/bootstrap-multiselect.js">
</script>



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
					<li class="nav-item"><a class="nav-link "
						href="/actorlist">นักแสดง</a></li>
					<li class="nav-item">
						<div class="dropdown">
							<a class="nav-link dropdown-toggle" role="button"
								id="dropdownMenuLink" data-bs-toggle="dropdown"
								aria-expanded="false"> ประเภทหนัง </a>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
								<c:forEach items="${catlist}" var="cat">
									<li><a class="dropdown-item" href="/movie/category/${cat.category_id}">${cat.category}</a></li>
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
	<div class="container-md mt-3 insert_form">
		<!-- Button trigger modal -->
		<button type="button" class="btn add-actor-btn" data-bs-toggle="modal"
			data-bs-target="#exampleModal">เพิ่มนักแสดง</button>

		<!-- Modal -->
		<div class="modal fade " id="exampleModal" tabindex="-1"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<form action="/movie/addactor" enctype="multipart/form-data"
				method="post">
				<div class="modal-dialog modal-add">
					<div class="modal-content">
						<div class="modal-header">
							<h1 class="modal-title fs-5" id="exampleModalLabel">เพิ่มนักแสดง</h1>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">

							<div class="mb-3">
								<label for="InputNameActor" class="form-label">ชื่อ</label> <input
									type="text" name="Actorname" class="form-control"
									id="InputActorName" aria-describedby="emailHelp"> <label
									for="InputAge" class="form-label">วันเกิด</label> <input
									type="date" name="birthday" class="form-control" id="InputAge"
									aria-describedby="emailHelp"> <label for="InputGender"
									class="form-label">เพศ</label><br> <select
									class="form-select" id="actorage"
									aria-label="Floating label select example" name="actorgender">
									<option selected value="f">ผู้หญิง</option>
									<option value="m">ผู้ชาย</option>

								</select>
								<div class="mb-3 mt-2">
									<img alt="" id="actpreview" width="200px"> <label
										for="formFile" class="form-label">รูปภาพ</label> <input
										class="form-control" type="file" id="formFile" name="img"
										onchange="actorPreview(event);">
								</div>


							</div>

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Close</button>
							<button type="submit" class="btn submit-add-act">Save
								changes</button>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="card shadow-lg">
			<div class="card-body">
				<form action="/movie/addmovies" enctype="multipart/form-data"
					method="post">
					<div class="mb-3">
						<label for="exampleInputEmail1" class="form-label">ชื่อหนัง</label>
						<input type="text" class="form-control" id="exampleInputEmail1"
							aria-describedby="emailHelp" name="moviename">
					</div>
					
					<div class="mb-3">
						<label for="year" class="form-label">ปีที่ฉาย</label>
						<input type="text" class="form-control" id="year"
							aria-describedby="emailHelp" name="yearmovie">
					</div>
					<div class="select-btn categories-btn mb-3">
                            <span class="btn-text">เลือกประเภท</span>
                            <span class="arrow-dwn">
                                <i class="fa-solid fa-chevron-down"></i>
                            </span>
                        </div>
					<ul class="list-items" id="categories">
					<c:forEach items="${catlist}" var="cat">
						<li class="item-checkbox"><input type="checkbox" value="${cat.category_id }" name="category"> ${cat.category} </li>
					</c:forEach>                    
                        </ul>
					<div class="mb-3">
						<div class="select-btn character-btn">
							<span class="btn-text">ตัวละคร</span> <span class="arrow-dwn">
								<i class="fa-solid fa-chevron-down"></i>
							</span>
						</div>

						<ul class="list-items" id="list-items">
							<li class="item"><input type="text"
								class="form-control me-2" id="role" placeholder="ชื่อตัวละคร"
								name="role"> <span>เล่นโดย</span> <select
								class="form-select ms-2" id="actorID1"
								aria-label="Floating label select example" name="actorID">
									<option selected>ชื่อนักแสดง</option>
									<c:forEach items="${actorlist}" var="act">
										<option value="${act.actors_id}">${act.actorName}</option>
									</c:forEach>
							</select>
								<button type="button" class="btn btn-primary ms-3 add-ch">
									<i class="fa-solid fa-plus"></i>
								</button></li>


						</ul>

					</div>
					<label for="exampleInputPassword1" class="form-label">คำบรรยาย</label>
					<div class="mb-3">
						<textarea id="" rows="4" cols="50" class="description-movie"name="description"></textarea>
					</div>

					<div class="mb-3">
						<img alt="" src="#" width="200px" id="imgpreview"> <label
							for="exampleInputPassword1" class="form-label">รูปภาพ</label> <input
							type="file" class="form-control" id="chooseimg" name="img"
							onchange="showPreview(event);">
					</div>
					<div class="mb-3 mt-2">
						<img alt="" src="#" width="200px" id="posterpreview"> <label
							for="formFile" class="form-label">โปสเตอร์</label> <input
							class="form-control" type="file" id="formFile" name="poster" onchange="posterPreview(event);">
					</div>
					<button type="submit" class="btn submit-add-act">Submit</button>

				</form>
			</div>
		</div>
	</div>

	<script src="https://kit.fontawesome.com/7ef6297bb4.js"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"
		integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD"
		crossorigin="anonymous">
    </script>
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/jquery.multiselect/1.13/jquery.multiselect.js"></script> -->
	<script>
        

        const select1 = document.querySelector(".categories-btn"),
        select2 = document.querySelector(".character-btn "),
        items = document.querySelectorAll(".item");

        select1.addEventListener("click", () => {
            select1.classList.toggle("open");
    });

    select2.addEventListener("click", () => {
        select2.classList.toggle("open");
    });
        $(".add-ch").click(function () {
            var options = $('#actorID1').html();
            var html = ` <li class="item">
                        <input type="text" class="form-control me-2" id="role" placeholder="ชื่อตัวละคร"  name="role" >
                        <span>เล่นโดย</span>
                        <select class="form-select ms-2"  aria-label="Floating label select example" name="actorID" >
                        `
                        +options+
                                `</select>
                                <button type="button" class="btn btn-danger ms-3 remove-row"> 
                                    <i class="fa-solid fa-trash"></i>
                                </button>
                            </li>`;


            $('#list-items').append(html);
        });

        // remove row
        $(document).on('click', '.remove-row', function () {
            $(this).closest('.item').remove();
        });

        function showPreview(event){
			  if(event.target.files.length > 0){
			    var src = URL.createObjectURL(event.target.files[0]);
			    var preview = document.getElementById("imgpreview");
			    preview.src = src;
			    preview.style.display = "block";
			  }
			}
		
		function actorPreview(event){
			  if(event.target.files.length > 0){
			    var src = URL.createObjectURL(event.target.files[0]);
			    var preview = document.getElementById("actpreview");
			    preview.src = src;
			    preview.style.display = "block";
			  }
			}
		
		function posterPreview(event){
			  if(event.target.files.length > 0){
			    var src = URL.createObjectURL(event.target.files[0]);
			    var preview = document.getElementById("posterpreview");
			    preview.src = src;
			    preview.style.display = "block";
			  }
			}
		
    </script>



</body>

</html>





