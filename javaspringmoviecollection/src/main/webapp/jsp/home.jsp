<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Collections</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

<link href="css/home.css" rel="stylesheet">
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

	<c:if test="${sessionScope.msg != null}">
		<div class="alert alert-danger" role="alert">
			${msg}
			<c:remove var="msg" />
		</div>
	</c:if>

	<!-- Navbar -->

	<jsp:include page="navbar.jsp"></jsp:include>
	<!-- Navbar -->


	<div class="listyear">

		<div id="horizontal-nav">
			<div class="btn-prev" role="button" tabindex="0">
				<svg viewBox="0 0 24 24">
      <path d="M8.59,16.59L13.17,12L8.59,7.41L10,6l6,6l-6,6L8.59,16.59z"
						fill="hsl(141, 15%, 50%)">
      </path>
    </svg>
			</div>
			<div class="menu-wrap">
				<ul class="menu">
					<c:forEach items="${listcat}" var="cat">
						<li class="list-item">
						<a href="${cat.category_id}" class="pill"> ${cat.category} </a></li>
					</c:forEach>


				</ul>
			</div>
			<div class="btn-next" role="button">
				<svg viewBox="0 0 24 24">
      <path d="M8.59,16.59L13.17,12L8.59,7.41L10,6l6,6l-6,6L8.59,16.59z"
						fill="hsl(141, 15%, 50%)">
      </path>
    </svg>
			</div>
		</div>


	</div>

	<div class="moviecard">
		<div class="movcard">
			<c:forEach items="${listmovcat}" var="listmov">

				<c:forEach items="${listmov.value}" var="mov">
					<a href="movie_detail/${mov.movies_id}" class="mov-pic">

						<div class="wrap-pic">
							<img src="${mov.posterimage}" class="img-mov" alt="...">
						</div>
						<div class="movie-name">
							<p class="moviename-text">${mov.moviename}</p>
						</div>
						<p class="movtype">categories: ${mov.category}</p>
					</a>
				</c:forEach>
			</c:forEach>
		</div>
	</div>

	<a href="admin/addmovies" class="float"> <i
		class="fa fa-plus my-float"></i>
	</a>

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

<script>

function resizeMenu() {
	  let wrapW = $("#horizontal-nav .menu-wrap").width(),
	    menuW = $("#horizontal-nav .menu").width();

	  let itemsToScroll = 3,
	    widthToScroll = 0,
	    scrollX = parseFloat($("#horizontal-nav .menu-wrap .menu").css("left"));

	  if ($(this).hasClass("btn-prev")) {
	    let prevItemIndex,
	      prevItemsWidth = 0;

	    $("#horizontal-nav .list-item").each((i, el) => {
	      if (prevItemIndex !== undefined) return;
	      prevItemsWidth += $(el).outerWidth() + 14;
	      if (Math.ceil(prevItemsWidth) > Math.abs(scrollX)) prevItemIndex = i;
	    });

	    for (
	      let i = prevItemIndex;
	      i >= 0 && i > prevItemIndex - itemsToScroll;
	      i--
	    )
	      prevItemsWidth -=
	        $(`#horizontal-nav .list-item:eq(${i})`).outerWidth() + 14;

	    widthToScroll = scrollX - prevItemsWidth;
	    let newScrollX = Math.abs(scrollX) + widthToScroll;
	    $("#horizontal-nav .menu-wrap .menu").css({ left: newScrollX });

	    $(this).toggleClass("hidden", !Math.floor(newScrollX));
	    $(".btn-next").removeClass("hidden");
	  } else {
	    let nextItemIndex,
	      prevItemsWidth = 0;

	    $("#horizontal-nav .list-item").each((i, el) => {
	      if (nextItemIndex !== undefined) return;
	      prevItemsWidth += $(el).outerWidth() + 14;
	      if (Math.floor(prevItemsWidth - 14) > Math.abs(scrollX) + wrapW)
	        nextItemIndex = i;
	    });

	    if (scrollX + wrapW >= menuW) {
	      if (!$(this).hasClass("hidden")) $(this).addClass("hidden");
	      return;
	    }
	    $(this).removeClass("hidden");

	    for (
	      let i = nextItemIndex + 1;
	      i < nextItemIndex + itemsToScroll &&
	      nextItemIndex + itemsToScroll <= $("#horizontal-nav .list-item").length;
	      i++
	    )
	      prevItemsWidth +=
	        $(`#horizontal-nav .list-item:eq(${i})`).outerWidth() + 14;
	    widthToScroll = prevItemsWidth - 14 - (Math.abs(scrollX) + wrapW);
	    let newScrollX = scrollX - widthToScroll;
	    $("#horizontal-nav .menu-wrap .menu").css({ left: newScrollX });
	    console.log(Math.round(Math.abs(newScrollX + wrapW)), menuW);
	    $(this).toggleClass(
	      "hidden",
	      Math.round(Math.abs(newScrollX) + wrapW) >= Math.round(menuW)
	    );
	    $(".btn-prev").removeClass("hidden");
	  }
	}
	$(() => {
	  $("#horizontal-nav .list-item").each(function () {
	    if ($(this).find(".sub-menu").length) $(this).addClass("has-submenu");
	  });
	  $("#horizontal-nav").on("click", ".btn-prev, .btn-next", resizeMenu);

	  $(document).on("resize", resizeMenu);
	});



</script>

</html>